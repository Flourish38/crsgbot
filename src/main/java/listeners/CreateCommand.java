package listeners;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static listeners.BotConfig.RACES_CATEGORY_ID;

public class CreateCommand  extends CommandListener{

    public CreateCommand() {
        super("create");
    }

    @Override
    void command(@Nonnull GuildMessageReceivedEvent event) {
        if(!(event.getChannel().getIdLong() == BotConfig.CREATION_CHANNEL_ID)) return;
        var dataMessage = BotConfig.dataMessage(event);
        List<String> data = Arrays.asList(dataMessage.getContentRaw().split("\n"));
        for(var s : data)
        {
            if(s.split(" ")[0].equals(event.getAuthor().getId()))
            {
                event.getChannel().sendMessage("You have already created a race.").queue();
                return;
            }
        }
        Category raceCategory = event.getGuild().getCategoryById(RACES_CATEGORY_ID);
        int races = Integer.parseInt(data.get(0));
        var channel = raceCategory.createTextChannel("race" + ++races)
                .setTopic("Inactive - type !start to start")
                .setPosition(Integer.MAX_VALUE - races)
                .complete();
        channel.sendMessage(event.getAuthor().getId()).queue((x) -> x.pin().queue());
        channel.createPermissionOverride(event.getMember()).setAllow(Permission.MESSAGE_READ).queue();
        channel.sendMessage(event.getAuthor().getAsMention() + " has joined the race.").queue();
        data.set(0, event.getAuthor().getId() + " " + channel.getId());
        StringBuilder newTopic = new StringBuilder(Integer.toString(races));
        for(var s : data) {
            newTopic.append("\n").append(s);
        }
        dataMessage.editMessage(newTopic).queue();
    }
}

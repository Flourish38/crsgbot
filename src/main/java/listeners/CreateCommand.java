package listeners;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateCommand  extends CommandListener{
    private static final long CREATION_CHANNEL_ID = 728397888035749959L;
    private static final long RACES_CATEGORY_ID = 724049312891273326L;

    public CreateCommand() {
        super("create");
    }

    @Override
    void command(@Nonnull GuildMessageReceivedEvent event) {
        if(!(event.getChannel().getIdLong() == CREATION_CHANNEL_ID)) return;
        Category raceCategory = event.getGuild().getCategoryById(RACES_CATEGORY_ID);
        String[] topic = event.getChannel().getTopic().split(" \\| ");
        int races = Integer.parseInt(topic[0]);
        var channel = raceCategory.createTextChannel("race" + ++races).setPosition(Integer.MAX_VALUE - races).complete();
        channel.sendMessage(event.getAuthor().getAsMention() + " has joined the race.").queue();
        topic[0] = event.getAuthor().getId() + " " + channel.getId();
        StringBuilder newTopic = new StringBuilder(Integer.toString(races));
        for(var s : topic) {
            newTopic.append(" | ").append(s);
        }
        event.getChannel().getManager().setTopic(newTopic.toString()).queue();
    }
}

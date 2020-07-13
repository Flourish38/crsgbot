package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Random;

public class StartCommand extends CommandListener {
    private static final Random rng = new Random();

    public StartCommand() {
        super("start");
    }

    @Override
    void command(@Nonnull GuildMessageReceivedEvent event) {
        if(event.getChannel().getParent() != null
                && event.getChannel().getParent().getIdLong() != BotConfig.RACES_CATEGORY_ID) return;
        if(!event.getChannel().getTopic().startsWith("Inactive")) return;
        /*if(event.getChannel().retrievePinnedMessages().complete().get(0).getContentRaw().split(" ").length < 2)
        {
            event.getChannel().sendMessage("There must be at least 2 players to start a race.").queue();
            return;
        }*/
        String messageId = event.getChannel().sendMessage("World seed is `" + rng.nextLong() + "`. Go!").complete().getId();
        event.getChannel().getManager().setTopic("Active - " + messageId).queue();
    }
}

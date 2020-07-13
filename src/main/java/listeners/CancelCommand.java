package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;

public class CancelCommand extends CommandListener {
    public CancelCommand() {
        super("cancel");
    }

    @Override
    void command(@Nonnull GuildMessageReceivedEvent event) {
        if(event.getChannel().getParent() == null
                || event.getChannel().getParent().getIdLong() != BotConfig.RACES_CATEGORY_ID) return;
        if(!event.getChannel().getTopic().startsWith("Inactive"))
        {
            if(event.getChannel().getTopic().startsWith("Active")
                    && new ArrayList<>(event.getMember().getRoles()).removeIf((x) -> x.getIdLong() == BotConfig.MODERATOR_ROLE_ID))
                cancel(event, "Cancelled by moderator");
            return;
        }
        if(event.getChannel().retrievePinnedMessages().complete().get(0).getContentRaw().split(" ")[0]
                .equals(event.getAuthor().getId()))
        {
            cancel(event, "Cancelled by race owner.");
        }
        else if (new ArrayList<>(event.getMember().getRoles()).removeIf((x) -> x.getIdLong() == BotConfig.MODERATOR_ROLE_ID))
            cancel(event, "Cancelled by moderator");

    }
    /*

     */

    private static void cancel(@Nonnull GuildMessageReceivedEvent event, String reason)
    {
        event.getChannel().sendMessage("Cancelling Race: " + reason).queue();
        event.getChannel().getManager().setTopic("Cancelled: " + reason).queue();
        var dataMessage = BotConfig.dataMessage(event);
        var data = new ArrayList<>(Arrays.asList(dataMessage.getContentRaw().split("\n")));
        data.removeIf((x) -> x.contains(event.getChannel().getId()));
        StringBuilder sb = new StringBuilder();
        for(var s : data) sb.append(s).append(" ");
        dataMessage.editMessage(sb.toString().strip()).queue();
    }
}

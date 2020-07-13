package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class LeaveCommand extends CommandListener {
    public LeaveCommand() {
        super("leave");
    }

    @Override
    void command(@Nonnull GuildMessageReceivedEvent event) {
        if(event.getChannel().getParent() == null
                || event.getChannel().getParent().getIdLong() != BotConfig.RACES_CATEGORY_ID) return;
        if(!event.getChannel().getTopic().startsWith("Inactive")) return;
        var usersMessage = event.getChannel().retrievePinnedMessages().complete().get(0);
        var users = Arrays.asList(usersMessage.getContentRaw().split(" "));
        switch (users.indexOf(event.getAuthor().getId()))
        {
            case 0: event.getChannel().sendMessage("Owner of room cannot leave. Type `" + prefix + "cancel` instead.").queue();
            case -1: return;
            default: break;
        }
        users.remove(event.getAuthor().getId());
        event.getChannel().getPermissionOverride(event.getMember()).delete().queue();
        StringBuilder sb = new StringBuilder();
        for(var s : users) sb.append(s).append(" ");
        usersMessage.editMessage(sb.toString().strip()).queue();
    }
}

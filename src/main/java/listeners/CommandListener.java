package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public abstract class CommandListener extends ListenerAdapter {
    protected static String prefix;
    protected String command;

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        var msg = event.getMessage();

        if((msg.getMentionedMembers().size() > 0
                && msg.getMentionedMembers().get(0).getId().equals(event.getJDA().getSelfUser().getId())
                && msg.getContentRaw().contains(command)) // bot pinged
            ||
                (prefix != null
                && msg.getContentRaw().startsWith(prefix + command))) // msg starts with prefix
        {
            command(event);
        }
    }

    public static void setPrefix(String p){
        prefix = p;
    }

    abstract void command(GuildMessageReceivedEvent event);

}

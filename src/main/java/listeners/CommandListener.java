package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public abstract class CommandListener extends ListenerAdapter {
    protected static String prefix;
    protected final String command;

    public CommandListener(String command){
        this.command = command;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        var raw = event.getMessage().getContentRaw();

        if((raw.startsWith(event.getJDA().getSelfUser().getAsMention() + " " + command)) // ping as prefix
            || (prefix != null && raw.startsWith(prefix + command))) // msg starts with prefix
        {
            command(event);
        }
    }

    public static void setPrefix(String p){
        prefix = p;
    }

    abstract void command(@Nonnull GuildMessageReceivedEvent event);

}

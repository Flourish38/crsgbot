package listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class ShutdownListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if(event.getGuild().getId().equals("612357251625254953"))
            if(event.getMessage().getMentionedUsers().size() > 0
                    && event.getMessage().getMentionedUsers().get(0).equals(event.getJDA().getSelfUser())
                    && event.getMessage().getContentRaw().contains("shutdown"))
                event.getJDA().shutdown();
    }
}

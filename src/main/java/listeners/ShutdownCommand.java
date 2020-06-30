package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ShutdownCommand extends CommandListener {
    public ShutdownCommand(){
        command = "shutdown";
    }

    @Override
    void command(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Shutting down...").complete();
        event.getJDA().shutdownNow();
    }
}

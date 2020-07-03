package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class ShutdownCommand extends CommandListener {
    public ShutdownCommand(){
        super("shutdown");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Shutting down...").complete();
        ShutdownHandler.handle(event);
        event.getJDA().shutdown();
    }
}

package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.time.Duration;

public class PingCommand extends CommandListener{
    public PingCommand(){
        super("ping");
    }

    @Override
    void command(GuildMessageReceivedEvent event) {
        long startTime = System.currentTimeMillis();
        event.getChannel().sendMessage("Pong!").queue((a) -> a.editMessage((System.currentTimeMillis() - startTime) + "ms").queue());
    }
}

package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ParameterCommand extends CommandListener {
    public ParameterCommand(String command) {
        super(command);
    }

    @Override
    void command(GuildMessageReceivedEvent event) {
        String raw = event.getMessage().getContentRaw();
        raw = raw.substring(raw.indexOf(command) + command.length()).strip();
        command(event, Arrays.asList(raw.split(" ")));
    }

    abstract void command(GuildMessageReceivedEvent event, List<String> params);
}

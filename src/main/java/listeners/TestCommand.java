package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class TestCommand extends ParameterCommand {
    public TestCommand() {
        super("test");
    }

    @Override
    void command(GuildMessageReceivedEvent event, List<String> params) {
        String yeet = "\uDB40\uDDF0";
        for(var s : params)
        {
            yeet += s + "\n";
        }
        event.getChannel().sendMessage(yeet).queue();
    }
}

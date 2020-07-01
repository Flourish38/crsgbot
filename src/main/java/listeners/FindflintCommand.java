package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.Random;

public class FindflintCommand extends ParameterCommand {
    private static final Random rng = new Random();
    public FindflintCommand() {
        super("findflint");
    }

    @Override
    void command(GuildMessageReceivedEvent event, List<String> params) {
        int n;
        try {
            n = Integer.parseInt(params.get(0));
        } catch (Exception e)
        {
            event.getChannel().sendMessage("An error occurred. Did you pass an integer as the first argument?").queue();
            return;
        }
        int gravel = 0;
        for(int flint = 0; flint < n; gravel++)
            if(rng.nextFloat() < 0.1)
                flint++;
        event.getChannel().sendMessage("You dug " + gravel + " gravel before you got " + n + " flint.").queue();
    }
}

package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class FindseedCommand extends CommandListener {
    private static final Random rng = new Random();
    public FindseedCommand(){
        super("findseed");
    }

    @Override
    void command(GuildMessageReceivedEvent event) {
        int eye = 0;
        for(int i = 0; i < 12; i++)
            if (rng.nextFloat() < 0.1f)
                eye++;
        event.getChannel().sendMessage("Your seed is a " + eye + " eye.").queue();
    }
}

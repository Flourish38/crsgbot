package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class FindbarterCommand extends CommandListener {
    private static final Random rng = new Random();
    public FindbarterCommand(){
        super("findbarter");
    }

    @Override
    void command(GuildMessageReceivedEvent event) {
        int gold = 0;
        int pearls = 0;
        for(; pearls < 12; gold++)
            if (rng.nextFloat() < 20f/423)
                pearls += 4 + rng.nextInt(5);
        event.getChannel().sendMessage("You needed " + gold + " gold to get " + pearls + " pearls.").queue();
    }
}

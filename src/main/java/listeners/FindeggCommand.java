package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class FindeggCommand extends CommandListener {
    private static final Random rng = new Random();
    public FindeggCommand(){
        command = "findegg";
    }

    @Override
    void command(GuildMessageReceivedEvent event) {
        if(rng.nextFloat() < 0.2 && rng.nextFloat() < 0.15)
            event.getChannel().sendMessage("The fox had an egg!").queue();
        else {
            int ticks = 6000 + rng.nextInt(6000);
            event.getChannel().sendMessage("The fox had no egg, so you waited " + ticks/(20*60) + "m "
                    + (ticks/20)%60 + (ticks%20 == 0 ? "" : "." + String.format("%02d", (ticks%20)*5)) + "s for a chicken to lay one.").queue();
        }
    }
}

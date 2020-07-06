package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class ShutdownCommand extends CommandListener {
    public ShutdownCommand(){
        super("shutdown");
    }

    @Override
    void command(@NotNull GuildMessageReceivedEvent event) {
        if(event.getAuthor().getIdLong() != BotConfig.BOT_ADMIN_ID) return;
        event.getChannel().sendMessage("Shutting down...").queue();
        ShutdownHandler.handle(event);
        event.getJDA().shutdown();
    }
}

class ShutdownHandler {

    public static void handle(GuildMessageReceivedEvent event) {
        var dataMessage = BotConfig.dataMessage(event);
        String[] data = dataMessage.getContentRaw().split("\n");
        dataMessage.editMessage(data[0]).queue();
        for(int i = 1; i < data.length; i++){
            var raceChannel = event.getGuild().getTextChannelById(data[i].split(" ")[1]);
            raceChannel.sendMessage("Cancelling Race: Bot shutting down.").queue();
            raceChannel.getManager().setTopic("Cancelled: Bot shut down").queue();
        }
    }
}
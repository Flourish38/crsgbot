package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ShutdownHandler {
    private static final long CREATION_CHANNEL_ID = 728397888035749959L;

    public static void handle(GuildMessageReceivedEvent event) {
        var channel = event.getGuild().getTextChannelById(CREATION_CHANNEL_ID);
        String[] topic = channel.getTopic().split(" \\| ");
        channel.getManager().setTopic(topic[0]).queue();
        for(int i = 1; i < topic.length; i++){
            var raceChannel = event.getGuild().getTextChannelById(topic[i].split(" ")[1]);
            raceChannel.sendMessage("Cancelling Race: Bot shutting down.").queue();
            raceChannel.getManager().setTopic("Cancelled: Bot shut down").queue();
        }

    }
}

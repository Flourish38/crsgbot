package listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;

public class BotConfig {
    public static final long CREATION_CHANNEL_ID = 729833469567303690L;
    public static final long RACES_CATEGORY_ID = 724049312891273326L;
    public static final long BOT_ADMIN_ID = 165216105197993984L;
    public static long CREATION_DATA_MESSAGE_ID;

    public static Message dataMessage(Event event){
        if(CREATION_DATA_MESSAGE_ID == 0) throw new RuntimeException("Init incomplete");
        return event.getJDA().getTextChannelById(CREATION_CHANNEL_ID).retrieveMessageById(CREATION_DATA_MESSAGE_ID).complete();
    }
}

import listeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) throws InterruptedException, LoginException, FileNotFoundException {
        Scanner scanner = new Scanner(new File("token.txt"));
        JDA jda = JDABuilder.createDefault(scanner.nextLine()).build();
        CommandListener.setPrefix("!");
        jda.addEventListener(new PingCommand());
        jda.addEventListener(new ShutdownCommand());
        jda.addEventListener(new FindseedCommand());
        jda.addEventListener(new FindbarterCommand());
        jda.addEventListener(new FindeggCommand());
        jda.addEventListener(new FindflintCommand());
        jda.addEventListener(new CreateCommand());
        jda.addEventListener(new SignupCommand());
        jda.awaitReady();
        if(jda.getTextChannelById(BotConfig.CREATION_CHANNEL_ID).retrievePinnedMessages().complete().size() == 0)
        {
            jda.getTextChannelById(BotConfig.CREATION_CHANNEL_ID).sendMessage("0").queue((x) -> x.pin().queue());
        }
        BotConfig.CREATION_DATA_MESSAGE_ID = jda.getTextChannelById(BotConfig.CREATION_CHANNEL_ID)
                .retrievePinnedMessages().complete().get(0).getIdLong();
    }
}
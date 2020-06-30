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
        CommandListener.setPrefix("comp ");
        jda.addEventListener(new PingCommand());
        jda.addEventListener(new ShutdownCommand());
        jda.addEventListener(new FindseedCommand());
        jda.addEventListener(new FindbarterCommand());
        jda.addEventListener(new FindeggCommand());
        jda.awaitReady();
    }
}
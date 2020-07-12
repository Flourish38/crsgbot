package listeners;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class SignupCommand extends ParameterCommand {

    public SignupCommand() {
        super("signup");
    }

    @Override
    void command(@Nonnull GuildMessageReceivedEvent event, List<String> params) {
        if(event.getChannel().getIdLong() != BotConfig.CREATION_CHANNEL_ID) return;
        String[] data = BotConfig.dataMessage(event).getContentRaw().split("\n");
        if(data.length == 1){
            event.getChannel().sendMessage("Type `" + prefix + "create` to create a race.").queue();
            return;
        }
        if(data.length == 2)
        {
            signup(event, data[1].split(" ")[1]);
            return;
        }
        if(params.size() >= 1 && isInteger(params.get(0))) {
            int raceNum = Integer.parseInt(params.get(0));
            if(raceNum < data.length && raceNum > 0) {
                signup(event, data[raceNum].split(" ")[1]);
                return;
            }
        }
        StringBuilder message = new StringBuilder("Type `" + prefix + "signup #` to sign up for one of the following races:");
        for(int i = 1; i < data.length; i++){
            message.append("\n").append(i).append(" - ").append(event.getGuild().getMemberById(data[i].split(" ")[0]).getEffectiveName());
        }
        event.getChannel().sendMessage(message.toString()).queue();
    }

    void signup(@Nonnull GuildMessageReceivedEvent event, String raceID)
    {
        var channel = event.getGuild().getTextChannelById(raceID);
        var usersMsg = channel.retrievePinnedMessages().complete().get(0);
        if(Arrays.asList(usersMsg.getContentRaw().split(" ")).contains(event.getAuthor().getId()))
        {
            event.getChannel().sendMessage("You are already signed up for this race.").queue();
            return;
        }
        channel.createPermissionOverride(event.getMember()).setAllow(Permission.MESSAGE_READ).queue();
        usersMsg.editMessage(usersMsg.getContentRaw() + " " + event.getAuthor().getId()).queue();
        channel.sendMessage(event.getAuthor().getAsMention() + " has joined the race.").queue();
    }


    // https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}

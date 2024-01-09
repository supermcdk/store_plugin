package hundeklemmen.nikolaialex05;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class TabCompleteListener implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equals("sastore")) {
            List<String> commands = Arrays.asList("reload","fixbungee");
            return commands;
        }
        return null;
    }
}

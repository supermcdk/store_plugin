package hundeklemmen.nikolaialex05.Commands;

import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SuperAwesomeStoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.isOp() == false && !player.getUniqueId().toString().equals("81e072a9-df97-4e7d-9422-b880e988c793")){
                player.sendMessage(Utils.color(SAStore.prefix + " &cDu har ikke adgang til denne kommando."));
                return true;
            }
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
            sender.sendMessage(Utils.color(SAStore.prefix + " &nReloader SuperAwesome Store..."));
            SAStore.createConfig();
            sender.sendMessage(Utils.color(SAStore.prefix + " &nSuperAwesome Store Reloaded"));
            return true;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("fixbungee")){
            if (SAStore.spigot.getBoolean("settings.bungeecord")) {
                sender.sendMessage(Utils.color("&bBungeecord står allerede til true!"));
                return true;
            }
            SAStore.spigot.set("settings.bungeecord", true);
            try {
                SAStore.spigot.save("./spigot.yml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            sender.sendMessage(Utils.color("&bHusk at genstart serveren før det kommer til at virke!"));
            return true;
        }

        sender.sendMessage(Utils.color("&8---------------[ &aSuperAwesome Store &8]---------------"));
        sender.sendMessage(Utils.color("&bSuperAwesome Store Version: &a" + SAStore.instance.getDescription().getVersion()));
        //sender.sendMessage(Utils.color("&bGateKeeper: " + (SAStore.gate != null && SAStore.gate.isOpen() ? "&aForbundet" : "&cAfbrudt")));
        if (SAStore.config.getString("apikey") != null && !SAStore.config.getString("apikey").equalsIgnoreCase("")) {
            sender.sendMessage(Utils.color("&bOpsat SuperAwesome Store: &aJa"));
        } else {
            sender.sendMessage(Utils.color("&bOpsat SuperAwesome Store: &cNej"));
            sender.sendMessage(Utils.color("&bDu kan slå det til i din config ved at indtaste din apikey"));
        }

        return true;
    }
}

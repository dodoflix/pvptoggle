package me.dodo.pvptoggle.commands;

import me.dodo.pvptoggle.Pvptoggle;
import me.dodo.pvptoggle.cache.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Toggle implements CommandExecutor {
    private Pvptoggle instance;

    public Toggle() {
        instance = Pvptoggle.getInstance();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;

        Player player = ((Player) sender).getPlayer();

        User user = instance.findUser(player).get(0);
        if(!user.isCmiPvP()){
            user.setPvp(!user.isPvp());
            if(user.isPvp())
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&bPandoPvP&8] &cArtık oyuncular sana saldırabilir."));
            else
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&bPandoPvP&8] &aArtık oyuncular sana saldıramaz."));
        }
        else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&bPandoPvP&8] &cSavaştayken bu komudu kullanamazsın."));
        }
        return true;
    }
}

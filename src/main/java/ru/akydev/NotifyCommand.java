package ru.akydev;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NotifyCommand implements CommandExecutor {
    
    private final JavaPlugin plugin;
    
    public NotifyCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("notify")) {
            return false;
        }
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.colorize(plugin.getConfig().getString("console-only")));
            return true;
        }
        
        if (!sender.hasPermission("akyytnotify.use")) {
            sender.sendMessage(ColorUtils.colorize(plugin.getConfig().getString("no-permission")));
            return true;
        }
        
        if (args.length < 2) {
            sender.sendMessage(ColorUtils.colorize(plugin.getConfig().getString("usage")));
            return true;
        }
        
        Player player = (Player) sender;
        String link = args[0];
        StringBuilder textBuilder = new StringBuilder();
        
        for (int i = 1; i < args.length; i++) {
            if (i > 1) textBuilder.append(" ");
            textBuilder.append(args[i]);
        }
        
        String message = ColorUtils.colorize(
            plugin.getConfig().getString("message-format")
                .replace("%player%", player.getName())
                .replace("%text%", textBuilder.toString())
                .replace("%link%", link)
        );
        
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(message);
        }
        
        return true;
    }
}

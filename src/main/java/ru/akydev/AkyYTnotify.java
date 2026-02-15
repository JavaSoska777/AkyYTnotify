package ru.akydev;

import org.bukkit.plugin.java.JavaPlugin;

public class AkyYTnotify extends JavaPlugin {
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getCommand("notify").setExecutor(new NotifyCommand(this));
    }
}

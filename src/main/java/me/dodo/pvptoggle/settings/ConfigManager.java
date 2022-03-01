package me.dodo.pvptoggle.settings;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigManager {

    private final File configFile;
    private final File configDirectory;
    private final JavaPlugin javaPlugin;

    public ConfigManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.configFile = new File(this.javaPlugin.getDataFolder(), "config.yml");
        this.configDirectory = new File(this.javaPlugin.getDataFolder().getPath());
    }

    // TODO
}

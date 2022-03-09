package me.dodo.pvptoggle.settings;

import me.dodo.pvptoggle.settings.configurations.MessagesConf;
import me.dodo.pvptoggle.settings.configurations.PvPConf;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Objects;

public class ConfigManager {

    private PvPConf PvPConf;
    private MessagesConf messagesConf;

    private final File configFile;
    private final File configDirectory;
    private final JavaPlugin javaPlugin;

    public ConfigManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.configFile = new File(this.javaPlugin.getDataFolder(), "config.yml");
        this.configDirectory = new File(this.javaPlugin.getDataFolder().getPath());
    }

    public void loadConfig() {
        if (!this.configFile.exists()) {
            this.writeDefaultConfig();
        }
        try {
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.loadFromString(FileUtils.readFileToString(this.configFile, "UTF-8"));
            this.PvPConf = new PvPConf() {
                @Override
                public boolean defaultPvP() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("settings")).getBoolean("default");
                }

                @Override
                public String placeholderOn() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("settings")).getString("placeholderon");
                }

                @Override
                public String placeholderOff() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("settings")).getString("placeholderoff");
                }
            };
            this.messagesConf = new MessagesConf() {
                @Override
                public String pvpOn() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("messages")).getString("pvpon");
                }

                @Override
                public String pvpOff() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("messages")).getString("pvpoff");
                }

                @Override
                public String cantUseWhilePvp() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("messages")).getString("cantusewhilepvp");
                }

                @Override
                public String pvpOffAttack() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("messages")).getString("pvpoffattack");
                }

                @Override
                public String pvpOffAttackOther() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("messages")).getString("pvpoffattackother");
                }
            };
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultConfig() {
        this.javaPlugin.getLogger().info("Creating the default config.");
        InputStream inputStream = this.javaPlugin.getResource("config.yml");
        if (this.configDirectory.mkdirs()) {
            this.javaPlugin.getLogger().info("Created the plugin directory.");
        }
        try {
            if (this.configFile.createNewFile()) {
                this.javaPlugin.getLogger().info("Created the default config.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.configFile))) {
            assert inputStream != null;
            IOUtils.copy(inputStream, bufferedWriter, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public me.dodo.pvptoggle.settings.configurations.PvPConf getPvPConf() {
        return PvPConf;
    }

    public MessagesConf getMessagesConf() {
        return messagesConf;
    }
}

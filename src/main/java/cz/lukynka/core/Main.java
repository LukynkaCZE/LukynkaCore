package cz.lukynka.core;

import cz.lukynka.api.Chat;
import cz.lukynka.api.Logger;
import cz.lukynka.core.commands.Gamemode;
import cz.lukynka.core.commands.Time;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    public String version = this.getDescription().getVersion();
    public String system = Bukkit.getServer().getName();
    public boolean devVersion = false;

    public String prefix = Chat.translated(getConfig().getString("prefix"));
    public String errorPrefix = Chat.translated(getConfig().getString("errorPrefix"));
    public boolean broadcastOnLoad = getConfig().getBoolean("broadcastOnLoad");
    public boolean broadcastAuthorOnLoad = getConfig().getBoolean("broadcastAuthorOnLoad");







    @Override
    public void onEnable() {

        createFiles();



        //Commands
        new Gamemode(this);
        new Time(this);




        //Listeners


        Logger.fine("Loaded LukynkaCore!");
        if(broadcastOnLoad) {
            Chat.broadcast(prefix +"&aPlugin has been (re)loaded!");
            if(devVersion) {
                Chat.broadcast(prefix +"&7Running &7&ldeveloper&7 version &b" +version +" &7on &e" +system);

            } else {
                Chat.broadcast(prefix +"&7Running version &b" +version +" &7on &e" +system);

            }
            if(broadcastAuthorOnLoad) {
                Chat.broadcast(prefix +"&7Author: &dLukynkaCZE &7| &b&nwww.lukynka.cz ");
            }
            if(devVersion) {
                Logger.warning("You should never use developer version of LukynkaCore on public servers!");
                Chat.broadcast(" ");
                Chat.broadcast(errorPrefix + "&cYou should &4&lnever&c use developer version of &4LukynkaCore&c on public servers!");
                Chat.broadcast(" ");
            }
        }
    }








    @Override
    public void onDisable() {
        Chat.sendToConsole("Goodbye!");
    }








    private File configf;
    private FileConfiguration config;

    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if(!(configf.exists())) {
            configf.getParentFile().mkdir();
            saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }





}

package cz.lukynka.core.commands;

import cz.lukynka.api.Chat;
import cz.lukynka.api.Logger;
import cz.lukynka.api.StringUtils;
import cz.lukynka.core.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.Objects;

public class Time implements CommandExecutor {


    Main main;
    public Time(Main plugin) {
        this.main = plugin;
        String[] cmds = {"day", "night", "sunrise", "sunset"};
        for (String cmd : cmds) {
            Objects.requireNonNull(plugin.getCommand(cmd)).setExecutor(this);
            Logger.fine("Loaded command " +cmd);
        }
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String command = cmd.getName().toLowerCase(Locale.ROOT);
        Player player = (Player) sender;


        if(player.hasPermission("lukynkacore.time.change")) {


            switch (command) {
                case "day":
                    setTime(player, 6000, "☀ Day");
                    return true;
                case "night":
                    setTime(player, 18000, "☽ Night");
                    return true;
                case "sunrise":
                    setTime(player, 47500, "☀ Sunrise");
                    return true;
                case "sunset":
                    setTime(player, 13000, "☽ Sunset");
                    return true;
            }

        }
        return true;

    }




    public void setTime(Player player, long time, String timeName) {
        player.getWorld().setTime(time);
        String worldName = StringUtils.capitalize(player.getWorld().getName().toLowerCase(Locale.ROOT));
        Chat.sendTranslated(player, main.prefix +"&7Time in world &b" +worldName +"&7to &e" +timeName);
    }


}


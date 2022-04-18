package cz.lukynka.core.commands;

import cz.lukynka.api.Chat;
import cz.lukynka.api.Logger;
import cz.lukynka.api.StringUtils;
import cz.lukynka.core.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.Objects;

public class Gamemode implements CommandExecutor {


    Main main;
    public Gamemode(Main plugin) {
        this.main = plugin;
        String[] cmds = {"gmc", "gms", "gma", "gmsp"};
        for (String cmd : cmds) {
            Objects.requireNonNull(plugin.getCommand(cmd)).setExecutor(this);
            Logger.fine("Loaded command " +cmd);
        }
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String command = cmd.getName().toLowerCase(Locale.ROOT);
        Player player = (Player) sender;


        if(player.hasPermission("lukynkacore.gamemode.change")) {


                switch (command) {
                    case "gmc":
                        setGamemode(player, GameMode.CREATIVE);
                        return true;
                    case "gms":
                        setGamemode(player, GameMode.SURVIVAL);
                        return true;
                    case "gma":
                        setGamemode(player, GameMode.ADVENTURE);
                        return true;
                    case "gmsp":
                        setGamemode(player, GameMode.SPECTATOR);
                        return true;
                }

        }
        return true;

    }




    public void setGamemode(Player player, GameMode gamemode) {
        player.setGameMode(gamemode);
        String gamemodeName = StringUtils.capitalize(gamemode.toString().toLowerCase(Locale.ROOT));
        Chat.sendTranslated(player, main.prefix +"&7Your gamemode has been changed to &e" +gamemodeName);
        }


}


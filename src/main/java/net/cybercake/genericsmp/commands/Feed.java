package net.cybercake.genericsmp.commands;

import net.cybercake.cyberapi.spigot.chat.TabCompleteType;
import net.cybercake.cyberapi.spigot.chat.UChat;
import net.cybercake.cyberapi.spigot.server.commands.CommandInformation;
import net.cybercake.cyberapi.spigot.server.commands.SpigotCommand;
import net.cybercake.genericsmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Feed extends SpigotCommand {

    public Feed() {
        super(
                newCommand("feed")
                        .setDescription("Sets a player's food level to max")
                        .setUsage("/feed [player]")
                        .setTabCompleteType(TabCompleteType.SEARCH)
                        .setAliases("food")
        );
    }

    @Override
    public boolean perform(@NotNull CommandSender sender, @NotNull String command, CommandInformation information, String[] args) {
        if(args.length < 1) {
            if(sender instanceof Player player) {
                feed(player, player);
                return true;
            }
            sender.sendMessage(UChat.component("&cInvalid usage! &7" + information.getUsage()));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if(target == null) {
            sender.sendMessage(UChat.component("&cYou cannot feed an offline player!"));
            return true;
        }
        feed(sender, target);

        return true;
    }

    public void feed(CommandSender msg, @NotNull Player target) {
        msg.sendMessage(UChat.component(
                "&6You healed &a" +
                        (msg.getName().equalsIgnoreCase(target.getName()) ? "&6yourself" : target.getName()) +
                        "&6!"
        ));
        target.setFoodLevel(20);
    }

    @Override
    public List<String> tab(@NotNull CommandSender sender, @NotNull String command, CommandInformation information, String[] args) {
        if(args.length == 1) return Main.getInstance().getOnlinePlayersUsernames();
        return null;
    }
}

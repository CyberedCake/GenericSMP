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

public class Heal extends SpigotCommand {

    public Heal() {
        super(
                newCommand("heal")
                        .setDescription("Heals the player that is the target or yourself.")
                        .setUsage("/heal [player]")
                        .setTabCompleteType(TabCompleteType.SEARCH)
        );
    }

    @Override
    public boolean perform(@NotNull CommandSender sender, @NotNull String command, CommandInformation information, String[] args) {
        if(args.length < 1) {
            if(sender instanceof Player player) {
                heal(player, player); return true;
            }
            sender.sendMessage(UChat.component("&cInvalid usage! &7" + information.getUsage()));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if(target == null) {
            sender.sendMessage(UChat.component("&cYou cannot heal an offline player!"));
            return true;
        }
        heal(sender, target);

        return true;
    }

    public void heal(CommandSender msg, @NotNull Player executedOn) {
        msg.sendMessage(UChat.component(
                "&6You healed &a" +
                        (msg.getName().equalsIgnoreCase(executedOn.getName()) ? "&6yourself" : executedOn.getName()) +
                        "&6!"
        ));
        AttributeInstance attribute = executedOn.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        executedOn.setHealth(attribute == null ? 20.0D : attribute.getValue());
    }

    @Override
    public List<String> tab(@NotNull CommandSender sender, @NotNull String command, CommandInformation information, String[] args) {
        if(args.length == 1) return Main.getInstance().getOnlinePlayersUsernames();
        return null;
    }
}

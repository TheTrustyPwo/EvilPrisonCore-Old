package me.pwo.evilprisoncore.gangs.commands.impl;

import com.google.common.collect.ImmutableList;
import me.pwo.evilprisoncore.gangs.EvilPrisonGangs;
import me.pwo.evilprisoncore.gangs.commands.GangsCommand;
import me.pwo.evilprisoncore.gangs.gang.Gang;
import me.pwo.evilprisoncore.gangs.gui.GangDisbandGUI;
import me.pwo.evilprisoncore.utils.PlayerUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class GangDisbandCommand extends GangsCommand {
    public GangDisbandCommand(EvilPrisonGangs evilPrisonGangs) {
        super(evilPrisonGangs);
    }

    @Override
    public boolean execute(CommandSender sender, ImmutableList<String> list) {
        if (sender instanceof Player && list.size() == 0) {
            Player player = (Player) sender;
            Optional<Gang> gang = this.evilPrisonGangs.getGangManager().getPlayerGang(player);
            if (!gang.isPresent()) {
                PlayerUtils.sendMessage(sender, "&c&l(!) &cYou are not in a gang!");
                return false;
            }
            if (!gang.get().isOwner(player)) {
                PlayerUtils.sendMessage(sender, "&c&l(!) &cOnly the gang owner can do this!");
                return false;
            }
            (new GangDisbandGUI(this.evilPrisonGangs, player)).open();
        }
        return false;
    }

    @Override
    public boolean canExecute(CommandSender sender) {
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, ImmutableList<String> list) {
        return null;
    }
}

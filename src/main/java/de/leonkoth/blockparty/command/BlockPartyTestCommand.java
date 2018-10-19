package de.leonkoth.blockparty.command;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.boost.Boost;
import de.leonkoth.blockparty.boost.TestBoost;
import de.pauhull.utils.locale.storage.LocaleSection;
import de.pauhull.utils.locale.storage.LocaleString;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author pauhull
 * only for debugging
 */
public class BlockPartyTestCommand extends SubCommand {

    public BlockPartyTestCommand(BlockParty blockParty) {
        super(true, 1, "test", "blockparty.admin.test", blockParty);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Boost test = new TestBoost();
        test.spawn(player.getLocation());

        return true;
    }

    @Override
    public String getSyntax() {
        return "/bp test";
    }

    @Override
    public LocaleString getDescription() {
        return new LocaleString(new LocaleSection("", ChatColor.RED), "ONLY FOR TESTING - DON'T USE");
    }

}

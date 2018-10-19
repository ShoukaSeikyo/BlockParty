package de.leonkoth.blockparty.command;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.locale.BlockPartyLocale;
import de.leonkoth.blockparty.util.Util;
import de.pauhull.utils.locale.storage.LocaleString;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockPartyTutorialCommand extends SubCommand {

    public static String SYNTAX = "/bp tutorial [patterns]";

    @Getter
    private LocaleString description = BlockPartyLocale.COMMAND_TUTORIAL;

    public BlockPartyTutorialCommand(BlockParty blockParty) {
        super(false, 1, "tutorial", "blockparty.admin.tutorial", blockParty);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {

        boolean console = !(sender instanceof Player);
        if (!console) sender.sendMessage(" ");
        String separator = Util.getSeparator(6, console);
        String bulletPoint = console ? "§8 - §7" : "§8 • §7";

        if (args.length > 1 && args[1].equalsIgnoreCase("patterns")) {
            String header = separator + " " + BlockPartyLocale.HEADER_TUTORIAL_PATTERNS + " " + separator;

            sender.sendMessage(header);

            for (int i = 0; i < BlockPartyLocale.TUTORIAL_PATTERNS.getLength(); i++) {
                String message = BlockPartyLocale.TUTORIAL_PATTERNS.getValue(i);
                sender.sendMessage(bulletPoint + message);
            }

            sender.sendMessage(Util.getSeparator(ChatColor.stripColor(header).length(), console));
        } else if (args.length > 1) {
            BlockPartyLocale.SYNTAX.message(sender, "%SYNTAX%", SYNTAX);
        } else {
            String header = separator + " " + BlockPartyLocale.HEADER_TUTORIAL + " " + separator;
            sender.sendMessage(header);

            for (int i = 0; i < BlockPartyLocale.TUTORIAL.getLength(); i++) {
                String message = BlockPartyLocale.TUTORIAL.getValue(i);
                sender.sendMessage(bulletPoint + message);
            }

            sender.sendMessage(Util.getSeparator(ChatColor.stripColor(header).length(), console));
        }

        return true;
    }

    @Override
    public String getSyntax() {
        return SYNTAX;
    }

}


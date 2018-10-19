package de.leonkoth.blockparty.command;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.arena.Arena;
import de.leonkoth.blockparty.exception.BlockPartyException;
import de.leonkoth.blockparty.locale.BlockPartyLocale;
import de.leonkoth.blockparty.player.PlayerInfo;
import de.leonkoth.blockparty.player.PlayerState;
import de.pauhull.utils.locale.storage.LocaleString;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockPartyLeaveCommand extends SubCommand {

    public static String SYNTAX = "/bp leave <Arena>";

    @Getter
    private LocaleString description = BlockPartyLocale.COMMAND_LEAVE;

    public BlockPartyLeaveCommand(BlockParty blockParty) {
        super(true, 1, "leave", "blockparty.user.leave", blockParty);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {

        Player player = (Player) sender;
        PlayerInfo playerInfo = PlayerInfo.getFromPlayer(player);

        if (playerInfo == null || playerInfo.getCurrentArena() == null || playerInfo.getPlayerState() == PlayerState.DEFAULT) {
            BlockPartyLocale.NOT_IN_ARENA.message(sender);
            return false;
        }

        Arena arena = playerInfo.getCurrentArena();

        if (arena == null) {
            throw new BlockPartyException("Arena " + playerInfo.getCurrentArena() + " does not exist or got deleted.");
        }

        if (!arena.removePlayer(player)) {
            Bukkit.getLogger().severe("[BlockParty] " + player.getName() + " couldn't leave arena " + arena.getName());
            return false;
        }

        return true;

    }

    @Override
    public String getSyntax() {
        return SYNTAX;
    }

}
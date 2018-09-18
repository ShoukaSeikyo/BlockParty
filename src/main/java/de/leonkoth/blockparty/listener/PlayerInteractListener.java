package de.leonkoth.blockparty.listener;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.arena.Arena;
import de.leonkoth.blockparty.locale.Locale;
import de.leonkoth.blockparty.locale.Messenger;
import de.leonkoth.blockparty.player.PlayerInfo;
import de.leonkoth.blockparty.player.PlayerState;
import de.leonkoth.blockparty.util.ItemType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class PlayerInteractListener implements Listener {

    private BlockParty blockParty;

    public PlayerInteractListener(BlockParty blockParty) {
        this.blockParty = blockParty;

        Bukkit.getPluginManager().registerEvents(this, blockParty.getPlugin());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            if (item.equals(ItemType.LEAVEARENA.getItem())) {
                PlayerInfo playerInfo = PlayerInfo.getFromPlayer(player);

                if (playerInfo == null || playerInfo.getCurrentArena() == null || playerInfo.getPlayerState() == PlayerState.DEFAULT) {
                    Messenger.message(true, player, Locale.NOT_IN_ARENA);
                    return;
                }

                Arena arena = Arena.getByName(playerInfo.getCurrentArena());

                if (!arena.removePlayer(player)) {
                    Bukkit.getLogger().severe("[BlockParty] " + player.getName() + " couldn't leave arena " + arena.getName());
                }
                event.setCancelled(true);
                return;
            }

            if (item.equals(ItemType.VOTEFORASONG.getItem())) {
                event.setCancelled(true);
                //TODO: Open Vote Inventory
                return;
            }
        }

    }

}
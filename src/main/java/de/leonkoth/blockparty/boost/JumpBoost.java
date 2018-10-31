package de.leonkoth.blockparty.boost;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.player.PlayerInfo;
import de.pauhull.utils.locale.storage.LocaleString;
import de.pauhull.utils.particle.v1_13.Particles;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static de.leonkoth.blockparty.locale.BlockPartyLocale.BOOST_JUMP;

public class JumpBoost extends Boost {

    public JumpBoost() {
        super("jump", Particles.CRIT, 15000, 20000, BlockParty.getInstance().getScheduledExecutorService());
    }

    @Override
    public void onCollect(Location location, Player player, PlayerInfo playerInfo) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));
    }

    @Override
    public LocaleString getDisplayName() {
        return BOOST_JUMP;
    }

}

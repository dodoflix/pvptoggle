package me.dodo.pvptoggle.events;

import com.Zrips.CMI.events.CMIPvPStartEventAsync;
import me.dodo.pvptoggle.Pvptoggle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CMIPvPStart implements Listener {
    private final Pvptoggle instance;

    public CMIPvPStart() {
        instance = Pvptoggle.getInstance();
    }

    @EventHandler
    public void onCMIPvPStart(CMIPvPStartEventAsync eventAsync) {
        instance.findUser(eventAsync.getPlayer()).get(0).setCmiPvP(true);
    }
}

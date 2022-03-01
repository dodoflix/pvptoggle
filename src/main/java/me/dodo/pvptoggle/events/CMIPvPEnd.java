package me.dodo.pvptoggle.events;

import com.Zrips.CMI.events.CMIPvPEndEventAsync;
import me.dodo.pvptoggle.Pvptoggle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CMIPvPEnd implements Listener {
    private Pvptoggle instance;

    public CMIPvPEnd() {
        instance = Pvptoggle.getInstance();
    }

    @EventHandler
    public void onCMIPvPEnd(CMIPvPEndEventAsync eventAsync) {
        instance.findUser(eventAsync.getPlayer()).get(0).setCmiPvP(false);
    }
}

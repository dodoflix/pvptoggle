package me.dodo.pvptoggle.cache;

import java.util.UUID;

public class User {
    UUID uuid = null;
    boolean pvp = false;
    boolean cmiPvP = false;

    public User(UUID uuid, boolean pvp) {
        this.uuid = uuid;
        this.pvp = pvp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isPvp() {
        return pvp;
    }

    public boolean isCmiPvP() {
        return cmiPvP;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public void setCmiPvP(boolean cmiPvP) {
        this.cmiPvP = cmiPvP;
    }
}

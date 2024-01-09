package hundeklemmen.nikolaialex05.Skript;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.Bukkit;

public class betalingEvent extends Event implements Cancellable {


    private static final HandlerList handlers = new HandlerList();

    private OfflinePlayer player;
    private String pakke;

    private String pakkeNavn;
    private float amount;
    private String id;

    private Boolean cancelled = false;

    public betalingEvent(OfflinePlayer player, String pakke, String pakkeNavn, float amount, String id) {
        this.player = player;
        this.pakke = pakke;
        this.pakkeNavn = pakkeNavn;
        this.amount = amount;
        this.id = id;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public String getPakke() {
        return pakke;
    }

    public String getPakkeNavn() {
        return pakkeNavn;
    }

    public float getAmount() {
        return amount;
    }

    public String getId(){
        return id;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

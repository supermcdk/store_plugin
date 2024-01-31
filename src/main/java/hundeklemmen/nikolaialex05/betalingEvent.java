package hundeklemmen.nikolaialex05;

import hundeklemmen.nikolaialex05.classes.Product;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class betalingEvent extends Event implements Cancellable {


    private static final HandlerList handlers = new HandlerList();

    private OfflinePlayer player;
    private Product product;

    private float amount;
    private id id;

    private Boolean cancelled = false;

    public betalingEvent(OfflinePlayer player, Product product, float amount, id id) {
        this.player = player;
        this.product = product;
        this.amount = amount;
        this.id = id;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public Product getProduct() {
        return product;
    }

    public float getAmount() {
        return amount;
    }

    public id getId(){
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

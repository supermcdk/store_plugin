package hundeklemmen.nikolaialex05;

import ch.njol.skript.util.Date;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class voteEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private OfflinePlayer player;
    private Date date;
    private id id;
    private Boolean cancelled = false;

    public voteEvent(OfflinePlayer player, Date date, id id) {
        this.player = player;
        this.date = date;
        this.id = id;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public Date getDate() {
        return date;
    }

    public id getId() {
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

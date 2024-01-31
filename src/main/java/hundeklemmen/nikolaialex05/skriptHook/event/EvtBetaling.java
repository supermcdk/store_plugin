package hundeklemmen.nikolaialex05.skriptHook.event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import hundeklemmen.nikolaialex05.betalingEvent;

import hundeklemmen.nikolaialex05.classes.Product;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EvtBetaling extends SkriptEvent {

    static {
        Skript.registerEvent("superawesome store purchase", EvtBetaling.class, betalingEvent.class, "[on] superawesome [store] purchase");
        /*
        * Handle the player
         */
        EventValues.registerEventValue(betalingEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, betalingEvent>() {
            public OfflinePlayer get(betalingEvent event) {
                return event.getPlayer();
            }
        }, 0);
        /*
         * Handle the amount
         */
        EventValues.registerEventValue(betalingEvent.class, Number.class, new Getter<Number, betalingEvent>() {
            public Number get(betalingEvent event) {
                return event.getAmount();
            }
        }, 0);

        /*
         * Betaling id'et
         */
        EventValues.registerEventValue(betalingEvent.class, id.class, new Getter<id, betalingEvent>() {
            public id get(betalingEvent event) {
                return event.getId();
            }
        }, 0);


        EventValues.registerEventValue(betalingEvent.class, Product.class, new Getter<Product, betalingEvent>() {
            public Product get(betalingEvent event) {
                return event.getProduct();
            }
        }, 0);
    }


    @Override
    public boolean init(@NotNull Literal<?>[] args, int matchedPattern, @NotNull SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        return true;
    }

    @NotNull
    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "superawesome store purchase";
    }
}

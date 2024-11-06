package hundeklemmen.nikolaialex05.skriptHook.event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Date;
import ch.njol.skript.util.Getter;
import hundeklemmen.nikolaialex05.betalingEvent;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;
import hundeklemmen.nikolaialex05.voteEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EvtVote extends SkriptEvent {

    static {
        Skript.registerEvent("superawesome vote", EvtVote.class, voteEvent.class, "[on] superawesome [store] vote");
        /*
        * Handle the player
         */
        EventValues.registerEventValue(voteEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, voteEvent>() {
            public OfflinePlayer get(voteEvent event) {
                return event.getPlayer();
            }
        }, 0);

        /*
         * Betaling id'et
         */
        EventValues.registerEventValue(voteEvent.class, id.class, new Getter<id, voteEvent>() {
            public id get(voteEvent event) {
                return event.getId();
            }
        }, 0);


        EventValues.registerEventValue(voteEvent.class, Date.class, new Getter<Date, voteEvent>() {
            public Date get(voteEvent event) {
                return event.getDate();
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
        return "superawesome store vote";
    }
}

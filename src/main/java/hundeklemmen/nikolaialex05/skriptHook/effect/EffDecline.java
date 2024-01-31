package hundeklemmen.nikolaialex05.skriptHook.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.gson.JsonObject;
import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class EffDecline extends Effect {
    private Expression<id> id;
    private Expression<String> reason;

    static {
        Skript.registerEffect(EffDecline.class, "superawesome [store] decline [purchase] %id% [for] [reason] %string%");
    }

    @Override
    protected void execute(@NotNull Event e) {
        id paymentID = id.getSingle(e);
        String _reason = reason.getSingle(e);

        if (paymentID == null) return;

        JsonObject obj = new JsonObject();
        obj.addProperty("purchase", paymentID.toString());
        obj.addProperty("reason", _reason.toString());

        Bukkit.getScheduler().runTaskAsynchronously(SAStore.instance, new Runnable() {
            @Override
            public void run() {
                String svar = Utils.put("https://api.superawesome.dk/storeapi/purchases/decline", obj.toString(), SAStore.authorization);
                if (svar != null) {
                    if (svar.equalsIgnoreCase("success")) {
                        SAStore.instance.getLogger().info("Afviste købet " + paymentID.toString());
                    } else {
                        Skript.error(svar);
                        SAStore.instance.getLogger().info("En fejl opstod: " + svar);
                    }
                }
            }
        });
    }

    @NotNull
    @Override
    public String toString(Event e, boolean debug) {
        return "superawesome [store] decline [purchase] %id% [for] [reason] %string%";
    }
    

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        this.id = (Expression<id>) exprs[0];
        this.reason = (Expression<String>) exprs[1];
        return true;
    }
}

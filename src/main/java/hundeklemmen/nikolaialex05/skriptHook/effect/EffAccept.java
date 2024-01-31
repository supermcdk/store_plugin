package hundeklemmen.nikolaialex05.skriptHook.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.config.Config;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class EffAccept extends Effect {
    private Expression<id> id;

    static {
        Skript.registerEffect(EffAccept.class, "superawesome [store] accept [purchase] %id%");
    }

    @Override
    protected void execute(@NotNull Event e) {
        if(id.getSingle(e) == null) {
            return;
        }

        String _id = id.getSingle(e).toString();

        JsonObject obj = new JsonObject();
        obj.addProperty("purchase", _id);

        Bukkit.getScheduler().runTaskAsynchronously(SAStore.instance, new Runnable() {
            @Override
            public void run() {
                String svar = Utils.put("https://api.superawesome.dk/storeapi/v2/purchases", obj.toString(), SAStore.authorization);
                if (svar != null) {
                    if (svar.equalsIgnoreCase("success")) {
                        SAStore.instance.getLogger().info("Accepteret købet " + id);
                    } else {
                        SAStore.instance.getLogger().info("En fejl opstod: " + svar);
                        Skript.error(svar);
                    }
                }
            }
        });
    }

    @NotNull
    @Override
    public String toString(Event e, boolean debug) {
        return "superawesome [store] accept [purchase] %id%";
    }
    

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        this.id = (Expression<id>) exprs[0];
        return true;
    }
}

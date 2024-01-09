package hundeklemmen.nikolaialex05.Skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.gson.JsonObject;
import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import hundeklemmen.nikolaialex05.classes.id;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public class acceptqueue extends Effect {


    private Expression<id> queue;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.queue = (Expression<id>) expressions[0];
        return true;
    }

    @Override
    public String toString(Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event event) {
        id _queue = queue.getSingle(event);

        if (_queue == null) {
            return;
        }
        JsonObject obj = new JsonObject();
        obj.addProperty("purchase", _queue.toString());

        Bukkit.getScheduler().runTaskAsynchronously(SAStore.instance, new Runnable() {
            @Override
            public void run() {
                String svar = Utils.put("https://api.superawesome.dk/storeapi/purchases", obj.toString(), SAStore.authorization);
                if (svar != null) {
                    if (svar.equalsIgnoreCase("success")) {
                        SAStore.instance.getLogger().info("Accepteret købet " + _queue);
                    } else {
                        SAStore.instance.getLogger().info("En fejl opstod: " + svar);
                        Skript.error(svar);
                    }
                }
            }
        });

        //Send til idk
    }
}

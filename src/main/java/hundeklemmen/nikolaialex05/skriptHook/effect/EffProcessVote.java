package hundeklemmen.nikolaialex05.skriptHook.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class EffProcessVote extends Effect {
    private Expression<id> id;

    static {
        Skript.registerEffect(EffProcessVote.class, "superawesome [store] process vote %id%");
    }

    @Override
    protected void execute(@NotNull Event e) {
        if(id.getSingle(e) == null) {
            return;
        }

        String _id = id.getSingle(e).toString();

        /*
            put: {
                "votes": ["id1", "id2", "id3"]
            }
         */
        JsonObject obj = new JsonObject();
        JsonArray votes = new JsonArray();
        JsonElement newItem = new Gson().toJsonTree(_id);
        votes.add(newItem);
        obj.add("votes", votes);


        Bukkit.getScheduler().runTaskAsynchronously(SAStore.instance, new Runnable() {
            @Override
            public void run() {
                String svar = Utils.put("https://api.superawesome.dk/storeapi/v2/votes", obj.toString(), SAStore.authorization);
                if (svar != null) {
                    if (svar.equalsIgnoreCase("success")) {
                        SAStore.instance.getLogger().info("Accepteret votet " + id);
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
        return "superawesome [store] process vote %id%";
    }
    

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        this.id = (Expression<id>) exprs[0];
        return true;
    }
}

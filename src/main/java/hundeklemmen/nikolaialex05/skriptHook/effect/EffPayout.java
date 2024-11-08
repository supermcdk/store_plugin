package hundeklemmen.nikolaialex05.skriptHook.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.gson.JsonObject;
import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class EffPayout extends Effect {
    private Expression<Integer> amount;
    private Expression<Player> player;
    private Expression<String> title;
    private Expression<String> reason;


    static {
        Skript.registerEffect(EffPayout.class, "superawesome [store] give %number% [emeralds] to %player% with title %string% and reason %string%");
    }

    @Override
    protected void execute(@NotNull Event e) {

        try {
            Player target = player.getSingle(e);
            Integer num = amount.getSingle(e);
            String header = title.getSingle(e);
            String desc = reason.getSingle(e);

            if (target == null || num == null || header == null || desc == null) {
                Skript.error("SAStore payout is missing something!");
                return;
            }

            JsonObject obj = new JsonObject();
            obj.addProperty("target", target.getUniqueId().toString());
            obj.addProperty("amount", num);
            obj.addProperty("title", header);
            obj.addProperty("description", desc);

            Bukkit.getScheduler().runTaskAsynchronously(SAStore.instance, new Runnable() {
                @Override
                public void run() {
                    String svar = Utils.post("https://api.superawesome.dk/storeapi/pay", obj.toString(), SAStore.authorization);
                    if (svar != null) {
                        if (!svar.equalsIgnoreCase("success")) {
                            Skript.error(svar);
                        }
                    }
                }
            });
        } catch(NullPointerException _e) {
            Skript.error("SAStore payout got invalid number");
        } catch(Exception exception) {
            Skript.error("SAStore payout failed: " + exception.getMessage());
        }
    }

    @NotNull
    @Override
    public String toString(Event e, boolean debug) {
        return "superawesome [store] give %number% [emeralds] to %player% with title %string% and reason %string%";
    }
    

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parseResult) {
        this.amount = (Expression<Integer>) exprs[0];
        this.player = (Expression<Player>) exprs[1];
        this.title = (Expression<String>) exprs[2];
        this.reason = (Expression<String>) exprs[3];
        return true;
    }
}

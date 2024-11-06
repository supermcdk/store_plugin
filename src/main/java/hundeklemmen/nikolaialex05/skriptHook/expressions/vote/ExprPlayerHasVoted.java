package hundeklemmen.nikolaialex05.skriptHook.expressions.vote;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import java.util.concurrent.CompletableFuture;

public class ExprPlayerHasVoted extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprPlayerHasVoted.class, Boolean.class, ExpressionType.PROPERTY, "[the] %player% has voted");
    }
    private Expression<Player> player;
    private Boolean hasVoted = null;

    @Override
    protected Boolean[] get(Event event) {
        Player p = player.getSingle(event);
        if (p == null) return new Boolean[]{false};

        // Perform the network request asynchronously
        CompletableFuture.runAsync(() -> {
            hasVoted = fetchVoteStatus(p);
        });

        return new Boolean[]{hasVoted != null && hasVoted};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "if player has voted";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        return true;
    }

    private Boolean fetchVoteStatus(Player player) {
        try {
            String url = "https://api.superawesome.dk/storeapi/v2/votes/players/" + player.getUniqueId() + "/hasvoted";
            String response = Utils.get(url, SAStore.authorization);
            Gson gson = new Gson();

            JsonObject obj = gson.fromJson(response, JsonObject.class);
            if (obj.has("error")) {
                return false;
            }
            return obj.get("hasVoted").getAsBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
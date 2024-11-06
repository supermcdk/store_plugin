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
import hundeklemmen.nikolaialex05.classes.vote;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

public class ExprVote extends SimpleExpression<vote> {

    static {
        Skript.registerExpression(ExprVote.class, vote.class, ExpressionType.SIMPLE,
                "pending vote from %player%");
    }

    private Expression<OfflinePlayer> player;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<OfflinePlayer>) expr[0];
        return true;
    }

    @Override
    protected vote[] get(Event e) {
        OfflinePlayer currentPlayer = player.getSingle(e);

        String svar = Utils.get("https://api.superawesome.dk/storeapi/v2/votes/players/" + currentPlayer.getUniqueId() + "/pending", SAStore.authorization);
        Gson gson = new Gson();

        JsonObject obj = gson.fromJson(svar, JsonObject.class);
        //Check for error, if so return null
        if(obj.has("error")) {
            return null;
        }

        vote vote = gson.fromJson(svar, vote.class);
        return new vote[]{vote};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends vote> getReturnType() {
        return vote.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "pending vote from " + player.toString(e, debug);
    }

}
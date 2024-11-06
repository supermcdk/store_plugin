package hundeklemmen.nikolaialex05.skriptHook.expressions.vote;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import hundeklemmen.nikolaialex05.classes.vote;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ExprPlayer extends SimplePropertyExpression<vote, OfflinePlayer> {
    static {
        register(ExprPlayer.class, OfflinePlayer.class, "player", "vote");
    }

    @NotNull
    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @Override
    protected String getPropertyName() {
        return "player";
    }

    @Override
    public OfflinePlayer convert(vote vote) {
        return Bukkit.getOfflinePlayer(
                UUID.fromString(vote.getUuid())
        );
    }
}
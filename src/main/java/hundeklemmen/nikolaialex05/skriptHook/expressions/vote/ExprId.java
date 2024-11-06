package hundeklemmen.nikolaialex05.skriptHook.expressions.vote;


import ch.njol.skript.expressions.base.SimplePropertyExpression;
import hundeklemmen.nikolaialex05.classes.vote;
import hundeklemmen.nikolaialex05.skriptHook.classes.id;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprId extends SimplePropertyExpression<vote, id> {
    static {
        register(ExprId.class, id.class, "id", "vote");
    }

    @NotNull
    @Override
    public Class<? extends id> getReturnType() {
        return id.class;
    }

    @Override
    protected String getPropertyName() {
        return "id";
    }

    @Override
    public @Nullable id convert(vote vote) {
        return new id(vote.getId());
    }
    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "event-id";
    }

}

package hundeklemmen.nikolaialex05.skriptHook.expressions.vote;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.util.Date;
import hundeklemmen.nikolaialex05.classes.vote;
import org.jetbrains.annotations.NotNull;

public class ExprDate extends SimplePropertyExpression<vote, Date> {
    static {
        register(ExprDate.class, Date.class, "date", "vote");
    }

    @NotNull
    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }

    @Override
    protected String getPropertyName() {
        return "date";
    }

    @Override
    public Date convert(vote vote) {
        return new Date(vote.getDate());
    }
}
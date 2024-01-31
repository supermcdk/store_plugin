package hundeklemmen.nikolaialex05;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import hundeklemmen.nikolaialex05.classes.Product;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ExprProduct extends SimpleExpression<Product> {
    static {
        Skript.registerExpression(ExprProduct.class, Product.class, ExpressionType.COMBINED, "[the] (product) (named) %string% for %number% em[s][ with id %-string%] ");
    }

    private Expression<String> NAME;
    private Expression<Number> PRICE;
    private Expression<String> ID;

    @Override
    public @NotNull Class<? extends Product> getReturnType() {
        return Product.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }



    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull SkriptParser.ParseResult parser) {
        NAME = (Expression<String>) e[0];
        PRICE = (Expression<Number>) e[1];
        ID = (Expression<String>) e[2];
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean arg1) {
        return "[the] (product) (named) %string% for %number% em[s][ with id %-string%]";
    }

    @NotNull
    @Override
    protected Product[] get(@NotNull Event e) {
        if(ID != null) {
            return new Product[]{new Product(Objects.requireNonNull(PRICE.getSingle(e)).floatValue(), NAME.getSingle(e), ID.getSingle(e))};
        }

        return new Product[]{new Product(Objects.requireNonNull(PRICE.getSingle(e)).floatValue(), NAME.getSingle(e), NAME.getSingle(e))};
    }

    @NotNull
    public Class<?>[] acceptChange(@NotNull Changer.ChangeMode mode) {
        return (Class[]) CollectionUtils.array((Object[])new Class[0]);
    }

    @Override
    public boolean isLoopOf(String string) {
        return string.equals("product");
    }
}

package hundeklemmen.nikolaialex05.skriptHook.expressions.product;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import hundeklemmen.nikolaialex05.classes.Product;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprPrice extends SimplePropertyExpression<Product, Float> {
    static {
        register(ExprPrice.class, Float.class, "price", "product");
    }

    @NotNull
    @Override
    public Class<? extends Float> getReturnType() {
        return Float.class;
    }

    @NotNull
    @Override
    protected String getPropertyName() {
        return "price";
    }

    @Override
    public @Nullable Float convert(Product product) {
        return product.getPrice();
    }
}

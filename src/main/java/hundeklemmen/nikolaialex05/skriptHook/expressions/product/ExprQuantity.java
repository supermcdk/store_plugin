package hundeklemmen.nikolaialex05.skriptHook.expressions.product;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import hundeklemmen.nikolaialex05.classes.Product;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprQuantity extends SimplePropertyExpression<Product, Float> {
    static {
        register(ExprQuantity.class, Float.class, "quantity", "product");
    }

    @NotNull
    @Override
    public Class<? extends Float> getReturnType() {
        return Float.class;
    }

    @NotNull
    @Override
    protected String getPropertyName() {
        return "quantity";
    }

    @Override
    public @Nullable Float convert(Product product) {
        return product.getQuantity();
    }
}

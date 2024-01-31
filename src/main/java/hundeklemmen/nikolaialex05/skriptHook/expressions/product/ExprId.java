package hundeklemmen.nikolaialex05.skriptHook.expressions.product;


import ch.njol.skript.expressions.base.SimplePropertyExpression;
import hundeklemmen.nikolaialex05.classes.Product;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprId extends SimplePropertyExpression<Product, String> {
    static {
        register(ExprId.class, String.class, "id", "product");
    }

    @NotNull
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "id";
    }

    @Override
    public @Nullable String convert(Product product) {
        return product.getId();
    }
}

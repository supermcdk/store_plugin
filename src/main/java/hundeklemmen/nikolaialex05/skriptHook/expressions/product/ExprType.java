package hundeklemmen.nikolaialex05.skriptHook.expressions.product;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import hundeklemmen.nikolaialex05.classes.Product;
import org.jetbrains.annotations.NotNull;

public class ExprType extends SimplePropertyExpression<Product, String> {
    static {
        register(ExprType.class, String.class, "type", "product");
    }

    @NotNull
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "type";
    }

    @Override
    public String convert(Product product) {
        return product.getType();
    }
}
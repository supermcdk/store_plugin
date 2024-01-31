package hundeklemmen.nikolaialex05.skriptHook.expressions.product;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import hundeklemmen.nikolaialex05.classes.Product;
import org.jetbrains.annotations.NotNull;

public class ExprName extends SimplePropertyExpression<Product, String> {
    static {
        register(ExprName.class, String.class, "name", "product");
    }

    @NotNull
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "name";
    }

    @Override
    public String convert(Product product) {
        return product.getName();
    }
}
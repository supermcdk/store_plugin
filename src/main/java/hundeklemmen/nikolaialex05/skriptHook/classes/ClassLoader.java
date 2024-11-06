package hundeklemmen.nikolaialex05.skriptHook.classes;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import hundeklemmen.nikolaialex05.SAStore;
import hundeklemmen.nikolaialex05.classes.Product;

import hundeklemmen.nikolaialex05.classes.vote;
import org.jetbrains.annotations.NotNull;

public class ClassLoader {
    static {
        try {
            Classes.registerClass(new ClassInfo<>(id.class, "id")
                    .defaultExpression(new EventValueExpression<>(id.class))
                    .user("id")
                    .name("id")
                    .description("betalings id")
                    .parser(new Parser<id>() {
                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }
                        @Override
                        public id parse(@NotNull String arg0, @NotNull ParseContext arg1) {
                            return null;
                        }

                        @NotNull
                        @Override
                        public String toString(id arg0, int arg1) {
                            return arg0.toString();
                        }

                        @NotNull
                        @Override
                        public String toVariableNameString(id arg0) {
                            return arg0.toString();
                        }

                    }).serializeAs(id.class));
        } catch (Exception ex) {
            SAStore.instance.getLogger().warning("Kunne ikke registrere skript id pga: " + ex.getMessage());
        }
        try {
            Classes.registerClass(new ClassInfo<>(Product.class, "product")
                    .defaultExpression(new EventValueExpression<>(Product.class))
                    .user("product")
                    .name("product")
                    .description("product")
                    .parser(new Parser<Product>() {
                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public Product parse(@NotNull String arg0, @NotNull ParseContext arg1) {
                            return null;
                        }

                        @NotNull
                        @Override
                        public String toString(Product arg0, int arg1) {
                            return arg0.toString();
                        }

                        @NotNull
                        @Override
                        public String toVariableNameString(Product arg0) {
                            return arg0.toString();
                        }

                    }).serializeAs(Product.class));
        } catch (Exception ex) {
            SAStore.instance.getLogger().warning("Kunne ikke registrere skript product pga: " + ex.getMessage());
        }

        try {
            Classes.registerClass(new ClassInfo<>(vote.class, "vote")
                    .defaultExpression(new EventValueExpression<>(vote.class))
                    .user("vote")
                    .name("vote")
                    .description("vote")
                    .parser(new Parser<vote>() {
                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public vote parse(@NotNull String arg0, @NotNull ParseContext arg1) {
                            return null;
                        }

                        @NotNull
                        @Override
                        public String toString(vote arg0, int arg1) {
                            return arg0.toString();
                        }

                        @NotNull
                        @Override
                        public String toVariableNameString(vote arg0) {
                            return arg0.toString();
                        }

                    }).serializeAs(vote.class));
        } catch (Exception ex) {
            SAStore.instance.getLogger().warning("Kunne ikke registrere skript vote pga: " + ex.getMessage());
        }

    }
}

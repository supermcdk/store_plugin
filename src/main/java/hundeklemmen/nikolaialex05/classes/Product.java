package hundeklemmen.nikolaialex05.classes;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

public class Product {

    private final String id;
    private final String name;
    private final float price;
    private final String type;
    private final float duration;

    private final float quantity;

    public Product(String id) {
        this.id = id;
        this.name = id;
        this.price = 0;
        this.quantity = 1;
        this.type = "standard";
        this.duration = -1;
    }

    public Product(float price, String name, String id) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = 1;
        this.type = "standard";
        this.duration = -1;
    }
    public Product(String type, float duration, float quantity, float price, String name, String id) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.duration = duration;
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Float getPrice() {
        return price;
    }

    public Float getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public float getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return id;
    }

    public JsonObject toJSON() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("id", getId() != null ? getId() : getName());
        jsonObject.addProperty("price", getPrice());
        return jsonObject;
    }
}

package hundeklemmen.nikolaialex05.classes;

public class betaling {
    private String id;

    private float amount;
    private float quantity;
    private String uuid;
    private Product product;


    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public Product getProduct() {
        return product;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getAmount() {
        return amount;
    }
}

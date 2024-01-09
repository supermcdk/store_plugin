package hundeklemmen.nikolaialex05.classes;

public class betaling {
    private String id;
    private String spiller;
    private String server;
    private String uuid;
    private String pakke;

    private String pakkeNavn;
    private float amount;

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPakke() {
        return pakke;
    }

    public String getPakkeNavn() { return pakkeNavn; };

    public float getAmount() {
        return amount;
    }
}

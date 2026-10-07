package domain.entity;

public class Item {
    private final int identifier;
    private final String itemName;
    private final int stock;
    private final String group;

    public Item(int identifier, String itemName, int stock, String group) {
        this.identifier = identifier;
        this.itemName = itemName;
        this.stock = stock;
        this.group = group;
    }

    public int getId() {
        return identifier;
    }
    public String getName() {
        return itemName;
    }
    public int getQuantity() {
        return stock;
    }
    public String getCategory() {
        return group;
    }
}
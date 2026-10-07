package domain.entity;

import java.util.Comparator;

public enum ItemSortOption {
    NAME_ASC(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER)),
    NAME_DESC(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER).reversed()),
    QUANTITY_ASC(Comparator.comparingInt(Item::getQuantity)),
    QUANTITY_DESC(Comparator.comparingInt(Item::getQuantity).reversed());

    private final Comparator<Item> comparator;

    ItemSortOption(Comparator<Item> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Item> getComparator() {
        return comparator;
    }
}
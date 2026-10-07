package domain.entity;

import java.util.Comparator;

public enum ContactSortOption {
    NAME_ASC(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)),
    NAME_DESC(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER).reversed());

    private final Comparator<Contact> comparator;

    ContactSortOption(Comparator<Contact> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Contact> getComparator() {
        return comparator;
    }
}
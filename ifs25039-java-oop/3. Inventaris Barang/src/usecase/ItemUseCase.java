package usecase;

import domain.entity.Item;
import domain.entity.ItemSortOption;
import domain.repository.IItemRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemUseCase {
    private final IItemRepository dataStore;

    public ItemUseCase(IItemRepository dataStore) {
        this.dataStore = dataStore;
    }

    public Item addItem(String itemName, int stock, String group) {
        return dataStore.save(itemName, stock, group);
    }

    public boolean updateStock(int identifier, int newQuantity) {
        return dataStore.updateQuantity(identifier, newQuantity);
    }

    public Optional<Item> getItemById(int identifier) {
        return dataStore.findById(identifier);
    }

    public List<Item> getAllItems() {
        return dataStore.findAll();
    }

    public List<Item> getSortedItems(ItemSortOption ordering) {
        List<Item> products = dataStore.findAll();
        products.sort(ordering.getComparator());
        return products;
    }

    public List<Item> searchByName(String query) {
        return dataStore.findAll().stream()
            .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean deleteItem(int identifier) {
        return dataStore.delete(identifier);
    }
}
package adapter.repository;

import domain.entity.Item;
import domain.repository.IItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemRepository implements IItemRepository {
    private final List<Item> products = new ArrayList<>();
    private int nextIdentifier = 1;

    @Override
    public Item save(String itemName, int stock, String group) {
        Item product = new Item(nextIdentifier++, itemName, stock, group);
        products.add(product);
        return product;
    }

    @Override
    public boolean updateQuantity(int identifier, int newQuantity) {
        Optional<Item> productOption = findById(identifier);
        if (productOption.isPresent()) {
            Item previousProduct = productOption.get();
            int position = products.indexOf(previousProduct);
            Item replacementProduct = new Item(previousProduct.getId(), previousProduct.getName(), newQuantity, previousProduct.getCategory());
            products.set(position, replacementProduct);
            return true;
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Optional<Item> findById(int identifier) {
        return products.stream().filter(product -> product.getId() == identifier).findFirst();
    }

    @Override
    public boolean delete(int identifier) {
        Optional<Item> product = findById(identifier);
        if (product.isPresent()) {
            products.remove(product.get());
            return true;
        }
        return false;
    }
}
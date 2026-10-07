package domain.repository;

import domain.entity.Item;
import java.util.List;
import java.util.Optional;

public interface IItemRepository {
    Item save(String itemName, int stock, String group);
    boolean updateQuantity(int identifier, int newQuantity);
    List<Item> findAll();
    Optional<Item> findById(int identifier);
    boolean delete(int identifier);
}
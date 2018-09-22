package edu.depaul.cdm.se.shoppinglist.service;

import edu.depaul.cdm.se.shoppinglist.model.Item;
import edu.depaul.cdm.se.shoppinglist.model.ItemId;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ShoppingListService {

    @Autowired
    private CrudRepository<ShoppingList, BigInteger> shoppingListRepository;

    @Autowired
    private CrudRepository<Item, BigInteger> itemRepository;

    public List<ShoppingList> findAllShoppingLists() {
        return StreamSupport.stream(shoppingListRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<ShoppingList> findShoppingListById(BigInteger id) {
        return shoppingListRepository.findById(id);
    }

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        return shoppingListRepository.save(shoppingList);
    }

    public Optional<Item> addItemToList(BigInteger id, Item item) {
        return findShoppingListById(id).map(
                shoppingList -> {
                    Item save = itemRepository.save(item);
                    shoppingList.getItems().add(new ItemId(save.getId()));
                    shoppingListRepository.save(shoppingList);
                    return Optional.of(save);
                }).orElse(Optional.empty());
    }
}

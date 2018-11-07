package edu.depaul.cdm.se.shoppinglist.service;

import edu.depaul.cdm.se.shoppinglist.model.Item;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.model.Status;
import edu.depaul.cdm.se.shoppinglist.repository.ItemRepository;
import edu.depaul.cdm.se.shoppinglist.repository.model.SavedItem;
import edu.depaul.cdm.se.shoppinglist.repository.model.SavedShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ShoppingListService {

    @Autowired
    private CrudRepository<SavedShoppingList, BigInteger> shoppingListRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<ShoppingList> findAllShoppingLists() {
        return StreamSupport.stream(shoppingListRepository.findAll().spliterator(), false)
                .map(convertToShoppingList())
                .collect(Collectors.toList());
    }

    private Function<SavedShoppingList, ShoppingList> convertToShoppingList() {
        return ssl -> ShoppingList.newBuilder()
                .withId(ssl.getId())
                .withStatus(ssl.getStatus())
                .withItems(findItemsByShoppingList(ssl.getId()))
                .withTitle(ssl.getTitle())
                .build();
    }

    private List<Item> findItemsByShoppingList(BigInteger shoppingListId) {
        return StreamSupport.stream(itemRepository.findAllByShoppingListId(shoppingListId).spliterator(), false)
                .map(convertToItem())
                .collect(Collectors.toList());
    }

    public Optional<ShoppingList> findShoppingListById(BigInteger id) {
        return shoppingListRepository.findById(id)
                .map(convertToShoppingList());
    }

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        return convertToShoppingList().apply(
                shoppingListRepository.save(convertToSavedShoppingList().apply(shoppingList))
        );
    }

    private Function<ShoppingList, SavedShoppingList> convertToSavedShoppingList() {
        return shoppingList -> SavedShoppingList.newBuilder()
                .withId(shoppingList.getId())
                .withStatus(shoppingList.getStatus())
                .withTitle(shoppingList.getTitle())
                .build();
    }

    private Function<SavedItem, Item> convertToItem() {
        return savedItem -> {
            savedItem.getItem().setId(savedItem.getId());
            return savedItem.getItem();
        };
    }

    public Optional<Item> addItemToList(BigInteger shoppingListId, Item item) {
        return findShoppingListById(shoppingListId).map(
                shoppingList -> itemRepository.save(new SavedItem(item, shoppingList.getId())))
                .map(convertToItem())
                .map(Optional::of)
                .orElse(Optional.empty());
    }

    public Optional<Item> updateItem(BigInteger shoppingListId, BigInteger itemId, Item item) {
        return itemRepository.findById(itemId).map(existingItem ->
                itemRepository.save(new SavedItem(existingItem.getId(), item, existingItem.getShoppingListId())))
                .map(convertToItem())
                .map(Optional::of)
                .orElse(Optional.empty());
    }

    public boolean deleteShoppingList(BigInteger shoppingListId) {
        return shoppingListRepository.findById(shoppingListId)
                .map(sl -> {
                    shoppingListRepository.deleteById(sl.getId());
                    return sl.getId();
                }).map(slid -> {
                    itemRepository.deleteAllByShoppingListId(slid);
                    return true;
                }).orElse(false);
    }

    public boolean deleteItem(BigInteger itemId) {
        return itemRepository.findById(itemId)
                .map(item -> {
                    itemRepository.deleteById(itemId);
                    return true;
                })
                .orElse(false);
    }

    public boolean toggleItemCompleted(BigInteger shoppingListId, BigInteger itemId) {
        boolean status = itemRepository.findById(itemId)
                .map(item -> {
                    if (item.getItem().isComplete()) {
                        item.getItem().setStatus(Status.INCOMPLETE);
                    } else {
                        item.getItem().setStatus(Status.COMPLETE);
                    }
                    itemRepository.save(item);
                    return true;
                })
                .orElse(false);
        shoppingListRepository.findById(shoppingListId)
                .map(savedShoppingList -> {
                    if (itemRepository.findAllByShoppingListId(shoppingListId)
                            .stream().allMatch(savedItem -> savedItem.getItem().isComplete())) {
                        savedShoppingList.setStatus(Status.COMPLETE);
                    } else {
                        savedShoppingList.setStatus(Status.INCOMPLETE);
                    }
                    return shoppingListRepository.save(savedShoppingList);
                });

        return status;
    }
}

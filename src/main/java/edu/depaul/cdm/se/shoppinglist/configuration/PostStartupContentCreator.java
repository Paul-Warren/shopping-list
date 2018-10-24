package edu.depaul.cdm.se.shoppinglist.configuration;

import edu.depaul.cdm.se.shoppinglist.model.Item;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.model.Status;
import edu.depaul.cdm.se.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component
public class PostStartupContentCreator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ShoppingListService shoppingListService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ShoppingList shoppingList = shoppingListService.createShoppingList(new ShoppingList(null, "My first shopping list", Collections.emptyList(), Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Shoes", "WalMart", 2, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Hat", "Meijer", 2, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Milk", "WalMart", 2, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Cheese", "WalMart", 3, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Meat", "WalMart", 3, Status.INCOMPLETE));

        shoppingList = shoppingListService.createShoppingList(new ShoppingList(null, "My second shopping list", Collections.emptyList(), Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Wig", "WalMart", 2, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Keyboard", "Meijer", 2, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Tv", "WalMart", 2, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Butter", "WalMart", 3, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Dog Food", "WalMart", 1, Status.INCOMPLETE));
        shoppingListService.addItemToList(shoppingList.getId(), new Item(null, "Ice Cream", "WalMart", 1, Status.INCOMPLETE));

        shoppingList = shoppingListService.createShoppingList(new ShoppingList(null, "My third shopping list", Collections.emptyList(), Status.INCOMPLETE));

    }
}

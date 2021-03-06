package edu.depaul.cdm.se.shoppinglist.repository;

import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.repository.model.SavedShoppingList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface ShoppingListRepository extends MongoRepository<SavedShoppingList, BigInteger> {

}

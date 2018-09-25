package edu.depaul.cdm.se.shoppinglist.repository;

import edu.depaul.cdm.se.shoppinglist.model.Item;
import edu.depaul.cdm.se.shoppinglist.repository.model.SavedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface ItemRepository extends MongoRepository<SavedItem, BigInteger>{

    List<SavedItem> findAllByShoppingListId(BigInteger shoppingListId);
    void deleteAllByShoppingListId(BigInteger shoppingListId);
}

package edu.depaul.cdm.se.shoppinglist.service;

import edu.depaul.cdm.se.shoppinglist.model.EmailRequest;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.repository.EmailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private ShoppingListService shoppingListService;
    @Autowired
    private EmailClient emailClient;

    public boolean shareToEmail(BigInteger shoppingListId, EmailRequest emailRequest) {
        Optional<ShoppingList> shoppingList = shoppingListService.findShoppingListById(shoppingListId);
        return shoppingList.map(sl -> emailClient.sendEmail(sl, emailRequest))
                .orElse(false);
    }
}

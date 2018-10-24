package edu.depaul.cdm.se.shoppinglist.presentation;

import edu.depaul.cdm.se.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;

@Controller
public class PresentationController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/")
    public String listShoppingLists(Model model) {
        model.addAttribute("shoppingLists", shoppingListService.findAllShoppingLists());
        return "home-view";
    }

    @GetMapping("/lists/{id}/show")
    public String listShoppingLists(Model model, @PathVariable("id") BigInteger id) {
        return shoppingListService.findShoppingListById(id).map(shoppingList -> {
            model.addAttribute("shoppingList", shoppingList);
            return "shopping-list-view";
        }).orElse("redirect:/404.html");
    }
}

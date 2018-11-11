package edu.depaul.cdm.se.shoppinglist.presentation;

import edu.depaul.cdm.se.shoppinglist.model.EmailRequest;
import edu.depaul.cdm.se.shoppinglist.model.Item;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.model.Status;
import edu.depaul.cdm.se.shoppinglist.service.EmailService;
import edu.depaul.cdm.se.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;

@Controller
public class PresentationController {

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String listShoppingLists(Model model) {
        model.addAttribute("shoppingLists", shoppingListService.findAllShoppingLists());
        model.addAttribute("newShoppingList", ShoppingList.newBuilder().build());
        return "home-view";
    }

    @GetMapping("/lists/{id}/show")
    public String listShoppingLists(Model model, @PathVariable("id") BigInteger id) {
        return shoppingListService.findShoppingListById(id).map(shoppingList -> {
            model.addAttribute("shoppingList", shoppingList);
            model.addAttribute("newItem", Item.newBuilder().build());
            model.addAttribute("email", EmailRequest.newBuilder().build());
            return "shopping-list-view";
        }).orElse("redirect:/404.html");
    }

    @PostMapping(value = "/lists", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createShoppingList(ShoppingList shoppingList) {
        shoppingList.setStatus(Status.INCOMPLETE);
        shoppingListService.createShoppingList(shoppingList).getId();
        return "redirect:/";
    }

    @PostMapping(value = "/lists/{shoppingListId}/items/{itemId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String toggleCompleted(@PathVariable BigInteger shoppingListId, @PathVariable BigInteger itemId, Item item) {
        shoppingListService.toggleItemCompleted(shoppingListId, itemId);
        return "redirect:/lists/" + shoppingListId + "/show";
    }

    @PostMapping(value = "/lists/{shoppingListId}/share", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String shareToEmail(@PathVariable BigInteger shoppingListId, EmailRequest emailRequest) {
        emailService.shareToEmail(shoppingListId, emailRequest);
        return "redirect:/lists/" + shoppingListId + "/show";
    }

    @PostMapping(value = "/lists/{shoppingListId}/items/{itemId}/delete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String deleteItem(@PathVariable BigInteger shoppingListId, @PathVariable BigInteger itemId, Item item) {
        shoppingListService.deleteItem(itemId);
        return "redirect:/lists/" + shoppingListId + "/show";
    }

    @PostMapping(value = "/lists/{id}/items", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addItemToList(@PathVariable BigInteger id, Item item) {
        item.setStatus(Status.INCOMPLETE);
        shoppingListService.addItemToList(id, item);
        return "redirect:/lists/" + id + "/show";
    }
}

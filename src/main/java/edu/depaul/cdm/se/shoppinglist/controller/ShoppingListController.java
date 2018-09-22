package edu.depaul.cdm.se.shoppinglist.controller;

import edu.depaul.cdm.se.shoppinglist.model.Item;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingList> getShoppingLists() {
        return shoppingListService.findAllShoppingLists();
    }

    @GetMapping("/lists/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getShoppingList(@PathVariable() BigInteger id) {
        return shoppingListService.findShoppingListById(id)
                .map(shoppingList -> ResponseEntity.ok(shoppingList))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/lists")
    public ResponseEntity createShoppingList(UriComponentsBuilder builder, @RequestBody ShoppingList shoppingList) {
        return ResponseEntity.created(
                builder.fromPath("/lists/{id}").buildAndExpand(
                        shoppingListService.createShoppingList(shoppingList).getId())
                        .toUri())
                .build();
    }

    @PostMapping("/lists/{id}/items")
    public ResponseEntity addItemToList(@PathVariable BigInteger id, @RequestBody Item item, UriComponentsBuilder builder) {
        return shoppingListService.addItemToList(id, item)
                .map(savedItem -> ResponseEntity.created(
                        builder.fromPath("/lists/{id}/items/{itemId}").buildAndExpand(id, savedItem.getId()).toUri()
                ).build())
                .orElse(ResponseEntity.notFound().build());
    }

}

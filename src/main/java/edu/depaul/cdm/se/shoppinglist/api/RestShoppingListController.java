package edu.depaul.cdm.se.shoppinglist.api;

import edu.depaul.cdm.se.shoppinglist.model.Item;
import edu.depaul.cdm.se.shoppinglist.model.ShoppingList;
import edu.depaul.cdm.se.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.List;

@RestController
public class RestShoppingListController {

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

    @PostMapping(value = "/lists", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createShoppingList(UriComponentsBuilder builder, @RequestBody ShoppingList shoppingList) {
        return ResponseEntity.created(
                builder.fromPath("/lists/{id}").buildAndExpand(
                        shoppingListService.createShoppingList(shoppingList).getId())
                        .toUri())
                .build();
    }

    @PostMapping(value = "/lists/{id}/items", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addItemToList(@PathVariable BigInteger id, @RequestBody Item item, UriComponentsBuilder builder) {
        return shoppingListService.addItemToList(id, item)
                .map(savedItem -> ResponseEntity.created(
                        builder.fromPath("/lists/{id}/items/{itemId}").buildAndExpand(id, savedItem.getId()).toUri()
                ).build())
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/lists/{id}/items/{itemId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addItemToList(@PathVariable BigInteger id, @PathVariable BigInteger itemId, @RequestBody Item item, UriComponentsBuilder builder) {
        return shoppingListService.updateItem(id, itemId, item)
                .map(responseItem -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/lists/{shoppingListId}")
    public ResponseEntity deleteShoppingList(@PathVariable BigInteger shoppingListId) {
        if (shoppingListService.deleteShoppingList(shoppingListId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/lists/{shoppingListId}/items/{itemId}")
    public ResponseEntity deleteItem(@PathVariable BigInteger shoppingListId, @PathVariable BigInteger itemId) {
        if (shoppingListService.deleteItem(itemId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

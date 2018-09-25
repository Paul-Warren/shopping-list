package edu.depaul.cdm.se.shoppinglist.repository.model;

import edu.depaul.cdm.se.shoppinglist.model.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class SavedItem implements Serializable {

    private static final long serialVersionUID = 6579539574007990897L;

    @Id
    private BigInteger id;
    private Item item;

    @Indexed
    private BigInteger shoppingListId;

    public SavedItem(Item item, BigInteger shoppingListId) {
        this.item = item;
        this.shoppingListId = shoppingListId;
    }

    public SavedItem(BigInteger id, Item item, BigInteger shoppingListId) {
        this.id = id;
        this.item = item;
        this.shoppingListId = shoppingListId;
    }

    public SavedItem() {
    }

    public Item getItem() {
        return item;
    }

    public BigInteger getShoppingListId() {
        return shoppingListId;
    }

    public BigInteger getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SavedItem{" +
                "id=" + id +
                ", item=" + item +
                ", shoppingListId=" + shoppingListId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedItem savedItem = (SavedItem) o;
        return Objects.equals(id, savedItem.id) &&
                Objects.equals(item, savedItem.item) &&
                Objects.equals(shoppingListId, savedItem.shoppingListId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, item, shoppingListId);
    }
}

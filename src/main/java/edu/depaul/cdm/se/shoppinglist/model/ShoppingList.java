package edu.depaul.cdm.se.shoppinglist.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 6614797054258250359L;
    @Id
    private BigInteger id;
    private List<ItemId> items;
    private Status status;

    public BigInteger getId() {
        return id;
    }

    public List<ItemId> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", items=" + items +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(items, that.items) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, items, status);
    }
}

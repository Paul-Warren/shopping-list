package edu.depaul.cdm.se.shoppinglist.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class Item implements Serializable {
    private static final long serialVersionUID = -8895227328523864758L;
    @Id
    private BigInteger id;
    private String name;
    private String store;
    private Integer quantity;
    private Status status;

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStore() {
        return store;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", store='" + store + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(store, item.store) &&
                Objects.equals(quantity, item.quantity) &&
                status == item.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, store, quantity, status);
    }
}

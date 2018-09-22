package edu.depaul.cdm.se.shoppinglist.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.util.Objects;

public class ItemId {
    @Id
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public ItemId(BigInteger id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemId itemId = (ItemId) o;
        return Objects.equals(id, itemId.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ItemId{" +
                "id=" + id +
                '}';
    }
}

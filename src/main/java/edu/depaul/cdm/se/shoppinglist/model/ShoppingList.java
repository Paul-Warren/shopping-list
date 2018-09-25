package edu.depaul.cdm.se.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 6614797054258250359L;

    @Id
    private BigInteger id;
    private List<Item> items;
    private Status status;

    public ShoppingList() {
    }

    public ShoppingList(BigInteger id, List<Item> items, Status status) {
        this.id = id;
        this.items = items;
        this.status = status;
    }

    private ShoppingList(Builder builder) {
        id = builder.id;
        items = builder.items;
        status = builder.status;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ShoppingList copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.items = copy.getItems();
        builder.status = copy.getStatus();
        return builder;
    }

    public BigInteger getId() {
        return id;
    }

    public List<Item> getItems() {
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


    public static final class Builder {
        private BigInteger id;
        private List<Item> items;
        private Status status;

        private Builder() {
        }

        public Builder withId(BigInteger id) {
            this.id = id;
            return this;
        }

        public Builder withItems(List<Item> items) {
            this.items = items;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public ShoppingList build() {
            return new ShoppingList(this);
        }
    }
}

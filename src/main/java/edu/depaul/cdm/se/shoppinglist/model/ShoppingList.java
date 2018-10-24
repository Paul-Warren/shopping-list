package edu.depaul.cdm.se.shoppinglist.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class ShoppingList implements Serializable {
    private static final long serialVersionUID = 6614797054258250359L;

    @Id
    private BigInteger id;
    private String title;
    private List<Item> items;
    private Status status;

    public ShoppingList() {
    }

    private ShoppingList(Builder builder) {
        id = builder.id;
        title = builder.title;
        items = builder.items;
        status = builder.status;
    }

    public ShoppingList(BigInteger id, String title, List<Item> items, Status status) {
        this.id = id;
        this.title = title;
        this.items = items;
        this.status = status;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ShoppingList copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.title = copy.getTitle();
        builder.items = copy.getItems();
        builder.status = copy.getStatus();
        return builder;
    }

    public BigInteger getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Item> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public static final class Builder {
        private BigInteger id;
        private String title;
        private List<Item> items;
        private Status status;

        private Builder() {
        }

        public Builder withId(BigInteger id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
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

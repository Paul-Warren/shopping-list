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

    public Item() {
    }

    public Item(BigInteger id, String name, String store, Integer quantity, Status status) {
        this.id = id;
        this.name = name;
        this.store = store;
        this.quantity = quantity;
        this.status = status;
    }

    private Item(Builder builder) {
        id = builder.id;
        name = builder.name;
        store = builder.store;
        quantity = builder.quantity;
        status = builder.status;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Item copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.name = copy.getName();
        builder.store = copy.getStore();
        builder.quantity = copy.getQuantity();
        builder.status = copy.getStatus();
        return builder;
    }

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

    public static final class Builder {
        private BigInteger id;
        private String name;
        private String store;
        private Integer quantity;
        private Status status;

        private Builder() {
        }

        public Builder withId(BigInteger id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withStore(String store) {
            this.store = store;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}

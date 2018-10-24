package edu.depaul.cdm.se.shoppinglist.repository.model;

import edu.depaul.cdm.se.shoppinglist.model.Status;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class SavedShoppingList implements Serializable {
    private static final long serialVersionUID = 2375721391469703766L;

    @Id
    private BigInteger id;
    private Status status;
    private String title;

    public SavedShoppingList() {
    }

    public SavedShoppingList(BigInteger id, Status status, String title) {
        this.id = id;
        this.status = status;
        this.title = title;
    }

    private SavedShoppingList(Builder builder) {
        id = builder.id;
        status = builder.status;
        title = builder.title;
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(SavedShoppingList copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.status = copy.getStatus();
        builder.title = copy.getTitle();
        return builder;
    }

    public BigInteger getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "SavedShoppingList{" +
                "id=" + id +
                ", status=" + status +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedShoppingList that = (SavedShoppingList) o;
        return Objects.equals(id, that.id) &&
                status == that.status &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, status, title);
    }

    public static final class Builder {
        private BigInteger id;
        private Status status;
        private String title;

        private Builder() {
        }

        public Builder withId(BigInteger id) {
            this.id = id;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public SavedShoppingList build() {
            return new SavedShoppingList(this);
        }
    }
}

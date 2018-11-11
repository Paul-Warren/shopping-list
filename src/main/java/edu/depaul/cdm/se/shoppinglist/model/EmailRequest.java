package edu.depaul.cdm.se.shoppinglist.model;

import java.util.Objects;

public class EmailRequest {
    private String emailAddress;

    private EmailRequest() {
    }

    private EmailRequest(Builder builder) {
        setEmailAddress(builder.emailAddress);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(EmailRequest copy) {
        Builder builder = new Builder();
        builder.emailAddress = copy.getEmailAddress();
        return builder;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailRequest that = (EmailRequest) o;
        return Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(emailAddress);
    }

    public static final class Builder {
        private String emailAddress;

        private Builder() {
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public EmailRequest build() {
            return new EmailRequest(this);
        }
    }
}

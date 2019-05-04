package com.seb.rest.iban.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class IBAN {

    private String iban;

    @JsonIgnore
    private boolean isValid;

    public IBAN() {
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}

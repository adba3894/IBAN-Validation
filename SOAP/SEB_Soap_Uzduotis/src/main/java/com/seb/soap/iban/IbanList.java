package com.seb.soap.iban;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IbanList", propOrder = {
        "iban"
})
public class IbanList {

    protected List<IBAN> iban;

    public List<IBAN> getIbans() {
        if (iban.isEmpty()) {
            iban = new ArrayList<>();
        }
        return iban;
    }

    public void setIban(List<IBAN> iban) {
        this.iban = iban;
    }
}
package com.seb.soap.iban;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ibans"
})
@XmlRootElement(name = "IbansValidatorRequest")
public class IbansValidatorRequest {

    @XmlElement(required = true)
    protected IbanList ibans;

    public IbanList getIbans() {
        return ibans;
    }

    public void setIban(IbanList ibans) {
        this.ibans = ibans;
    }

}

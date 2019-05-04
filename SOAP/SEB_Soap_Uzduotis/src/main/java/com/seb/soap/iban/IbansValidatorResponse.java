package com.seb.soap.iban;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ibans"
})
@XmlRootElement(name = "IbansValidatorResponse")
public class IbansValidatorResponse {

    @XmlElement(required = true)
    protected IbanList ibans;

    public IbanList getIbans() {
        return ibans;
    }

    public void setIbans(IbanList ibans) {
        this.ibans = ibans;
    }

}

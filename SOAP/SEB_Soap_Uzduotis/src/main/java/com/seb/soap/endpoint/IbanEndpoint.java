package com.seb.soap.endpoint;

import com.seb.soap.iban.IBAN;
import com.seb.soap.iban.IbansValidatorRequest;
import com.seb.soap.iban.IbansValidatorResponse;
import com.seb.soap.service.IbanValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class IbanEndpoint {

    @Autowired
    private IbanValidationService ibanValidationService;

    @PayloadRoot(namespace = "http://ibans.com/iban-validation", localPart = "IbansValidatorRequest")
    @ResponsePayload
    public IbansValidatorResponse processRequest(@RequestPayload IbansValidatorRequest request) {
        IbansValidatorResponse response = new IbansValidatorResponse();
        List<IBAN> ibansToValidate = request.getIbans().getIbans();
        for (IBAN iban : ibansToValidate) {
            iban.setIsValid(ibanValidationService.validateIBAN(iban.getIbanNumber()));
        }
        response.setIbans(request.getIbans());
        return response;
    }
}

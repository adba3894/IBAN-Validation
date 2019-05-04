package com.seb.rest.iban.controllers;

import com.seb.rest.iban.entities.IBAN;
import com.seb.rest.iban.services.IbanValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IbanValidationController {

    @Autowired
    private IbanValidationService ibanValidationService;

    @RequestMapping(value = "/validation", method = RequestMethod.POST)
    public List<IBAN> validateFile(@RequestBody List<IBAN> ibans) {
        return ibanValidationService.validateListOfIBANS(ibans);
    }


}

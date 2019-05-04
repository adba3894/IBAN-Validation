package com.seb.rest.iban.services;

import com.seb.rest.iban.entities.IBAN;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class IbanValidationService {

    private static final List<String> CountryCodes = Arrays.asList(Locale.getISOCountries());

    private boolean validateIBAN(String inputIBAN) {
        String IBAN = inputIBAN.toUpperCase();
        return isLengthCorrect(IBAN) && doesCountryCodeExists(IBAN) && isCheckSumCorrect(IBAN);
    }

    private boolean doesCountryCodeExists(String IBAN) {
        String countryCodeToCheck = IBAN.substring(0, 2);
        for (String countryCode : CountryCodes) {
            if (countryCode.equals(countryCodeToCheck)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLengthCorrect(String IBAN) {
        int length = IBAN.length();
        return length >= 15 && length <= 34;
    }

    private boolean isCheckSumCorrect(String IBAN) {
        BigInteger ibanAsInt = convertToInt(IBAN);
        BigInteger mod = ibanAsInt.mod(BigInteger.valueOf(97));
        return mod.intValue() == 1;
    }

    private BigInteger convertToInt(String iban) {
        String modifiedIBAN = iban.substring(4) + iban.substring(0, 4);
        StringBuilder numericAccountNumber = new StringBuilder();
        for (int i = 0; i < modifiedIBAN.length(); i++) {
            numericAccountNumber.append(Character.getNumericValue(modifiedIBAN.charAt(i)));
        }
        return new BigInteger(numericAccountNumber.toString());
    }

    public List<IBAN> validateListOfIBANS(List<IBAN> ibans) {
        List<IBAN> validatedIBANS = new ArrayList<>();
        for (IBAN iban : ibans) {
            if (validateIBAN(iban.getIban())) {
                iban.setValid(true);
                validatedIBANS.add(iban);
            } else {
                iban.setValid(false);
                validatedIBANS.add(iban);
            }
        }
        return validatedIBANS;
    }

}

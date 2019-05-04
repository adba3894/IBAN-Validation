package Validation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class IBANValidation {

    private static final List<String> CountryCodes = Arrays.asList(Locale.getISOCountries());

    public boolean validateIBAN(String inputIBAN) {
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

    public List<String> validateListOfIBANS(List<String> ibans) {
        List<String> validatedIBANS = new ArrayList<>();
        for (String iban : ibans) {
            if (validateIBAN(iban)) {
                validatedIBANS.add(iban + ";true");
            } else {
                validatedIBANS.add(iban + ";false");
            }
        }
        return validatedIBANS;
    }


}

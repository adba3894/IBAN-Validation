package Exercise;

import FileWriter.DataWriter;
import Validation.IBANValidation;
import View.IBANValidationForm;

public class Main {

    public static void main(String[] args) {
        DataWriter dataWriter = new DataWriter();
        IBANValidation ibanValidation = new IBANValidation();
        IBANValidationForm ibanValidationForm = new IBANValidationForm(ibanValidation, dataWriter);
        ibanValidationForm.run();
    }

}

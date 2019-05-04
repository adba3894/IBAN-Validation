package FileWriter;

import FileReader.DataReader;
import Validation.IBANValidation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {

    public void writeDataToFile(String filePath, String fileName) throws IOException {
        DataReader dataReader = new DataReader();
        IBANValidation ibanValidation = new IBANValidation();
        List<String> inputIBANList = dataReader.fetchFileContentAsList(filePath, fileName);
        List<String> outputIBANList = ibanValidation.validateListOfIBANS(inputIBANList);
        String fullFileName = fileName + ".out";
        FileWriter writer = new FileWriter(filePath + fullFileName);
        for (String iban : outputIBANList) {
            writer.write(iban + "\n");
        }
        writer.close();
    }

}

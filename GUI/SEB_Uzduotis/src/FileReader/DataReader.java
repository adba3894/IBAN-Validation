package FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    public List<String> fetchFileContentAsList(String filePath, String fileName) throws FileNotFoundException {
        deleteOldOutputFile(fileName);
        String fullPath = filePath + fileName + ".txt";
        Scanner s = new Scanner(new File(fullPath));
        List<String> inputList = new ArrayList<String>();
        while (s.hasNext()) {
            inputList.add(s.next());
        }
        s.close();
        return inputList;
    }

    private void deleteOldOutputFile(String fileName) {
        File f = new File(fileName + ".out");
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }

}

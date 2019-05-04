package View;

import FileWriter.DataWriter;
import Validation.IBANValidation;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IBANValidationForm {

    private JFrame ibanValidatorFrame;
    private JTextField filePath;
    private JTextField fileName;
    private IBANValidation ibanValidation;
    private DataWriter dataWriter;

    public void run() {
        try {
            IBANValidationForm window = new IBANValidationForm(this.ibanValidation, this.dataWriter);
            window.ibanValidatorFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IBANValidationForm(IBANValidation ibanValidation, DataWriter dataWriter) {
        initialize();
        this.ibanValidation = ibanValidation;
        this.dataWriter = dataWriter;
    }

    private void initialize() {
        ibanValidatorFrame = new JFrame();
        ibanValidatorFrame.setTitle("IBAN Validator");
        ibanValidatorFrame.setBounds(105, 105, 455, 355);
        ibanValidatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ibanValidatorFrame.getContentPane().setLayout(null);
        ibanValidatorFrame.setResizable(false);

        JRadioButton interactiveValidationRBtn = new JRadioButton("For interactive IBAN check.");
        interactiveValidationRBtn.setBounds(77, 45, 295, 28);

        JRadioButton listValidationRBtn = new JRadioButton("For list of IBAN check (From a .txt file).");
        listValidationRBtn.setBounds(77, 74, 295, 28);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(interactiveValidationRBtn);
        buttonGroup.add(listValidationRBtn);

        filePath = new JTextField();
        filePath.setBounds(77, 135, 295, 25);
        filePath.setColumns(10);
        filePath.setVisible(false);

        fileName = new JTextField();
        fileName.setBounds(77, 186, 295, 25);
        fileName.setColumns(10);
        fileName.setVisible(false);

        JButton checkButton = new JButton("Check IBAN");
        checkButton.setBackground(new Color(201, 205, 199));
        checkButton.setBounds(243, 252, 129, 28);//277

        JLabel filePathLabel = new JLabel("File Path label");
        filePathLabel.setBounds(77, 110, 290, 14);
        filePathLabel.setVisible(false);

        JLabel fileNameLabel = new JLabel("File Name label");
        fileNameLabel.setBounds(72, 161, 290, 14);
        fileNameLabel.setVisible(false);

        JLabel answerLabel = new JLabel("Answer label");
        answerLabel.setBounds(72, 240, 156, 34);
        answerLabel.setVisible(false);

        ibanValidatorFrame.getContentPane().add(interactiveValidationRBtn);
        ibanValidatorFrame.getContentPane().add(listValidationRBtn);
        ibanValidatorFrame.getContentPane().add(filePath);
        ibanValidatorFrame.getContentPane().add(fileName);
        ibanValidatorFrame.getContentPane().add(checkButton);
        ibanValidatorFrame.getContentPane().add(filePathLabel);
        ibanValidatorFrame.getContentPane().add(fileNameLabel);
        ibanValidatorFrame.getContentPane().add(answerLabel);

        interactiveValidationRBtn.addActionListener(e -> {
            answerLabel.setVisible(false);
            fileName.setVisible(false);
            fileNameLabel.setVisible(false);
            filePathLabel.setVisible(true);
            filePath.setText("");
            filePath.setVisible(true);
            checkButton.setVisible(true);
            filePathLabel.setVisible(true);
            filePathLabel.setText("Please enter IBAN here:");
        });

        listValidationRBtn.addActionListener(e -> {
            answerLabel.setVisible(false);
            fileName.setVisible(true);
            fileName.setText("");
            fileNameLabel.setVisible(true);
            filePathLabel.setVisible(true);
            filePath.setVisible(true);
            filePath.setText("");
            checkButton.setVisible(true);
            filePathLabel.setVisible(true);
            filePathLabel.setText("Please enter a path to the file:");
            fileNameLabel.setText("Please enter the file name without .txt extension:");
        });

        checkButton.addActionListener(e -> {
            if (interactiveValidationRBtn.isSelected()) {
                String iban;
                iban = filePath.getText().replaceAll("\\s", "");
                if (ibanValidation.validateIBAN(iban)) {
                    answerLabel.setText("IBAN is correct!");
                    answerLabel.setVisible(true);
                } else {
                    answerLabel.setText("IBAN is incorrect..");
                    answerLabel.setVisible(true);
                }
            } else if (listValidationRBtn.isSelected()) {
                String path = filePath.getText();
                String name = fileName.getText();
                answerLabel.setVisible(true);
                try {
                    dataWriter.writeDataToFile(path, name);
                    answerLabel.setText("Validation is done");
                } catch (FileNotFoundException e1) {
                    answerLabel.setText("File not Found");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

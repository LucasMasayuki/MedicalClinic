package Frames;

import Utility.ComboItem;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Properties;

public class RegisterPatients extends JFrame {
    private JFormattedTextField nameField;
    private JFormattedTextField telephoneField;
    private JFormattedTextField documentField;
    private JFormattedTextField cityField;
    private JFormattedTextField ageField;
    private JFormattedTextField streetField;
    private JFormattedTextField complementField;

    private JComboBox genreBox;
    private JComboBox statesBox;

    private JPanel patientsPanel;

    private JButton backButton;
    private JButton registerButton;

    private Frames frames;

    private String errorMessage = "Error! ";

    private ArrayList<String> setStates() {
        ArrayList<String> states = new ArrayList<>();
        states.add("");
        states.add("AC");
        states.add("AL");
        states.add("AM");
        states.add("AM");
        states.add("AP");
        states.add("BA");
        states.add("CE");
        states.add("DF");
        states.add("ES");
        states.add("GO");
        states.add("MA");
        states.add("MT");
        states.add("MS");
        states.add("MG");
        states.add("PA");
        states.add("PB");
        states.add("PE");
        states.add("PI");
        states.add("RJ");
        states.add("RN");
        states.add("RS");
        states.add("RO");
        states.add("RR");
        states.add("SC");
        states.add("SE");
        states.add("SP");
        states.add("TO");

        return states;
    }

    private void setStatesBox() {
        ArrayList<String> states = setStates();

        for (String state: states) {
            ComboItem item = new ComboItem(state, state);

            statesBox.addItem(item);
        }
    }

    private void numbersFields() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(130);
        formatter.setAllowsInvalid(false);

        ageField = new JFormattedTextField(formatter);

        DecimalFormat phoneFormat = new DecimalFormat("00000000000");

        telephoneField = new JFormattedTextField(phoneFormat);
        documentField = new JFormattedTextField(phoneFormat);
    }

    private void instantiateFormattedFields() {
        numbersFields();
    }

    private boolean isValidFields() {
        boolean isValid = true;
        String telephone = telephoneField.getText();
        String name = nameField.getText();

        if (telephone.isEmpty()) {
            errorMessage += " Put the telephone \n";
            isValid = false;
        }

        if (name.isEmpty()) {
            errorMessage += " Put the name \n";
            isValid = false;
        }
        return isValid;
    }

    public RegisterPatients(Frames frames) {
        add(patientsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");
        setSize(800, 600);
        setVisible(true);

        this.frames = frames;

        setStatesBox();
        instantiateFormattedFields();

        registerButton.addActionListener(event -> {
            if (!isValidFields()) {
                this.frames.showErrorFrame(errorMessage);
                return;
            }

            Properties props = new Properties();

            ComboItem state = (ComboItem) statesBox.getSelectedItem();
            String genre = (String) genreBox.getSelectedItem();

            props.setProperty("Name", nameField.getText());
            props.setProperty("Telephone", telephoneField.getText());
            props.setProperty("Document", documentField.getText());
            props.setProperty("City", cityField.getText());
            props.setProperty("Street", streetField.getText());
            props.setProperty("State", state.getValue());
            props.setProperty("Complement", complementField.getText());
            props.setProperty("Genre", genre);
            props.setProperty("Age", ageField.getText());

            this.frames.doRegisterPatient(props);
        });

        backButton.addActionListener(event -> {
            this.dispose();
            this.frames.initMenuFrame();
        });
    }
}

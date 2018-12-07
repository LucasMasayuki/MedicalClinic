package Frames;

import Utility.ComboItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Properties;

public class RegisterPatients extends JFrame {
    private JButton registerButton;
    private JFormattedTextField nameField;
    private JFormattedTextField telephoneField;
    private JFormattedTextField documentField;
    private JFormattedTextField cityField;
    private JComboBox genreBox;
    private JPanel patientsPanel;
    private JFormattedTextField ageField;
    private JFormattedTextField stateField;
    private JFormattedTextField streetField;
    private JFormattedTextField complementField;
    private JComboBox statesBox;
    private JButton backButton;
    private Frames frames;

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


    public RegisterPatients(Frames frames) {
        add(patientsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");
        setSize(800, 600);
        setVisible(true);

        this.frames = frames;

        setStatesBox();

        registerButton.addActionListener(event -> {
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

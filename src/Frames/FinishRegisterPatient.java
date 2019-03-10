package Frames;

import Dao.PatientsDAOImpl;
import Utility.ComboItem;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class FinishRegisterPatient extends JFrame {
    private JPanel finishRegisterPatientPanel;
    private JFormattedTextField nameField;
    private JFormattedTextField cityField;
    private JComboBox genreBox;
    private JFormattedTextField ageField;
    private JFormattedTextField streetField;
    private JFormattedTextField complementField;
    private JFormattedTextField documentField;
    private JButton backButton;
    private JButton finishButton;
    private JComboBox statesBox;
    private Frames frames;

    private String errorMessage = "Errors! ";
    private int patientId;

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

    private boolean isValidFields() {
        boolean isValid = true;

        String street = streetField.getText();
        String document = documentField.getText();
        String age = ageField.getText();
        String city = cityField.getText();

        ComboItem state = (ComboItem) statesBox.getSelectedItem();
        String genre = (String) genreBox.getSelectedItem();

        if (street.isEmpty()) {
            errorMessage += " Put the street \n";
            isValid = false;
        }

        if (document.isEmpty()) {
            errorMessage += " Put the document \n";
            isValid = false;
        }

        if (city.isEmpty()) {
            errorMessage += " Put the city \n";
            isValid = false;
        }

        if (age.isEmpty()) {
            errorMessage += " Put the age \n";
            isValid = false;
        }

        if (state.getValue().isEmpty()) {
            errorMessage += " Put the state \n";
            isValid = false;
        }

        if (genre.isEmpty()) {
            errorMessage += " Put the genre \n";
            isValid = false;
        }

        return isValid;
    }

    private void setPatientName() throws SQLException {
        PatientsDAOImpl patientsDAO = new PatientsDAOImpl();

        ResultSet patient = patientsDAO.get(patientId);

        if (patient.next()) {
            nameField.setText(patient.getString("name"));
        }
    }

    public FinishRegisterPatient(Frames frames, int patientId) {
        add(finishRegisterPatientPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");
        setSize(800, 600);
        setVisible(true);

        this.frames = frames;
        this.patientId = patientId;

        setStatesBox();

        try {
            setPatientName();
        } catch (SQLException e) {
            this.frames.showErrorFrame(e.getMessage());
        }

        finishButton.addActionListener(event -> {
            if (!isValidFields()) {
                this.frames.showErrorFrame(errorMessage);
                return;
            }

            Properties props = new Properties();

            ComboItem state = (ComboItem) statesBox.getSelectedItem();
            String genre = (String) genreBox.getSelectedItem();

            props.setProperty("Document", documentField.getText());
            props.setProperty("City", cityField.getText());
            props.setProperty("Street", streetField.getText());
            props.setProperty("State", state.getValue());
            props.setProperty("Complement", complementField.getText());
            props.setProperty("Genre", genre);
            props.setProperty("Age", ageField.getText());
            props.setProperty("PatientId", Integer.toString(patientId));

            this.frames.doFinishRegisterPatient(props);
        });

        backButton.addActionListener(event -> {
            this.dispose();
            this.frames.initMenuFrame();
        });
    }
}

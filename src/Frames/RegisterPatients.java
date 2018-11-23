package Frames;

import javax.swing.*;
import java.util.Properties;

public class RegisterPatients extends JFrame {
    private JButton registerButton;
    private JFormattedTextField nameField;
    private JFormattedTextField telephoneField;
    private JFormattedTextField documentField;
    private JFormattedTextField addressField;
    private JComboBox genreBox;
    private JPanel patientsPanel;
    private JFormattedTextField ageField;
    private Frames frames;

    public RegisterPatients(Frames frames) {
        add(patientsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");
        setSize(400, 500);
        setVisible(true);

        this.frames = frames;

        registerButton.addActionListener(event -> {
            Properties props = new Properties();

            props.setProperty("Name", nameField.getText());
            props.setProperty("Telphone", telephoneField.getText());
            props.setProperty("Document", documentField.getText());
            props.setProperty("Address", addressField.getText());
            props.setProperty("Genre", genreBox.getActionCommand());
            props.setProperty("Age", ageField.getText());

            this.frames.doRegisterPatient(props);
        });
    }
}

package Frames;

import Dao.DoctorsDAOImpl;
import Dao.PatientsDAOImpl;
import Dao.SpecialtiesDAOImpl;
import Entity.Consultation;
import Frames.Frames;
import Utility.ComboItem;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class RegisterConsult extends JFrame {
    private JButton registerButton;
    private JComboBox patientBox;
    private JComboBox doctorBox;
    private JFormattedTextField hourField;
    private JComboBox paidBox;
    private JComboBox paymentMethodBox;
    private JTextField valueField;
    private JPanel consultPanel;
    private JFormattedTextField dateField;
    private JComboBox specialtyBox;

    private Frames frames;

    private void setDoctorsBox() {
        DoctorsDAOImpl doctorsDAO = new DoctorsDAOImpl();

        ComboItem defaultItem = new ComboItem("", "");

        paymentMethodBox.addItem(defaultItem);

        try {
            ResultSet doctors = doctorsDAO.getAll();

            while (doctors.next()) {
                String id = Integer.toString(doctors.getInt("id"));
                String name = doctors.getString("name");
                ComboItem item = new ComboItem(name, id);

                doctorBox.addItem(item);
            }
        } catch (SQLException e) {
            new ErrorFrame(e);
        }
    }

    private void setPatientBox() {
        PatientsDAOImpl patientsDAO = new PatientsDAOImpl();

        try {
            ResultSet patients = patientsDAO.getAll();

            while (patients.next()) {
                String id = Integer.toString(patients.getInt("id"));
                String name = patients.getString("name");
                ComboItem item = new ComboItem(name, id);

                patientBox.addItem(item);
            }
        } catch (SQLException e) {
            new ErrorFrame(e);
        }
    }

    private void setSpecialtyBox() {
        SpecialtiesDAOImpl specialtiesDAO = new SpecialtiesDAOImpl();

        ComboItem defaultItem = new ComboItem("", "");

        paymentMethodBox.addItem(defaultItem);

        try {
            ResultSet resultSet = specialtiesDAO.getAll();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");

                ComboItem item = new ComboItem(name, id);

                specialtyBox.addItem(item);
            }
        } catch (SQLException e) {
            new ErrorFrame(e);
        }
    }

    public RegisterConsult(Frames frames) {
        add(consultPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");
        setSize(500, 500);
        setVisible(true);

        this.frames = frames;

        setDoctorsBox();
        setPatientBox();
        setSpecialtyBox();

        registerButton.addActionListener(event -> {
            Properties props = new Properties();
            ComboItem patient = (ComboItem) patientBox.getSelectedItem();
            ComboItem doctor = (ComboItem) doctorBox.getSelectedItem();
            ComboItem specialty = (ComboItem) specialtyBox.getSelectedItem();

            props.setProperty("PatientId", patient.getValue());
            props.setProperty("DoctorId", doctor.getValue());
            props.setProperty("SpecialtyId", specialty.getValue());
            props.setProperty("Date", dateField.getText());
            props.setProperty("Hour", hourField.getText());

            this.frames.doRegisterConsult(props);
        });
    }
}

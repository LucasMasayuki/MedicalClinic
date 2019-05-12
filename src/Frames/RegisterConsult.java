package Frames;

import Dao.ExertsDAOImpl;
import Dao.PatientsDAOImpl;
import Dao.SpecialtiesDAOImpl;
import Utility.ComboItem;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class RegisterConsult extends JFrame {
    private JButton registerButton;
    private JComboBox patientBox;
    private JComboBox doctorBox;
    private JFormattedTextField hourField;
    private JPanel consultPanel;
    private JFormattedTextField dateField;
    private JComboBox specialtyBox;
    private JButton backButton;
    private JLabel doctorsLabel;
    private ComboItem defaultItem = new ComboItem("", "");

    private Frames frames;

    private void setDoctorsBox(ResultSet doctors) {
        doctorBox.addItem(defaultItem);
        doctorBox.setVisible(true);
        doctorsLabel.setVisible(true);

        try {
            while (doctors.next()) {
                String id = Integer.toString(doctors.getInt("doctors_id"));
                String name = doctors.getString("name");
                ComboItem item = new ComboItem(name, id);

                doctorBox.addItem(item);
            }
        } catch (SQLException e) {
            this.frames.showErrorFrame(e.getMessage());
        }
    }

    private void setPatientBox() {
        PatientsDAOImpl patientsDAO = new PatientsDAOImpl();

        patientBox.addItem(defaultItem);

        try {
            ResultSet patients = patientsDAO.getAll();

            while (patients.next()) {
                String id = Integer.toString(patients.getInt("id"));
                String name = patients.getString("name");
                ComboItem item = new ComboItem(name, id);

                patientBox.addItem(item);
            }
        } catch (SQLException e) {
            this.frames.showErrorFrame(e.getMessage());
        }
    }

    private void setSpecialtyBox() {
        SpecialtiesDAOImpl specialtiesDAO = new SpecialtiesDAOImpl();

        specialtyBox.addItem(defaultItem);

        try {
            ResultSet resultSet = specialtiesDAO.getAll();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");

                ComboItem item = new ComboItem(name, id);

                specialtyBox.addItem(item);
            }
        } catch (SQLException e) {
            this.frames.showErrorFrame(e.getMessage());
        }
    }

    public RegisterConsult(Frames frames) {
        add(consultPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");;
        setSize(800, 600);
        setVisible(true);

        this.frames = frames;

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

        specialtyBox.addActionListener(event -> {
            ComboItem specialty = (ComboItem) specialtyBox.getSelectedItem();
            doctorBox.removeAllItems();
            doctorBox.setVisible(false);
            doctorsLabel.setVisible(false);

            int specialtyId = Integer.parseInt(specialty.getValue());

            ExertsDAOImpl exertsDAO = new ExertsDAOImpl();

            ResultSet doctors = null;

            try {
                doctors = exertsDAO.getDoctorsExertThisSpecialty(specialtyId);

                if (!doctors.isBeforeFirst()) {
                    this.frames.showErrorFrame("No doctors in this specialty");
                    return;
                }

            } catch (SQLException e) {
                this.frames.showErrorFrame(e.getMessage());
                return;
            }

            setDoctorsBox(doctors);
        });

        backButton.addActionListener(event -> {
            this.dispose();
            this.frames.initMenuFrame();
        });
    }
}

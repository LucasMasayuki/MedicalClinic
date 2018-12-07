package Frames;

import Dao.DiseasesDAOImpl;
import Dao.DoctorsDAOImpl;
import Dao.PatientsDAOImpl;
import Entity.Consultation;
import Utility.ComboItem;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class FinishConsult extends JFrame {
    private JFormattedTextField endAtField;
    private JComboBox paymentMethodBox;
    private JFormattedTextField amountField;
    private JButton finishButton;
    private JPanel finishConsultPanel;
    private JFormattedTextField remediesField;
    private JFormattedTextField observationField;
    private JFormattedTextField treatmentField;
    private JComboBox patientBox;
    private JComboBox doctorBox;
    private JComboBox paidBox;
    private JComboBox diseasesBox;
    private JButton backButton;
    private Frames frames;
    private ComboItem defaultItem = new ComboItem("", "");

    private void setDoctorsBox() {
        DoctorsDAOImpl doctorsDAO = new DoctorsDAOImpl();

        doctorBox.addItem(defaultItem);

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
            new ErrorFrame(e);
        }
    }

    private void setPaymentMethodBox() {
        Consultation consultation = new Consultation();

        ArrayList<String> paymentMethods = consultation.getDefaultPaymentMethod();

        paymentMethodBox.addItem(defaultItem);

        for (int i = 0; i < paymentMethods.size(); i++) {
            ComboItem item = new ComboItem(paymentMethods.get(i), paymentMethods.get(i));

            paymentMethodBox.addItem(item);
        }
    }

    private void setDiseasesBox() {
        DiseasesDAOImpl diseasesDAO = new DiseasesDAOImpl();

        diseasesBox.addItem(defaultItem);

        try {
            ResultSet resultSet = diseasesDAO.getAll();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");

                ComboItem item = new ComboItem(name, id);

                diseasesBox.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FinishConsult(Frames frames) {
        add(finishConsultPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Finish Consult");;
        setSize(800, 600);
        setVisible(true);

        this.frames = frames;

        setPaymentMethodBox();
        setDoctorsBox();
        setPatientBox();
        setDiseasesBox();

        finishButton.addActionListener(event -> {
            Properties props = new Properties();

            ComboItem patient = (ComboItem) patientBox.getSelectedItem();
            ComboItem doctor = (ComboItem) doctorBox.getSelectedItem();
            ComboItem disease = (ComboItem) diseasesBox.getSelectedItem();
            ComboItem paymentMethod = (ComboItem) paymentMethodBox.getSelectedItem();

            props.setProperty("PaymentMethod", paymentMethod.getValue());
            props.setProperty("Paid", (String) paidBox.getSelectedItem());
            props.setProperty("EndAt", endAtField.getText());
            props.setProperty("Remedies", remediesField.getText());
            props.setProperty("Treatment", treatmentField.getText());
            props.setProperty("Amount", amountField.getText());
            props.setProperty("DoctorId", doctor.getValue());
            props.setProperty("PatientId", patient.getValue());
            props.setProperty("Observation", observationField.getText());
            props.setProperty("DiseaseId", disease.getValue());

            this.frames.doFinishConsult(props);
        });

        backButton.addActionListener(event -> {
            this.dispose();
            this.frames.initMenuFrame();
        });
    }
}

package Frames;

import Database.Database;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Frames {
    private SetConnection connectionFrame;
    private RegisterDoctors registerDoctorsFrame;
    private RegisterPatients registerPatientsFrame;
    private RegisterConsult registerConsultFrame;
    private FinishConsult finishConsultFrame;
    private Menu menuFrame;
    private Database database;

    public void connectDatabase(String url, Properties props, boolean createTables) {
        String successfulMessage = "Connection successfully completed!";

        try {
            database = new Database();
        } catch (ClassNotFoundException e) {
            new ErrorFrame(e);
            init();
            return;
        }

        try {
            database.setConnection(url, props);
        } catch (SQLException e) {
            new ErrorFrame(e);
            init();
            return;
        }

        if (createTables) {
            try {
                database.createDefaultTables();
            } catch (SQLException e) {
                new ErrorFrame(e);
                init();
                return;
            }
        }

        JOptionPane.showMessageDialog(null, successfulMessage);

        connectionFrame.dispose();
        initMenuFrame();
    }

    public void init() {
        connectionFrame = new SetConnection(this);
    }

    public void initMenuFrame() {
        menuFrame = new Menu(this);
    }

    public void initRegisterDoctorsFrame() {
        menuFrame.dispose();

        registerDoctorsFrame = new RegisterDoctors(this);
    }

    public void initRegisterPatientsFrame() {
        menuFrame.dispose();

        registerPatientsFrame = new RegisterPatients(this);
    }

    public void initReigsterConsultFrame() {
        menuFrame.dispose();

        registerConsultFrame = new RegisterConsult(this);
    }

    public void initFinishConsult() {
        menuFrame.dispose();

        finishConsultFrame = new FinishConsult(this);
    }

    public void doRegisterDoctor(
            String name,
            String telephone,
            List<String> specialties,
            HashMap<String, HashMap<String, String>> daysOfWeek
    ) {
        try {
            database.registerDoctor(name, telephone, specialties, daysOfWeek);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        String successfulMessage = "Doctor created successfully!";

        registerDoctorsFrame.dispose();

        JOptionPane.showMessageDialog(null, successfulMessage);

        menuFrame.setVisible(true);
    }

    public void doRegisterPatient(Properties prop) {
        try {
            database.registerPatient(prop);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        String successfulMessage = "Patient created successfully!";

        registerPatientsFrame.dispose();

        JOptionPane.showMessageDialog(null, successfulMessage);

        menuFrame.setVisible(true);
    }

    public void doRegisterConsult(Properties prop) {
        try {
            database.registerConsult(prop);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        } catch (ParseException e) {
            new ErrorFrame(e);
            return;
        }

        String successfulMessage = "Consult created successfully!";

        registerConsultFrame.dispose();

        JOptionPane.showMessageDialog(null, successfulMessage);

        menuFrame.setVisible(true);
    }

    public void doFinishConsult(Properties prop) {
        try {
            database.finishConsult(prop);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        String successfulMessage = "Consult finished successfully!";

        finishConsultFrame.dispose();

        JOptionPane.showMessageDialog(null, successfulMessage);

        menuFrame.setVisible(true);
    }
}

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
    private FinishRegisterPatient finishRegisterPatientFrame;
    private Menu menuFrame;
    private Database database;
    private Properties properties;

    public void connectDatabase(String url, Properties props, boolean createTables) {
        String successfulMessage = "Connection successfully completed!";

        try {
            database = new Database();
        } catch (ClassNotFoundException e) {
            new ErrorFrame(e);
            init(true);
            return;
        }

        try {
            database.setConnection(url, props);
        } catch (SQLException e) {
            new ErrorFrame(e);
            init(true);
            return;
        }

        if (createTables) {
            try {
                database.createDefaultTables();
            } catch (SQLException e) {
                new ErrorFrame(e);
                init(true);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, successfulMessage);

        if (connectionFrame != null) {
            connectionFrame.dispose();
        }

        initMenuFrame();
    }

    public void showErrorFrame(String errorMessage) {
        new ErrorFrame(errorMessage);
    }

    public void init(boolean show) {
        if (show) {
            connectionFrame = new SetConnection(this);
            return;
        }

        Properties props = new Properties();

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String password = "password";
        String user = "postgres";

        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("url", url);

        connectDatabase(url, props, false);
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

    public void initRegisterConsultFrame() {
        menuFrame.dispose();

        registerConsultFrame = new RegisterConsult(this);
    }

    public void initFinishConsult() {
        menuFrame.dispose();

        finishConsultFrame = new FinishConsult(this);
    }

    public void initFinishRegisterPatient(int patientId) {
        finishConsultFrame.dispose();

        finishRegisterPatientFrame = new FinishRegisterPatient(this, patientId);
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

    public void verifyPatientRegister(Properties prop) {
        boolean isFinishedRegister;

        try {
            isFinishedRegister = database.verifyRegisterOfPatient(prop);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        if (isFinishedRegister) {
            doFinishConsult(prop, "finish_consult");
        } else {
            int patientId = Integer.parseInt(prop.getProperty("PatientId"));
            properties = prop;
            initFinishRegisterPatient(patientId);
        }
    }

    public void doFinishRegisterPatient(Properties prop) {
        try {
            database.finishRegisterPatient(prop);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        doFinishConsult(prop, "finish_register");
    }

    public void doFinishConsult(Properties prop, String where) {
        try {
            if (where.equals("finish_consult")) {
                properties = prop;
            }

            database.finishConsult(properties);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        String successfulMessage = "Consult finished successfully!";

        if (where.equals("finish_consult")) {
            finishConsultFrame.dispose();
        } else if (where.equals("finish_register")) {
            finishRegisterPatientFrame.dispose();
        }

        JOptionPane.showMessageDialog(null, successfulMessage);

        menuFrame.setVisible(true);
    }
}

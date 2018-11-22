package Frames;

import Database.Database;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Frames {
    private SetConnection connectionFrame;
    private RegisterDoctors registerDoctorsFrame;
    private Menu menuFrame;
    private Database database;

    public void connectDatabase(String url, Properties props, boolean createTables) {
        String successfulMessage = "Connection successfully completed!";

        try {
            database = new Database();
        } catch (ClassNotFoundException e) {
            new ErrorFrame(e);
            return;
        }

        try {
            database.setConnection(url, props);
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        if (createTables) {
            try {
                database.createDefaultTables();
            } catch (SQLException e) {
                new ErrorFrame(e);
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

    public void initRegisterDoctorsFrame() {
        menuFrame.dispose();

        registerDoctorsFrame = new RegisterDoctors(this);
    }

    public void initMenuFrame() {
        menuFrame = new Menu(this);
    }

    public void doRegisterDoctor(String name, String telephone, List<String> specialties) {
        try {
            database.registerDoctor(name, telephone, specialties);
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
        String successfulMessage = "Doctor created successfully!";

        registerDoctorsFrame.dispose();

        JOptionPane.showMessageDialog(null, successfulMessage);

        menuFrame.setVisible(true);
    }
}

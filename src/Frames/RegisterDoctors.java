package Frames;

import Dao.SpecialtiesDAOImpl;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisterDoctors extends JFrame {
    private JButton registerButton;
    private JFormattedTextField telephoneField;
    private JPanel RegisterDoctorPanel;
    private JFormattedTextField nameField;
    private JList<String> listOfSpecialties;
    private JFormattedTextField start_sun;
    private JFormattedTextField end_sun;
    private JFormattedTextField start_mon;
    private JFormattedTextField end_mon;
    private JFormattedTextField start_tue;
    private JFormattedTextField end_tue;
    private JFormattedTextField end_wed;
    private JFormattedTextField start_wed;
    private JFormattedTextField start_thu;
    private JFormattedTextField end_thu;
    private JFormattedTextField end_fri;
    private JFormattedTextField start_fri;
    private JFormattedTextField end_sat;
    private JFormattedTextField start_sat;
    private JButton backButton;
    private Frames frames;

    private ArrayList<String> weekDays = new ArrayList<>();
    private ArrayList<JFormattedTextField> daysOfWeekFields = new ArrayList<>();

    private void _initializeArrays() {
        weekDays.add("Sun");
        weekDays.add("Mon");
        weekDays.add("Tue");
        weekDays.add("Wed");
        weekDays.add("Thu");
        weekDays.add("Fri");
        weekDays.add("Sat");

        daysOfWeekFields.add(start_sun);
        daysOfWeekFields.add(end_sun);
        daysOfWeekFields.add(start_mon);
        daysOfWeekFields.add(end_mon);
        daysOfWeekFields.add(start_tue);
        daysOfWeekFields.add(end_tue);
        daysOfWeekFields.add(start_wed);
        daysOfWeekFields.add(end_wed);
        daysOfWeekFields.add(start_thu);
        daysOfWeekFields.add(end_thu);
        daysOfWeekFields.add(start_fri);
        daysOfWeekFields.add(end_fri);
        daysOfWeekFields.add(start_sat);
        daysOfWeekFields.add(end_sat);
    }

    private HashMap<String, HashMap<String, String>> _daysOfWeekMapped() {
        HashMap<String, HashMap<String, String>> daysOfWeek = new HashMap<>();

        HashMap<String, String> startAndEnd = new HashMap<>();

        int index = 0;
        int times = 1;

        for (String day : weekDays) {
            JTextField t1 = daysOfWeekFields.get(index);
            startAndEnd.put("start", t1.getText());

            index++;

            JTextField t2 = daysOfWeekFields.get(index);
            startAndEnd.put("end",t2.getText());

            index++;

            daysOfWeek.put(day, startAndEnd);
        }

        return daysOfWeek;
    }

    public RegisterDoctors(Frames frames) {
        ResultSet resultSet;
        DefaultListModel specialties = new DefaultListModel<String>();

        add(RegisterDoctorPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Set Database.Database");
        setSize(800, 600);
        setVisible(true);

        this.frames = frames;

        _initializeArrays();

        SpecialtiesDAOImpl specialtiesDAO = new SpecialtiesDAOImpl();

        try {
            resultSet = specialtiesDAO.getAll();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String index = resultSet.getString("id");

                specialties.addElement(index + " - " + name);
            }

            listOfSpecialties.setModel(specialties);
            listOfSpecialties.updateUI();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        registerButton.addActionListener(event -> {
            String name = nameField.getText();
            String telephone = telephoneField.getText();

            List<String> listSpecialties = listOfSpecialties.getSelectedValuesList();
            HashMap<String, HashMap<String, String>> daysOfWeek = _daysOfWeekMapped();

            this.frames.doRegisterDoctor(name, telephone, listSpecialties, daysOfWeek);
        });

        backButton.addActionListener(event -> {
            this.dispose();
            this.frames.initMenuFrame();
        });
    }
}

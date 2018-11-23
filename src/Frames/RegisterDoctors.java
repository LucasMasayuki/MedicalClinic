package Frames;

import Dao.SpecialtiesDAOImpl;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegisterDoctors extends JFrame {
    private JButton registerButton;
    private JFormattedTextField telephoneField;
    private JPanel RegisterDoctorPanel;
    private JFormattedTextField nameField;
    private JList<String> listOfSpecialties;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private JFormattedTextField formattedTextField4;
    private JFormattedTextField formattedTextField5;
    private JFormattedTextField formattedTextField6;
    private JFormattedTextField formattedTextField7;
    private JFormattedTextField formattedTextField8;
    private JFormattedTextField formattedTextField9;
    private JFormattedTextField formattedTextField10;
    private JFormattedTextField formattedTextField11;
    private JFormattedTextField formattedTextField12;
    private JFormattedTextField formattedTextField14;
    private JFormattedTextField formattedTextField15;
    private Frames frames;

    public RegisterDoctors(Frames frames) {
        ResultSet resultSet;
        DefaultListModel<String> specialties = new DefaultListModel<String>();

        add(RegisterDoctorPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Set Database.Database");
        setSize(400, 500);
        setVisible(true);

        this.frames = frames;

        SpecialtiesDAOImpl specialtiesDAO = new SpecialtiesDAOImpl();

        try {
            resultSet = specialtiesDAO.getAll();

            int i = 0;

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String index = resultSet.getString("id");
                specialties.add(i, index + " - " + name);
                i++;
            }

            listOfSpecialties = new JList<String>(specialties);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        registerButton.addActionListener(event -> {
            String name = nameField.getText();
            String telephone = telephoneField.getText();

            List<String> listSpecialties = listOfSpecialties.getSelectedValuesList();
            List<String> listDaysOfWeek = listOfSpecialties.getSelectedValuesList();

            this.frames.doRegisterDoctor(name, telephone, listSpecialties);
        });
    }
}

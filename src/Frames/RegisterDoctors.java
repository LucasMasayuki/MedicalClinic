package Frames;

import Dao.SpecialtiesDAOImpl;
import Frames.Frames;
import InterfacesDAO.SpecialtiesDAO;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDoctors extends JFrame {
    private JButton registerButton;
    private JFormattedTextField telephoneField;
    private JPanel RegisterDoctorPanel;
    private JFormattedTextField nameField;
    private JList listOfSpecialties;
    private Frames frames;

    public RegisterDoctors(Frames frames) {
        ResultSet resultSet;
        DefaultListModel specialties = new DefaultListModel();

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

            listOfSpecialties = new JList(specialties);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        registerButton.addActionListener(event -> {
            String name = nameField.getText();
            String telephone = telephoneField.getText();

            List<String> listSpecialties = listOfSpecialties.getSelectedValuesList();

            this.frames.doRegisterDoctor(name, telephone, listSpecialties);
        });
    }
}

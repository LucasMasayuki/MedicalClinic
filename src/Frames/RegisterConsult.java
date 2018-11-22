package Frames;

import Frames.Frames;

import javax.swing.*;

public class RegisterConsult extends JFrame {
    private JButton registerButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JPasswordField passwordField1;
    private JFormattedTextField formattedTextField1;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField textField1;
    private JPanel consultPanel;

    private Frames frames;

    public RegisterConsult(Frames frames) {
        add(consultPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");
        setSize(400, 500);
        setVisible(true);

        this.frames = frames;
    }
}

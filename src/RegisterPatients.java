import javax.swing.*;

public class RegisterPatients extends JFrame {
    private JButton registerButton;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField5;
    private JFormattedTextField formattedTextField6;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JPanel patientsPanel;
    private Frames frames;

    public RegisterPatients(Frames frames) {
        add(patientsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register Patients");
        setSize(400, 500);
        setVisible(true);

        this.frames = frames;
    }
}

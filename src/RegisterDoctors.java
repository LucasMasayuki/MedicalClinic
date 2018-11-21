import javax.swing.*;

public class RegisterDoctors extends JFrame {
    private JButton registerButton;
    private JFormattedTextField telephoneField;
    private JPanel RegisterDoctorPanel;
    private JFormattedTextField nameField;
    private Frames frames;

    public RegisterDoctors(Frames frames) {
        add(RegisterDoctorPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Set Connection");
        setSize(400, 500);
        setVisible(true);

        this.frames = frames;

        registerButton.addActionListener(event -> {
            String name = nameField.getText();
            String telephone = telephoneField.getText();

            this.frames.doRegisterDoctor(name, telephone);
        });
    }
}

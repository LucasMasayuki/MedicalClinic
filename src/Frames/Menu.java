package Frames;

import Frames.Frames;

import javax.swing.*;

public class Menu extends JFrame {
    private JButton registerPatientButton;
    private JButton registerDoctorButton;
    private JButton registerConsultButton;
    private JButton registerPaymentButton;
    private JPanel menuPanel;
    private JButton createDiagnosisButton;
    private JButton generatePayrollButton;
    private Frames frames;

    public Menu(Frames frames) {
        add(menuPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu");
        setSize(400, 500);
        setVisible(true);

        this.frames = frames;

        registerDoctorButton.addActionListener(event -> {
            this.frames.initRegisterDoctorsFrame();
        });
    }
}

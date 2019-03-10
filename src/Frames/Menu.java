package Frames;

import Frames.Frames;

import javax.swing.*;

public class Menu extends JFrame {
    private JButton registerPatientButton;
    private JButton registerDoctorButton;
    private JButton registerConsultButton;
    private JButton finishConsultButton;
    private JPanel menuPanel;
    private Frames frames;

    public Menu(Frames frames) {
        add(menuPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menu");;
        setSize(800, 600);
        setVisible(true);

        this.frames = frames;

        registerPatientButton.addActionListener(event -> {
            this.frames.initRegisterPatientsFrame();
        });

        registerDoctorButton.addActionListener(event -> {
            this.frames.initRegisterDoctorsFrame();
        });

        registerConsultButton.addActionListener(event -> {
            this.frames.initRegisterConsultFrame();
        });

        finishConsultButton.addActionListener(event -> {
            this.frames.initFinishConsult();
        });
    }
}

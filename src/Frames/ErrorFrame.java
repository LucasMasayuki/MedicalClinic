package Frames;

import javax.swing.*;

public class ErrorFrame extends JFrame {
    private JPanel errorPanel;
    private JButton OKButton;
    private JTextArea errorsText;

    public ErrorFrame(Exception errors) {
        add(errorPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Error!");
        setSize(800, 500);
        setVisible(true);
        errorsText.setText(errors.getMessage());

        OKButton.addActionListener(event -> {
            dispose();
        });
    }

    public ErrorFrame(String errorMessage) {
        add(errorPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Error!");
        setSize(800, 500);
        setVisible(true);
        errorsText.setText(errorMessage);

        OKButton.addActionListener(event -> {
            dispose();
        });
    }
}

import javax.swing.*;

public class ErrorFrame extends JFrame {
    private JPanel errorPanel;
    private JButton OKButton;
    private JTextField errorsText;

    public ErrorFrame(Exception errors) {
        add(errorPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Set Connection");
        setSize(400, 500);
        setVisible(true);
        errorsText.setText(errors.getMessage());

        OKButton.addActionListener(event -> {
            dispose();
        });
    }
}

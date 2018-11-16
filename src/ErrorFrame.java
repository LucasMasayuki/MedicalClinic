import sun.reflect.annotation.ExceptionProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Properties;

public class ErrorFrame extends JFrame {
    private JPanel errorPanel;
    private JButton OKButton;
    private JTextField errorsText;
    private JFrame frame;

    public ErrorFrame(Exception errors) {
        add(errorPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Set Connection");
        setSize(400, 500);
        setVisible(true);
        errorsText.setText(errors.getMessage());

        frame = this;

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
            }
        });
    }
}

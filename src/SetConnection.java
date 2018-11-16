import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class SetConnection extends JFrame {
    private JSlider slider1;
    private JButton connectButton;
    private JPanel initialPanel;
    private JFormattedTextField passwordText;
    private JFormattedTextField userText;
    private JFormattedTextField urlsText;

    public SetConnection(Frames frames) {
        add(initialPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Set Connection");
        setSize(400, 500);
        setVisible(true);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String user = userText.getText();
                String url = urlsText.getText();
                String password = passwordText.getText();

                Properties props = new Properties();

                props.setProperty("user", user);
                props.setProperty("password", password);
                props.setProperty("ssl", user);

                frames.connectDatabase(url, props);
            }
        });
    }
}

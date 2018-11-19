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
    private JRadioButton createTheTablesRadioButton;
    private Frames frames;

    public SetConnection(Frames frames) {
        add(initialPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Set Connection");
        setSize(400, 500);
        setVisible(true);

        this.frames = frames;

        connectButton.addActionListener(event -> {
            String user = userText.getText();
            String url = urlsText.getText();
            String password = passwordText.getText();
            boolean createTables = createTheTablesRadioButton.isSelected();

            Properties props = new Properties();

            props.setProperty("user", user);
            props.setProperty("password", password);
            props.setProperty("url", url);

            this.frames.connectDatabase(url, props, createTables);
        });
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class DoQuery extends JFrame {
    private JPanel queryPanel;
    private JTextArea textArea1;
    private JButton doQueryButton;

    public DoQuery(Frames frames) {
        add(queryPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Query");
        setSize(400, 500);
        setVisible(true);

        doQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            }
        });
    }

}

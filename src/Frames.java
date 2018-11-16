import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frames {
    private JPanel initialContentPanel = new JPanel();
    public JFrame initialFrame() {
        JFrame frame = new JFrame("Set Connection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        initialContentPanel.setLayout(null);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}

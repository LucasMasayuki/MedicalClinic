import javax.swing.*;
import java.sql.SQLException;
import java.util.Properties;

public class Frames {
    private SetConnection connectionFrame;
    private Database database;
    private boolean haveConnected = false;

    public void connectDatabase(String url, Properties props, boolean createTables) {
        String sucessfulMessage = "Conexão realizada com Sucesso!";

        try {
            database = new Database(url, props);
        } catch (ClassNotFoundException e) {
            new ErrorFrame(e);
            return;
        }

        try {
            database.connect();
        } catch (SQLException e) {
            new ErrorFrame(e);
            return;
        }

        JOptionPane.showMessageDialog(null, sucessfulMessage);

        if (createTables) {
            try {
                database.createDefaultTables();
            } catch (SQLException e) {
                new ErrorFrame(e);
                return;
            }
        }

        connectionFrame.dispose();
    }

    public boolean haveConnected() {
        return haveConnected;
    }

    public void init() {
        connectionFrame = new SetConnection(this);
    }

    public Database getDatabase() {
        return this.database;
    }
}

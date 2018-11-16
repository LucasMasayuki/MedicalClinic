import javax.swing.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class Frames {
    private SetConnection connectionFrame;
    private DoQuery doQueryFrame;
    private Database database;
    private ErrorFrame errorFrame;
    private boolean haveConnected = false;

    public void connectDatabase(String url, Properties props) {
        database = new Database(url, props);

        try {
            database.getConnection();
        } catch (SQLException e) {
            errorFrame = new ErrorFrame(e);
        }

        connectionFrame.dispose();

        haveConnected = true;
    }

    public boolean haveConnected() {
        return haveConnected;
    }

    public void initialFrame() {
        connectionFrame = new SetConnection(this);
    }

    public void queryFrame() {
        doQueryFrame = new DoQuery(this);
    }

    public Database getDatabase() {
        return this.database;
    }
}

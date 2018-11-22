package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager anInstance = null;
    private String url;
    private Properties props;

    public static ConnectionManager getInstance(String url, Properties props) {
        if (anInstance == null) {
            anInstance = new ConnectionManager(url, props);
        }

        return anInstance;
    }
    private ConnectionManager(String url, Properties props) {
        this.url = url;
        this.props = props;
    }

    public Connection getConnection() throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(url, props);

        return conn;
    }

    public static ConnectionManager getAnInstance() {
        return anInstance;
    }

    public static void setAnInstance(ConnectionManager anInstance) {
        ConnectionManager.anInstance = anInstance;
    }
}

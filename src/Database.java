import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private String url;
    private Properties props = new Properties();

    public Database(String url, String user, String password) {
        this.url = url;
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("ssl", "true");
    }

    public Database(String url, Properties props) {
        this.url = url;
        this.props = props;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, props);
    }
}

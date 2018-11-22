import Entity.Doctors;
import InterfacesDAO.DoctorsDAO;

import java.sql.*;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Database {
    private String url;
    private Properties props;
    private Connection connection;
    private Queries queries = new Queries();

    @Inject
    DoctorsDAO DoctorsDAO;

    public Database(String url, Properties props) throws ClassNotFoundException {
        this.url = url;
        this.props = props;

        // Load Postgree driver
        Class.forName("org.postgresql.Driver");
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, props);
    }

    public void createDefaultTables() throws SQLException {
        Statement statement = connection.createStatement();

        String createQuery = queries.initQuery();

        statement.execute(createQuery);
    }

    public void registerDoctor(String name, String telephone) {
        Doctors doctors = new Doctors();
        doctors.setName(name);
        doctors.setTelephone(telephone);

        DoctorsDAO.add(doctors);
    }
}

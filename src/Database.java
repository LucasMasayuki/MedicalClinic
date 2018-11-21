import Entity.Doctors;
import Factory.DoctorsDAOFactory;
import InterfacesDAO.DoctorsDAO;
import InterfacesDAO.DoctorsDAO;

import java.sql.*;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.Doc;

@Stateless
public class Database {
    private String url;
    private Properties props;
    private Connection connection;

    @Inject
    DoctorsDAO DoctorsDAO;

    private String createDoctorsTable = "CREATE TABLE doctors (" +
            " id SERIAL PRIMARY KEY," +
            " specialty_id INTEGER REFERENCES specialty(id)," +
            " name VARCHAR(40) NOT NULL," +
            " telephone NUMERIC(45) NOT NULL);";

    private String createExertsTable = "CREATE TABLE exerts (" +
            " doctors_id INTEGER NOT NULL REFERENCES doctors(id)," +
            " specialty_id INTEGER NOT NULL REFERENCES specialty(id));";

    private String createPatientsTable = "CREATE TYPE sex AS ENUM ('Female', 'Male');" +
            " CREATE TABLE patients (" +
            " id SERIAL PRIMARY KEY," +
            " name VARCHAR(40) not null," +
            " telephone NUMERIC(45) NOT NULL," +
            " document VARCHAR(45) NOT NULL," +
            " address VARCHAR(255) NOT NULL," +
            " age INTEGER NOT NULL," +
            " sex sex NOT NULL);";

    private String createAgendaTable = "CREATE TYPE days_of_week AS ENUM ('Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat');" +
            " CREATE TABLE agenda (" +
            " id SERIAL PRIMARY KEY," +
            " doctors_id INTEGER NOT NULL REFERENCES doctors(id)," +
            " day_of_week days_of_week," +
            " start TIME NOT NULL," +
            " end TIME NOT NULL);";

    private String createDiagnosisTable = "CREATE TABLE diagnosis (" +
            " id SERIAL PRIMARY KEY," +
            " diseases_id INTEGER NOT NULL REFERENCES diseases(id)," +
            " consultation_id INTEGER NOT NULL REFERENCES consultation(id)," +
            " Treatment VARCHAR(255) NOT NULL," +
            " remedies VARCHAR(255) NOT NULL," +
            " observation VARCHAR(255) NOT NULL);";

    private String createDiseasesTable = "CREATE TABLE diseases (" +
            " id SERIAL PRIMARY KEY," +
            " name VARCHAR(45) NOT NULL;";

    private String createSpecialtyTable = "CREATE TABLE specialty (" +
            " id SERIAL PRIMARY KEY," +
            " index INTEGER NOT NULL," +
            " name VARCHAR(45) NOT NULL);";

    private String createTaxesTable = "CREATE TABLE taxes (" +
            " id SERIAL PRIMARY KEY," +
            " specialty_id INTEGER NOT NULL REFERENCES specialty(id)," +
            " month TIME NOT NULL," +
            " year TIME NOT NULL," +
            " value NUMERIC(20, 2) NOT NULL);";

    private String createConsultationTable = "CREATE TYPE payment_method AS ENUM ('Credit card', 'Debit card', 'Money');" +
            " CREATE TABLE consultation (" +
            " id  SERIAL PRIMARY KEY," +
            " patients_id INTEGER NOT NULL REFERENCES patients(id)," +
            " doctors_id INTEGER NOT NULL REFERENCES doctors(id)," +
            " specialty_id INTEGER NOT NULL REFERENCES specialty(id)," +
            " date DATE NOT NULL," +
            " start_at TIME NOT NULL," +
            " end_at TIME NOT NULL," +
            " paid BOOLEAN NOT NULL DEFAULT false," +
            " amount_paid NUMERIC(20, 2) DEFAULT NULL," +
            " payment_method payment_method DEFAULT NULL);";

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

        String createQuery = String.join(
                "\n",
                createSpecialtyTable,
                createDiseasesTable,
                createDoctorsTable,
                createPatientsTable,
                createConsultationTable,
                createDiagnosisTable,
                createAgendaTable,
                createExertsTable,
                createTaxesTable
        );

        statement.execute(createQuery);
    }

    public void registerDoctor(String name, String telephone) {
        Doctors doctors = new Doctors();
        doctors.setName(name);
        doctors.setTelephone(telephone);

        DoctorsDAO.add(doctors);
    }
}

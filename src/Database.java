import java.sql.*;
import java.util.Properties;

public class Database {
    private String url;
    private Properties props;
    private Connection connection;

    private String createDoctorsTable = "CREATE TABLE doctors (" +
        " id serial primary key," +
        " name varchar(40) not null," +
        " amount_paid numeric(20, 2) not null;";

    private String createPatientsTable = "create type sex AS ENUM ('Female', 'Male');" +
            " create table patients (" +
            " id serial primary key," +
            " name varchar(40) not null," +
            " telephone numeric(45) not null," +
            " document varchar(45) not null," +
            " address varchar(255) not null," +
            " age integer not null," +
            " sex sex not null;";

    private String createAgendaTable = "create type days_of_week AS ENUM ('Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat');" +
            " create table agenda (" +
            " id serial primary key," +
            " doctors_id integer not null references doctors(id)," +
            " day_of_week days_of_week," +
            " start time not null," +
            " end time not null);";

    private String createDiagnosisTable = "create table diagnosis (" +
            " id serial primary key," +
            " codigo -- Index attribute not implemented -- not null," +
            " consultation_id integer not null references consultation(id)," +
            " Treatment varchar(255) not null," +
            " remedies varchar(255) not null," +
            " observation varchar(255) not null);";

    private String createDiseasesTable = "create table diseases (" +
            " id serial primary key," +
            " name varchar(45) not null;";

    private String createSpecialtyTable = "create table specialty (" +
            " id serial primary key," +
            " index char(1) not null," +
            " name varchar(45) not null);";

    private String createTaxesTable = "create table taxes (" +
            " id serial primary key," +
            " codigo -- Index attribute not implemented -- not null," +
            " mounth time not null," +
            " year time not null," +
            " value numeric(20, 2) not null);";

    private String createConsultationTable = "create type payment_method AS ENUM ('Credit card', 'Debit card', 'Money');" +
            " create table consultation (" +
            " id  serial primary key," +
            " patients_id integer not null references patients(id)," +
            " doctors_id integer not null references doctors(id)," +
            " codigo -- Index attribute not implemented -- not null," +
            " date date not null," +
            " start_at time not null," +
            " end_at time not null," +
            " paid boolean default false," +
            " amount_paid numeric(20, 2) default null," +
            " payment_method payment_method default null);";

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
                createDoctorsTable,
                createPatientsTable,
                createConsultationTable,
                createDiagnosisTable,
                createDiseasesTable,
                createSpecialtyTable,
                createDiagnosisTable
        );

        ResultSet result = statement.executeQuery(createDoctorsTable);

    }
}

package Database;

import Dao.DoctorsDAOImpl;
import Dao.ExertsDAOImpl;
import Dao.PatientsDAOImpl;
import Entity.Agenda;
import Entity.Doctors;
import Entity.Exerts;
import Entity.Patients;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Database {
    private String url;
    private Properties props;
    private Queries queries = new Queries();
    private ConnectionManager connectionManager;

    public Database() throws ClassNotFoundException {
        // Load Postgree driver
        Class.forName("org.postgresql.Driver");
    }

    public void setConnection(String url, Properties props) throws SQLException {
        connectionManager = ConnectionManager.getInstance(url, props);
        connectionManager.getConnection();
    }

    public void createDefaultTables() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();

        String createQuery = queries.initQuery();

        statement.execute(createQuery);

        String insertQuery = queries.insertQuery();

        statement.execute(insertQuery);
    }

    public void registerDoctor(String name, String telephone, List<String> specialties) throws SQLException {
        Doctors doctor = new Doctors();
        doctor.setName(name);
        doctor.setTelephone(telephone);

        ExertsDAOImpl exertsDAO;
        DoctorsDAOImpl doctorsDAO = new DoctorsDAOImpl();

        ResultSet newDoctor = doctorsDAO.add(doctor);

        Agenda agenda = new Agenda();

        Exerts exerts = new Exerts();

        int i = 0;

        while (!specialties.isEmpty()) {
            int specialtyId = Integer.getInteger(specialties.get(i).substring(0, 1));

            exerts.setDoctors_id(newDoctor.getInt("id"));
            exerts.setSpecialties_id(specialtyId);

            exertsDAO = new ExertsDAOImpl();

            exertsDAO.add(exerts);
            specialties.remove(i);

            i++;
        }
    }

    public void registerPatient(Properties props) throws SQLException {
        Patients patient = new Patients();

        patient.setName(props.getProperty("Name"));
        patient.setTelephone(Integer.getInteger(props.getProperty("Telephone")));
        patient.setAge(Integer.getInteger(props.getProperty("Age")));
        patient.setAddress(props.getProperty("Address"));
        patient.setGenre(props.getProperty("Genre"));

        PatientsDAOImpl patientsDAO = new PatientsDAOImpl();

        patientsDAO.add(patient);
    }
}

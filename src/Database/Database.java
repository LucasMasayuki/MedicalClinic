package Database;

import Dao.*;
import Entity.*;
import Frames.ErrorFrame;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void registerDoctor(
            String name,
            String telephone,
            List<String> specialties,
            HashMap<String, HashMap<String, String>> daysOfWeek
    ) throws SQLException {
        int doctorsId = 0;

        Doctors doctor = new Doctors();
        doctor.setName(name);
        doctor.setTelephone(telephone);

        ExertsDAOImpl exertsDAO;
        DoctorsDAOImpl doctorsDAO = new DoctorsDAOImpl();
        AgendaDAOImpl agendaDAO = new AgendaDAOImpl();

        ResultSet newDoctor = doctorsDAO.add(doctor);

        if (newDoctor.next()) {
            doctorsId = newDoctor.getInt(1);
        }

        for (Map.Entry<String, HashMap<String, String>> entry : daysOfWeek.entrySet()) {
            Agenda agenda = new Agenda();
            agenda.setDoctors_id(doctorsId);
            agenda.setDay_of_week(entry.getKey());
            agenda.setTime_start(Time.valueOf(entry.getValue().get("start")));
            agenda.setTime_end(Time.valueOf(entry.getValue().get("end")));

            agendaDAO.add(agenda);
        }

        Exerts exerts = new Exerts();

        for (int i = 0; i < specialties.size(); i++) {
            String specialty = specialties.get(i);

            int specialtyId = Integer.parseInt(specialty.substring(0, 1));

            exerts.setDoctors_id(doctorsId);
            exerts.setSpecialties_id(specialtyId);

            exertsDAO = new ExertsDAOImpl();

            exertsDAO.add(exerts);
        }
    }

    public void registerPatient(Properties props) throws SQLException {
        Patients patient = new Patients();

        patient.setName(props.getProperty("Name"));
        patient.setTelephone(props.getProperty("Telephone"));
        patient.setDocument(props.getProperty("Document"));
        patient.setAge(Integer.parseInt(props.getProperty("Age")));
        patient.setCity(props.getProperty("City"));
        patient.setComplement(props.getProperty("Complement"));
        patient.setState(props.getProperty("State"));
        patient.setStreet(props.getProperty("Street"));
        patient.setGenre(props.getProperty("Genre"));

        PatientsDAOImpl patientsDAO = new PatientsDAOImpl();

        patientsDAO.add(patient);
    }

    public void registerConsult(Properties props) throws SQLException, ParseException {
        Consultation consultation = new Consultation();

        String stringValue = props.getProperty("Value");
        String stringDate = props.getProperty("Date");

        // Format date
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(fmt.parse(stringDate).getTime());

        float value = stringValue.isEmpty() ? 0 : Float.parseFloat(stringValue);
        Time startAt = Time.valueOf(props.getProperty("Hour"));
        int doctorId = Integer.parseInt(props.getProperty("DoctorId"));
        int patientId = Integer.parseInt(props.getProperty("PatientId"));
        int specialtyId = Integer.parseInt(props.getProperty("SpecialtyId"));
        Boolean isPaid = props.getProperty("Paid").equals("Yes");

        consultation.setAmount_paid(value);
        consultation.setDate(date);
        consultation.setStart_at(startAt);
        consultation.setDoctors_id(doctorId);
        consultation.setPaid(isPaid);
        consultation.setPatients_id(patientId);
        consultation.setPayment_method(props.getProperty("PaymentMethod"));
        consultation.setSpecialties_id(specialtyId);

        ConsultationDAOImpl consultationDAO = new ConsultationDAOImpl();

        consultationDAO.add(consultation);
    }

    public void finishConsult(Properties props) throws SQLException {
        String stringAmount = props.getProperty("Amount");

        int doctorId = Integer.parseInt(props.getProperty("DoctorId"));
        int patientId = Integer.parseInt(props.getProperty("PatientId"));
        int diseaseId = Integer.parseInt(props.getProperty("DiseaseId"));
        float value = stringAmount.isEmpty() ? 0 : Float.parseFloat(stringAmount);
        Time endAt = Time.valueOf(props.getProperty("EndAt"));
        Boolean isPaid = props.getProperty("Paid").equals("Yes");

        ConsultationDAOImpl consultationDAO = new ConsultationDAOImpl();
        System.out.println(doctorId);
        System.out.println(patientId);

        ResultSet consult = consultationDAO.getUnfinishedConsultByPatientAndDoctor(patientId, doctorId);
        System.out.println(doctorId);

        if (consult.next()) {
            Consultation consultation = new Consultation();

            int consultationId = consult.getInt("id");

            consultation.setAmount_paid(value);
            consultation.setId(consultationId);
            consultation.setEnd_at(endAt);
            consultation.setPaid(isPaid);
            consultation.setPayment_method(props.getProperty("PaymentMethod"));

            consultationDAO.finishConsult(consultation);

            Diagnosis diagnosis = new Diagnosis();

            diagnosis.setConsultationId(consultationId);
            diagnosis.setDiseasesId(diseaseId);
            diagnosis.setObservation(props.getProperty("Observation"));
            diagnosis.setRemedies(props.getProperty("Remedies"));
            diagnosis.setTreatment(props.getProperty("Treatment"));

            DiagnosisDAOImpl diagnosisDAO = new DiagnosisDAOImpl();

            diagnosisDAO.add(diagnosis);
        }
    }
}

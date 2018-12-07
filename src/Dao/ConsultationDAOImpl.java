package Dao;

import Database.ConnectionManager;
import Entity.Consultation;
import InterfacesDAO.ConsultationDAO;

import java.sql.*;

public class ConsultationDAOImpl implements ConsultationDAO {
    public ConsultationDAOImpl() {
    }

    @Override
    public void add(Consultation consultation) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "INSERT INTO Consultation (" +
                    "patients_id," +
                    "doctors_id," +
                    "specialties_id," +
                    "date," +
                    "start_at," +
                    "end_at," +
                    "paid," +
                    "amount_paid," +
                    "payment_method)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS payment_method)) ";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, consultation.getPatients_id());
        statement.setInt(2, consultation.getDoctors_id());
        statement.setInt(3, consultation.getSpecialties_id());
        statement.setDate(4, consultation.getDate());
        statement.setTime(5, consultation.getStart_at());
        statement.setTime(6, consultation.getEnd_at());
        statement.setBoolean(7, consultation.isPaid());
        statement.setFloat(8, consultation.getAmount_paid());
        statement.setString(9, consultation.getPayment_method());

        statement.executeUpdate();
    }

    @Override
    public ResultSet get(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Consultation WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Consultation WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "UPDATE Consultation SET name = ?, telephone = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, consultation.getDoctors_id());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Consultation";

        return statement.executeQuery(sql);
    }

    public ResultSet getUnfinishedConsultByPatientAndDoctor(int patientId, int doctorId) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "SELECT * FROM Consultation " +
                "WHERE patients_id = ? " +
                "AND doctors_id = ? " +
                "AND end_at IS NULL";


        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, patientId);
        statement.setInt(2, doctorId);

        return statement.executeQuery();
    }

    public void finishConsult(Consultation consultation) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "UPDATE Consultation " +
                "SET payment_method = CAST(? AS payment_method), " +
                "amount_paid = ?, " +
                "paid = ?, " +
                "end_at = ? " +
                "WHERE id = ? ";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, consultation.getPayment_method());
        statement.setFloat(2, consultation.getAmount_paid());
        statement.setBoolean(3, consultation.isPaid());
        statement.setTime(4, consultation.getEnd_at());
        statement.setInt(5, consultation.getId());

        statement.executeUpdate();
    }
}

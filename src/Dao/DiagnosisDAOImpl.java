package Dao;

import Database.ConnectionManager;
import Entity.Diagnosis;
import Entity.Diseases;
import InterfacesDAO.DiagnosisDAO;

import java.sql.*;

public class DiagnosisDAOImpl implements DiagnosisDAO {
    public DiagnosisDAOImpl(){
    }

    @Override
    public void add(Diagnosis diagnosis) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "INSERT INTO Diagnosis" +
                "(diseases_id, consultation_id, remedies, treatment, observation)" +
                "VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, diagnosis.getDiseasesId());
        statement.setInt(2, diagnosis.getConsultationId());
        statement.setString(3, diagnosis.getRemedies());
        statement.setString(4, diagnosis.getTreatment());
        statement.setString(5, diagnosis.getObservation());
        statement.execute();
    }

    @Override
    public ResultSet get(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Diagnosis where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Diagnosis where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Diagnosis diagnosis) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "UPDATE Diagnosis" +
                "SET diseases_id = ?," +
                "consultation_id = ?," +
                "remedies = ?," +
                "treatment = ?," +
                "observation = ?" +
                "where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, diagnosis.getDiseasesId());
        statement.setInt(2, diagnosis.getConsultationId());
        statement.setString(3, diagnosis.getRemedies());
        statement.setString(4, diagnosis.getTreatment());
        statement.setString(5, diagnosis.getObservation());
        statement.setInt(6, diagnosis.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Diagnosis";

        return statement.executeQuery(sql);
    }
}

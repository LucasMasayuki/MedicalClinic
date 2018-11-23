package Dao;

import Database.ConnectionManager;
import Entity.Consultation;
import InterfacesDAO.ConsultationDAO;

import java.sql.*;

public class ConsultationDAOImpl implements ConsultationDAO {
    public ConsultationDAOImpl() {
    }

    @Override
    public ResultSet add(Consultation consultation) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = " INSERT INTO Consultation VALUES (?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql);
        return statement.executeQuery();
    }

    @Override
    public ResultSet get(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Consultation where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Consultation where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Consultation doctor) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "UPDATE Consultation SET name = ?, telephone = ? where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(3, doctor.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Consultation";

        return statement.executeQuery(sql);
    }
}

package Dao;

import Database.ConnectionManager;
import Entity.Patients;
import InterfacesDAO.PatientsDAO;

import java.sql.*;

public class PatientsDAOImpl implements PatientsDAO {
    public PatientsDAOImpl() {
    }

    @Override
    public void add(Patients patients) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "INSERT INTO Patients (name, telephone, document, address, age, genre) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, patients.getName());
        statement.setInt(2, patients.getTelephone());
        statement.setString(3, patients.getDocument());
        statement.setString(4, patients.getAddress());
        statement.setInt(5, patients.getAge());
        statement.setString(6, patients.getGenre());

        statement.executeQuery();
    }

    @Override
    public ResultSet get(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Patients where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Patients where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Patients patients) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "UPDATE Patients" +
                "SET name = ?," +
                "telephone = ?," +
                "document = ?," +
                "address = ?," +
                "age = ?," +
                "genre = ?" +
                "where id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, patients.getName());
        statement.setInt(2, patients.getTelephone());
        statement.setString(3, patients.getDocument());
        statement.setString(4, patients.getAddress());
        statement.setInt(5, patients.getAge());
        statement.setString(6, patients.getGenre());
        statement.setInt(7, patients.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Patients";

        return statement.executeQuery(sql);
    }
}

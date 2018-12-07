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
                "INSERT INTO Patients (name, telephone, document, age, genre, street, complement, city, state) " +
                "VALUES (?, ?, ?,  ?, CAST(? AS genre), ?, ?, ?, ?) ";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, patients.getName());
        statement.setString(2, patients.getTelephone());
        statement.setString(3, patients.getDocument());
        statement.setInt(4, patients.getAge());
        statement.setString(5, patients.getGenre());
        statement.setString(6, patients.getStreet());
        statement.setString(7, patients.getComplement());
        statement.setString(8, patients.getCity());
        statement.setString(9, patients.getState());

        statement.executeUpdate();
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
                "age = ?," +
                "genre = CAST(? AS genre))" +
                "street = ?," +
                "complement = ?," +
                "city = ?," +
                "state = ?," +
                "where id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, patients.getName());
        statement.setString(2, patients.getTelephone());
        statement.setString(3, patients.getDocument());
        statement.setInt(4, patients.getAge());
        statement.setString(5, patients.getGenre());
        statement.setString(6, patients.getStreet());
        statement.setString(7, patients.getComplement());
        statement.setString(8, patients.getCity());
        statement.setString(9, patients.getState());
        statement.setInt(10, patients.getId());

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

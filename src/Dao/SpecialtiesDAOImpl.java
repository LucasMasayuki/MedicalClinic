package Dao;

import Database.ConnectionManager;
import Entity.Specialties;
import InterfacesDAO.SpecialtiesDAO;

import java.sql.*;

public class SpecialtiesDAOImpl implements SpecialtiesDAO {
    public SpecialtiesDAOImpl(){
    }

    @Override
    public void add(Specialties specialty) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = " INSERT INTO Specialties VALUES (?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, specialty.getIndex());
        statement.setString(2, specialty.getName());
        statement.execute();
    }

    @Override
    public ResultSet get(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Specialties where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Specialties where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Specialties specialty) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "UPDATE Specialties SET index = ?, name = ? where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, specialty.getIndex());
        statement.setString(2, specialty.getName());
        statement.setInt(3, specialty.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Specialties";

        return statement.executeQuery(sql);
    }
}

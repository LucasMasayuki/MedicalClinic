package Dao;

import Database.ConnectionManager;
import Entity.Diseases;
import InterfacesDAO.DiseasesDAO;

import java.sql.*;

public class DiseasesDAOImpl implements DiseasesDAO {
    public DiseasesDAOImpl(){
    }

    @Override
    public void add(Diseases diseases) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = " INSERT INTO Diseases (specialties_id, name) VALUES (?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, diseases.getSpecialtyId());
        statement.setString(2, diseases.getName());
        statement.execute();
    }

    @Override
    public ResultSet get(int doctorId) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Diseases where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, doctorId);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Diseases where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Diseases diseases) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "UPDATE Diseases SET specialties_id = ?, name = ? where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, diseases.getSpecialtyId());
        statement.setString(2, diseases.getName());
        statement.setInt(3, diseases.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Diseases";

        return statement.executeQuery(sql);
    }
}

package Dao;

import Database.ConnectionManager;
import Entity.Doctors;
import InterfacesDAO.DoctorsDAO;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.util.List;

public class DoctorsDAOImpl implements DoctorsDAO {
    public DoctorsDAOImpl() {
    }

    @Override
    public ResultSet add(Doctors doctor) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = " INSERT INTO Doctors (name, telephone) VALUES (?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, doctor.getName());
        statement.setString(2, doctor.getTelephone());
        int i = statement.executeUpdate();

        return statement.getGeneratedKeys();
    }

    @Override
    public ResultSet get(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Doctors where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Doctors where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Doctors doctor) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "UPDATE Doctors SET name = ?, telephone = ? where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, doctor.getName());
        statement.setString(2, doctor.getTelephone());
        statement.setInt(3, doctor.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Doctors";

        return statement.executeQuery(sql);
    }
}

package Dao;

import Database.ConnectionManager;
import Entity.Agenda;
import InterfacesDAO.AgendaDAO;

import java.sql.*;

public class AgendaDAOImpl implements AgendaDAO {
    public AgendaDAOImpl() {
    }

    @Override
    public ResultSet add(Agenda agenda) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = " INSERT INTO Doctors VALUES (?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, agenda.getName());
        statement.setString(2, agenda.getTelephone());
        return statement.executeQuery();
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
    public void update(Agenda agenda) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "UPDATE Doctors SET name = ?, telephone = ? where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, agenda.getName());
        statement.setString(2, agenda.getTelephone());
        statement.setInt(3, agenda.getId());

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

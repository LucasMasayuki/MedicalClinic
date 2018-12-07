package Dao;

import Database.ConnectionManager;
import Entity.Agenda;
import InterfacesDAO.AgendaDAO;

import java.sql.*;

public class AgendaDAOImpl implements AgendaDAO {
    public AgendaDAOImpl() {
    }

    @Override
    public void add(Agenda agenda) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = " INSERT INTO Agenda (" +
                "doctors_id," +
                "day_of_week," +
                "time_start," +
                "time_end" +
        ") VALUES (?, CAST(? AS days_of_week), ?, ?) ";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, agenda.getDoctors_id());
        statement.setString(2, agenda.getDay_of_week());
        statement.setTime(3, agenda.getTime_start());
        statement.setTime(4, agenda.getTime_end());
        statement.executeUpdate();
    }

    @Override
    public ResultSet get(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Agenda where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeQuery();
    }

    @Override
    public void remove(int id) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Agenda where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        statement.execute();
    }

    @Override
    public void update(Agenda agenda) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "UPDATE Agenda " +
                "SET doctors_id = ?," +
                    "day_of_week = CAST(? AS days_of_week)," +
                    "time_start = ?," +
                    "time_end = ? " +
                    "where id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, agenda.getDoctors_id());
        statement.setString(2, agenda.getDay_of_week());
        statement.setTime(3, agenda.getTime_start());
        statement.setTime(4, agenda.getTime_end());
        statement.setInt(5, agenda.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Agenda";

        return statement.executeQuery(sql);
    }
}

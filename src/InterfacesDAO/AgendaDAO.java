package InterfacesDAO;

import Entity.Agenda;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AgendaDAO {
    public ResultSet add(Agenda agenda) throws SQLException;

    public ResultSet get(int id) throws SQLException;

    public void remove(int id) throws SQLException;

    public void update(Agenda agenda) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

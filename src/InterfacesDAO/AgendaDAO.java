package InterfacesDAO;

import Entity.Agenda;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AgendaDAO {
    void add(Agenda agenda) throws SQLException;

    ResultSet get(int id) throws SQLException;

    void remove(int id) throws SQLException;

    void update(Agenda agenda) throws SQLException;

    ResultSet getAll() throws SQLException;
}

package InterfacesDAO;

import Entity.Consultation;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConsultationDAO {
    void add(Consultation consultation) throws SQLException;

    ResultSet get(int id) throws SQLException;

    void remove(int id) throws SQLException;

    void update(Consultation consultation) throws SQLException;

    ResultSet getAll() throws SQLException;
}

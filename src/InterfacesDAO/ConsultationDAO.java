package InterfacesDAO;

import Entity.Consultation;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConsultationDAO {
    public ResultSet add(Consultation consultation) throws SQLException;

    public ResultSet get(int id) throws SQLException;

    public void remove(int id) throws SQLException;

    public void update(Consultation consultation) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

package InterfacesDAO;

import Entity.Patients;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PatientsDAO {
    void add(Patients patients) throws SQLException;

    ResultSet get(int id) throws SQLException;

    void remove(int id) throws SQLException;

    void update(Patients patients) throws SQLException;

    ResultSet getAll() throws SQLException;
}

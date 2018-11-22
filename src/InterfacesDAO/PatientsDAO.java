package InterfacesDAO;

import Entity.Patients;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PatientsDAO {
    public void add(Patients patients) throws SQLException;

    public ResultSet get(int id) throws SQLException;

    public void remove(int id) throws SQLException;

    public void update(Patients patients) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

package InterfacesDAO;

import Entity.Diagnosis;
import Entity.Diseases;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DiagnosisDAO {
    public void add(Diagnosis diagnosis) throws SQLException;

    public ResultSet get(int id) throws SQLException;

    public void remove(int id) throws SQLException;

    public void update(Diagnosis diagnosis) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

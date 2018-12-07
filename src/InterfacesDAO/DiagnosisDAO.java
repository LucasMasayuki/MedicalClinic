package InterfacesDAO;

import Entity.Diagnosis;
import Entity.Diseases;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DiagnosisDAO {
    void add(Diagnosis diagnosis) throws SQLException;

    ResultSet get(int id) throws SQLException;

    void remove(int id) throws SQLException;

    void update(Diagnosis diagnosis) throws SQLException;

    ResultSet getAll() throws SQLException;
}

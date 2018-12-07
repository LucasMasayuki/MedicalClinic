package InterfacesDAO;

import Entity.Specialties;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SpecialtiesDAO {
    void add(Specialties Doctor) throws SQLException;

    ResultSet get(int id) throws SQLException;

    void remove(int id) throws SQLException;

    void update(Specialties doctor) throws SQLException;

    ResultSet getAll() throws SQLException;
}

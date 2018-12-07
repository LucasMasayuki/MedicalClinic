package InterfacesDAO;

import Entity.Doctors;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DoctorsDAO {
    ResultSet add(Doctors Doctor) throws SQLException;

    ResultSet get(int id) throws SQLException;

    void remove(int id) throws SQLException;

    void update(Doctors doctor) throws SQLException;

    ResultSet getAll() throws SQLException;
}

package InterfacesDAO;

import Entity.Doctors;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DoctorsDAO {
    public ResultSet add(Doctors Doctor) throws SQLException;

    public ResultSet get(int id) throws SQLException;

    public void remove(int id) throws SQLException;

    public void update(Doctors doctor) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

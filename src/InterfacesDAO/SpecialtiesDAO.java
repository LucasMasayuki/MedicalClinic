package InterfacesDAO;

import Entity.Specialties;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SpecialtiesDAO {
    public void add(Specialties Doctor) throws SQLException;

    public ResultSet get(int id) throws SQLException;

    public void remove(int id) throws SQLException;

    public void update(Specialties doctor) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

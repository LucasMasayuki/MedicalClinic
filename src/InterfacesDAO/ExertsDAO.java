package InterfacesDAO;

import Entity.Exerts;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ExertsDAO {
    public void add(Exerts Doctor) throws SQLException;

    public ResultSet getSpecialtiesOfDoctor(int id) throws SQLException;

    public void removeSpecialtyOfDoctor(int doctors_id, int specialties_id) throws SQLException;

    public void update(Exerts doctor) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

package InterfacesDAO;

import Entity.Exerts;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ExertsDAO {
    void add(Exerts Doctor) throws SQLException;

    ResultSet getSpecialtiesOfDoctor(int id) throws SQLException;

    void removeSpecialtyOfDoctor(int doctors_id, int specialties_id) throws SQLException;

    void update(Exerts doctor) throws SQLException;

    ResultSet getAll() throws SQLException;
}

package InterfacesDAO;

import Entity.Diseases;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DiseasesDAO {
    void add(Diseases diseases) throws SQLException;

    ResultSet get(int id) throws SQLException;

    void remove(int id) throws SQLException;

    void update(Diseases diseases) throws SQLException;

    ResultSet getAll() throws SQLException;
}

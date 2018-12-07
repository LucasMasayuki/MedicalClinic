package InterfacesDAO;

import Entity.Diseases;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DiseasesDAO {
    public void add(Diseases diseases) throws SQLException;

    public ResultSet get(int id) throws SQLException;

    public void remove(int id) throws SQLException;

    public void update(Diseases diseases) throws SQLException;

    public ResultSet getAll() throws SQLException;
}

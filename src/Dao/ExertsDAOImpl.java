package Dao;

import Database.ConnectionManager;
import Entity.Exerts;
import InterfacesDAO.ExertsDAO;

import java.sql.*;

public class ExertsDAOImpl implements ExertsDAO {
    public ExertsDAOImpl(){
    }

    @Override
    public void add(Exerts exerts) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = " INSERT INTO Exerts (doctors_id, specialties_id) VALUES (?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, exerts.getDoctors_id());
        statement.setInt(2, exerts.getSpecialties_id());
        statement.execute();
    }

    @Override
    public ResultSet getSpecialtiesOfDoctor(int doctorId) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "SELECT * FROM Exerts where doctors_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, doctorId);

        return statement.executeQuery();
    }

    @Override
    public void removeSpecialtyOfDoctor(int doctorsId, int specialtiesId) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "DELETE * FROM Exerts where doctors_id = ? AND specialties_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, doctorsId);
        statement.setInt(2, specialtiesId);

        statement.execute();
    }

    @Override
    public void update(Exerts exerts) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "UPDATE Exerts SET doctors_id = ?, specialties_id = ? where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, exerts.getDoctors_id());
        statement.setInt(2, exerts.getSpecialties_id());
        statement.setInt(3, exerts.getId());

        statement.execute();
    }

    @Override
    public ResultSet getAll() throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();
        Statement statement = conn.createStatement();

        String sql = "SELECT * FROM Exerts";

        return statement.executeQuery(sql);
    }

    public ResultSet getDoctorsExertThisSpecialty(int specialtyId) throws SQLException {
        Connection conn = ConnectionManager.getAnInstance().getConnection();

        String sql = "" +
                "SELECT * FROM Exerts " +
                "INNER JOIN Doctors " +
                "ON Doctors.id = Exerts.doctors_id " +
                "WHERE specialties_id = ? ";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, specialtyId);

        return statement.executeQuery();
    }
}

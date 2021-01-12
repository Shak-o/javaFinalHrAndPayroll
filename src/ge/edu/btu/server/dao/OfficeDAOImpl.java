package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.Office;

import java.sql.*;

public class OfficeDAOImpl implements OfficeDAO {
    private Connection connection;

    public OfficeDAOImpl() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employees", "postgres", "test");
    }

    @Override
    public void addStructure(Office office) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO office " +
                "(structure, position, position_id) VALUES (?,?,?)");
        preparedStatement.setString(1, office.getStructure());
        preparedStatement.setString(2, office.getPosition());
        preparedStatement.setString(3, office.getPosition_id());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    @Override
    public void editStructure(Office office, String pos_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE office SET" +
                "(structure = ?, position = ?, position_id = ?) WHERE id = ?");
        preparedStatement.setString(1, office.getStructure());
        preparedStatement.setString(2, office.getPosition());
        preparedStatement.setString(3, office.getPosition_id());
        preparedStatement.setString(4, pos_id);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void deleteStructure(String pos_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM office WHERE position_id = ?");
        preparedStatement.setString(1, pos_id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


}

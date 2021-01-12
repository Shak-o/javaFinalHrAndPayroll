package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.Office;

import java.sql.SQLException;

public interface OfficeDAO {
    void addStructure(Office office) throws SQLException;
    void editStructure(Office office, String pos_id) throws SQLException;
    void deleteStructure(String pos_id) throws SQLException;
}

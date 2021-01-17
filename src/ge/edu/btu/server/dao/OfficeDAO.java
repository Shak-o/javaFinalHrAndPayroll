package ge.edu.btu.server.dao;

import ge.edu.btu.common.OfficeView;
import ge.edu.btu.server.model.Office;

import java.sql.SQLException;
import java.util.List;

public interface OfficeDAO {
    void addStructure(OfficeView office) throws SQLException;
    void editStructure(Office office, String pos_id) throws SQLException;
    void deleteStructure(String pos_id) throws SQLException;
    List<OfficeView> getAllOffice() throws SQLException;
}

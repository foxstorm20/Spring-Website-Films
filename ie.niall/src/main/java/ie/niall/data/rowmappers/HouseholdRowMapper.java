package ie.niall.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;

import ie.niall.data.Household;



public class HouseholdRowMapper implements RowMapper<Household> {
	public Household mapRow(ResultSet rs, int rowNumber) throws SQLException{
		Household household = new Household();
		household.setHouseId(rs.getInt("houseId"));
		household.setEircode(rs.getString("eircode"));
		household.setAddress(rs.getString("address"));
		return household;
	}

	public int[] getRowsForPaths(TreePath[] arg0) {
		return null;
	}
}

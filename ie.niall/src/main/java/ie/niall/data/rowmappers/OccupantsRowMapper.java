package ie.niall.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;

import ie.niall.data.Occupants;

public class OccupantsRowMapper implements RowMapper<Occupants> {
	public Occupants mapRow(ResultSet rs, int rowNumber) throws SQLException{
		Occupants occupants = new Occupants();
		occupants.setOccupantId(rs.getInt("occupantId"));
		occupants.setName(rs.getString("name"));
		occupants.setAge(rs.getInt("age"));
		occupants.setOccupation(rs.getString("occupation"));
		occupants.setEircode(rs.getString("eircode"));
		return occupants;
	}

	public int[] getRowsForPaths(TreePath[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}

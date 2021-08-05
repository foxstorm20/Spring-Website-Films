package ie.niall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ie.niall.data.Household;
import ie.niall.data.Occupants;
import ie.niall.data.rowmappers.HouseholdRowMapper;
import ie.niall.data.rowmappers.OccupantsRowMapper;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DaoImplementation implements DaoInterface{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Occupants findOccupant(int occupantId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM occupants WHERE occupants.occupantId = ?", new Object[] {occupantId}, new OccupantsRowMapper());
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public Household findHouse(String eircode) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM house WHERE house.eircode = ?", new Object[] {eircode}, new HouseholdRowMapper());
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public Household findHouseID(int houseID) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM house WHERE house.houseID = ?", new Object[] {houseID}, new HouseholdRowMapper());
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public int deletePerson(int occupantId) {
		final String SQL = "DELETE FROM occupants WHERE occupants.occupantId=?";
		return jdbcTemplate.update(SQL, new Object[] {occupantId});
	}
	public int deleteHousehold(String eircode) {
		final String SQL = "DELETE FROM house WHERE house.eircode=?";
		return jdbcTemplate.update(SQL, new Object[] {eircode});
	}
	public int addPerson(final String personName, final int age, final String occupation, final String eircode) {
		final String INSERT_SQL = "INSERT into occupants(name, age, occupation, eircode) VALUES (?,?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator()
				{
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException
					{
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"occupantId"});
						ps.setString(1,  personName);
						ps.setInt(2, age);
						ps.setString(3, occupation);
						ps.setString(4, eircode);
						return ps;
					}
				}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int addHouse(final String eircode, final String address) {
		final String INSERT_SQL = "INSERT into house(eircode, address) VALUES (?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator()
				{
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException
					{
						PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] {"houseId"});
						ps.setString(1,  eircode);
						ps.setString(2, address);
						return ps;
					}
				}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public List<Occupants> findPeopleFromEircode(String eircode) {
		try {
			return jdbcTemplate.query("SELECT * FROM occupants WHERE occupants.eircode = ?", new Object[] {eircode}, new OccupantsRowMapper());
		}
		catch (Exception ex)
		{
			log.error("There is no eircode named " + eircode);
			return null;
		}
	}
	
	public List<Household> listAllHouse(){
		return jdbcTemplate.query("SELECT * FROM house", new HouseholdRowMapper());
	}
	
	public List<Occupants> listAllOccupants(){
		return jdbcTemplate.query("SELECT * FROM occupants", new OccupantsRowMapper());
	}
	
	public int moveAPersonFromAnotherHousehold(int occupantId, String newEircode) {
		final String SQL = "UPDATE occupants SET occupants.eircode = ? WHERE occupants.occupantId = ?";
		int numberChanged = jdbcTemplate.update(SQL, new Object[] {newEircode, occupantId});
		if(numberChanged == 0)
			log.error("there is no eircode with " + occupantId);
		// TODO Auto-generated method stub
		return numberChanged;
	}
	public String displayStatistics() {
		// TODO Auto-generated method stub
		return "Average age of householders: "+jdbcTemplate.queryForObject("SELECT AVG(age) from occupants", Integer.class) + 
				"\nNumber of students in households: " + jdbcTemplate.queryForObject("SELECT COUNT(*) from occupants WHERE occupation = 'Student'", Integer.class) +
				"\nNumber of OAPs (over 65s): " + jdbcTemplate.queryForObject("SELECT COUNT(*) from occupants WHERE age >= 65", Integer.class);
		
	}

	/*public boolean exists(String personName) {
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM occupants WHERE occupants.name = ?", new Object[] {personName}, Integer.class);
	}*/
	
	public boolean existsWithId(int occupantId) {
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM occupants WHERE occupants.occupantId = ?", new Object[] {occupantId}, Integer.class);
	}
	
	public boolean houseExists(String eircode) {
		return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM house WHERE house.eircode = ?", new Object[] {eircode}, Integer.class);
	}
}

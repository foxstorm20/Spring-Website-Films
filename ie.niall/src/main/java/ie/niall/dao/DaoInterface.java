package ie.niall.dao;

import java.util.List;

import ie.niall.data.Household;
import ie.niall.data.Occupants;

public interface DaoInterface {
	List<Household> listAllHouse();
	List<Occupants> listAllOccupants();
	List<Occupants> findPeopleFromEircode(String eircode);
	int deleteHousehold(String eircode);
	int deletePerson(int occupantId);
	int moveAPersonFromAnotherHousehold(int occupantId, String newEircode);
	String displayStatistics();
	int addPerson(final String personName, final int age, final String occupation, final String eircode);
	boolean existsWithId(int occupantId);
	//findOccupant(int occupantId);
	boolean houseExists(String eircode);
	Occupants findOccupant(int occupantId);
	Household findHouse(String eircode);
	Household findHouseID(int houseID);
	int addHouse(final String eircode, final String address);
}

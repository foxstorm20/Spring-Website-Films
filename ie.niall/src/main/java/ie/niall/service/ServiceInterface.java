package ie.niall.service;

import java.util.List;

import ie.niall.data.Household;
import ie.niall.data.Occupants;


public interface ServiceInterface {
	List<Household> listAllHouse();
	List<Occupants> listAllOccupants();
	List<Occupants> findPeopleFromEircode(String eircode);
	int deleteHousehold(String eircode);
	int deletePerson(int occupantId);
	int moveAPersonFromAnotherHouseholdService(int occupantId, String newEircode);
	String displayStatistics();
	Household addHouse(final String eircode, final String address);
	Occupants findOccupant(int occupantId);
	Household findHouseID(int householdId);
	//Household findHouseID(int houseID);
	Household findHouse(String eircode);
	Occupants addPerson(final String personName, final int age, final String occupation, final String eircode);
}

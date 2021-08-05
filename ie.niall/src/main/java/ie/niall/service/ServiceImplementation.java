package ie.niall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.niall.dao.DaoInterface;
import ie.niall.data.Household;
import ie.niall.data.Occupants;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceImplementation implements ServiceInterface{
	@Autowired
	DaoInterface dao;
	
	public Occupants findOccupant(int occupantId) {
		return dao.findOccupant(occupantId);
	}
	
	public List<Occupants> findPeopleFromEircode(String eircode) {
		return dao.findPeopleFromEircode(eircode);
	}

	public int deleteHousehold(String householdId) {
		return dao.deleteHousehold(householdId);
	}

	public int deletePerson(int occupantId) {
		return dao.deletePerson(occupantId);
	}

	public int moveAPersonFromAnotherHouseholdService(int occupantId, String newEircode) {
		if(! dao.existsWithId(occupantId)) {
			log.error(occupantId + " is not in the database");
			return 0;}
		if (!(dao.houseExists(newEircode))) {
			log.error(newEircode + " does not exist in the house"); 
			return 0;
		}
		int countChanged = dao.moveAPersonFromAnotherHousehold(occupantId, newEircode);
		if (countChanged == 0)
			log.error(occupantId + " is not in the database.");
		return countChanged;
	}

	public String displayStatistics() {
		return dao.displayStatistics();
	}

	public Household addHouse(final String eircode, final String address) {
		if (!dao.houseExists(eircode)) {
			return dao.findHouseID(dao.addHouse(eircode, address));
		}
		else {
			log.error(eircode + " is already in the database");
		}
		return null;
	}

	public Occupants addPerson(final String personName, final int age, final String occupation, final String eircode) {
		if(!(dao.houseExists(eircode)))
			log.error(eircode + " doesn't exist");
		else {
			return dao.findOccupant(dao.addPerson(personName, age, occupation, eircode));
		}
		return null;
	}

	public Household findHouseID(int householdId) {
		return dao.findHouseID(householdId);
	}
	public Household findHouse(String eircode) {
		return dao.findHouse(eircode);
	}

	public List<Household> listAllHouse() {
		return dao.listAllHouse();
	}

	public List<Occupants> listAllOccupants() {
		return dao.listAllOccupants();
	}

}

package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ie.niall.data.Occupants;
import ie.niall.service.ServiceInterface;
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTest {
	@Autowired
	ServiceInterface service;
	
	@Test
	@Order(1)
	public void testAddingOccupant() {
		String occupantName = "Karl Marx";
		int age = 57;
		String occupation = "Communist";
		String eircode = "P31 DX78";
		Occupants didItAdd = service.addPerson(occupantName, age, occupation, eircode);
		assertTrue(didItAdd.toString().contains("Karl Marx"));
	}
	
	@Test
	@Order(3)
	public void testDeletingSomeone() {
		int indexed = 2;
		int delete = service.deletePerson(indexed);
		assertEquals(1, delete);
		//assertFalse();
	}
	
	@Test
	@Order(2)
	public void testListingBasedEircode() {
		String eircode = "P31 DX78";
		List<Occupants> listEircode = service.findPeopleFromEircode(eircode);
		assertTrue(listEircode.toString().contains("P31 DX78") & listEircode.toString().contains("Niall Xie") & listEircode.toString().contains("Karl Marx"));
	}
	
	@Test
	@Order(4)
	public void testStatistics() {
		int occupantId = 3;
		String newEircode = "P50 DX65";
		int moved = service.moveAPersonFromAnotherHouseholdService(occupantId, newEircode);
		assertEquals(1, moved);
	}

}

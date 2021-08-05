package ie.niall.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ie.niall.data.Household;
import ie.niall.data.Occupants;
import ie.niall.service.ServiceInterface;

public class Main {
	
	public static void displayMenu() {
		System.out.println("Choose your option");
		System.out.println("===============================");
		System.out.println("\t1: Find household's occupants");
		System.out.println("\t2: Add Household");
		System.out.println("\t3: Add new occupant for a house");
		System.out.println("\t4: Move occupant to another house");
		System.out.println("\t5: Delete household");
		System.out.println("\t6: Delete occupant");
		System.out.println("\t7: Display Statistics");
		System.out.println("\t8: List all households");
		System.out.println("\t9: List all occupants");
		System.out.println("\t0: QUIT");
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//menu of options
		
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ServiceInterface heroService =(ServiceInterface)context.getBean("serviceImplementation");
		displayMenu();
		Scanner in = new Scanner(System.in);
		int decision = in.nextInt();
		in.nextLine();
		while(decision != 0) {
			//decision = in.nextInt();
			if(decision == 1) {
				System.out.println("Please enter Eircode of occupants you want to display?");
				String eircode = in.nextLine();
				
				System.out.println(heroService.findHouse(eircode) + "\n");
				List<Occupants> occupants = heroService.findPeopleFromEircode(eircode);
				for(Occupants occupant: occupants)
					System.out.println("\t"+occupant);	
			}
			else if (decision == 2) {
				System.out.println("Please enter Eircode");
				String eircode = in.nextLine();
				
				System.out.println("Enter the address");
				String address = in.nextLine();
				Household added = heroService.addHouse(eircode, address);
				//System.out.println(heroService.addHouse(eircode, address));
				if (added != null) {
				System.out.println("Would you like to add another occupant");
				String choice = in.nextLine();
				while(choice.equals("y")) {
					System.out.println("Type in the name");
					String personName = in.nextLine();
					System.out.println("Type in age");
					int age = in.nextInt();
					in.nextLine();
					System.out.println("Type in occupation");
					String occupation = in.nextLine();
					System.out.println("Adding " + personName + ": "+heroService.addPerson(personName, age, occupation, eircode));
					System.out.println("Would you like to add another occupant");
					choice = in.nextLine();
				}}
				//}
			}
			else if (decision == 3) {
				System.out.println("Type in the name");
				String personName = in.nextLine();
				System.out.println("Type in age");
				int age = in.nextInt();
				in.nextLine();
				System.out.println("Type in occupation");
				String occupation = in.nextLine();
				System.out.println("Type in the eircode");
				String eircode = in.nextLine();
				System.out.println("Adding " + personName + ": "+heroService.addPerson(personName, age, occupation, eircode));
			}
			else if (decision == 4) {
				System.out.println("select the ID of the person you want to move");
				int occupantId = in.nextInt();
				in.nextLine();
				System.out.println("Select the eircode of the house you want to move to");
				String newEircode = in.nextLine();
				System.out.println("Moving person to "+newEircode+": "+heroService.moveAPersonFromAnotherHouseholdService(occupantId, newEircode));
			}
			else if (decision == 5) {
				System.out.println("Type in eircode to delete house");
				String eircode = in.nextLine();
				if (heroService.deleteHousehold(eircode) == 1)
					System.out.println(eircode + " deleted ");
				else 
					System.out.println("eircode not found");
			}
			else if (decision == 6) {
				System.out.println("Type in id to delete occupant");
				int occupantId = in.nextInt();
				in.nextLine();
				if (heroService.deletePerson(occupantId) == 1) 
					System.out.println("Person "+occupantId + " deleted");
				else
					System.out.println("Person does not exist");
			}
			else if (decision == 7) {
				System.out.println(heroService.displayStatistics());
			}
			else if (decision == 8) {
				List<Household> houses = heroService.listAllHouse();
				for(Household house: houses)
					System.out.println("\t"+ house);
			}
			else if (decision == 9) {
				List<Occupants> person = heroService.listAllOccupants();
				for(Occupants occupant1: person)
					System.out.println("\t"+ occupant1);
			}
			else if (decision == 0) {
				System.out.println("Goodbye");
			}
			else {
				System.out.println("Please print a number between 0-10");
			}
			displayMenu();
			decision = in.nextInt();
			in.nextLine();
		}
		System.out.println("Goodbye");
	}

}

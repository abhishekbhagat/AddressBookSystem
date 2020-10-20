package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;

public class AddressBookSystem {
	private static Map<String, List<ContactPerson>> addressBookNameMap = new TreeMap<>();

	/**
	 * uc6
	 * 
	 * @param addressBookName
	 */
	public void addAddressBookList(String addressBookName) {
		if (addressBookNameMap.get(addressBookName).size() != 0)
			System.out.println("Already exist");
		else {
			List<ContactPerson> newContactPersonList = new ArrayList<ContactPerson>();
			addressBookNameMap.put(addressBookName, newContactPersonList);
		}
	}

	/**
	 * uc7
	 * 
	 * @param addressBookName
	 */
	public static void addContactPerson(String addressBookName) {
		List<ContactPerson> contactPersonList = addressBookNameMap.get(addressBookName);
		if (contactPersonList.size() == 0)
			System.out.println("Address Book of given name is not exist");
		else {
			Scanner consoleInput = new Scanner(System.in);
			System.out.println("Enter the First Name ");
			String firstName = consoleInput.next();
			System.out.println("Enter the last Name ");
			String lastName = consoleInput.next();
			System.out.println("Enter the address ");
			String address = consoleInput.next();
			System.out.println("Enter the  city ");
			String city = consoleInput.next();
			System.out.println("Enter the state ");
			String state = consoleInput.next();
			System.out.println("Enter the zip ");
			String zip = consoleInput.next();
			System.out.println("Enter the phoneNumber ");
			double phoneNumber = consoleInput.nextDouble();
			System.out.println("Enter the email ");
			String email = consoleInput.next();
			ContactPerson contactPerson = new ContactPerson(firstName, lastName, address, city, state, zip, phoneNumber,
					email);
			Predicate<ContactPerson> isDuplicate = (ContactPerson) -> {
				for (ContactPerson contactPersonInList : contactPersonList) {
					if (contactPersonInList.getFirstName().equals(contactPerson.getFirstName())
							&& contactPersonInList.getLastName().equals(contactPerson.getLastName()))
						return true;
				}
				return false;
			};
			if (contactPersonList.stream().anyMatch(isDuplicate)) {
			} else {
				contactPersonList.add(contactPerson);
			}
		}
	}

	/**
	 * uc8
	 * 
	 * @param fname
	 * @param choice
	 * @param cityOrState
	 */
	public void searchPersonAcrossCity(String city) {
		Predicate<ContactPerson> searchCity = contactPerson -> contactPerson.getCity().equals(city);
		addressBookNameMap.values().forEach(contactPersonList -> contactPersonList.stream().filter(searchCity)
				.forEach(contactPerson -> System.out.println(contactPerson)));
	}

	public void searchPersonAcrossState(String state) {
		Predicate<ContactPerson> searchState = contactPerson -> contactPerson.getState().equals(state);
		addressBookNameMap.values().forEach(contactPersonList -> contactPersonList.stream().filter(searchState)
				.forEach(contactPerson -> System.out.println(contactPerson)));
	}

	public static void deleteContactPerson(String firstName, String lastName) {
		for (Map.Entry<String, List<ContactPerson>> addressBook : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = addressBook.getValue();
			for (ContactPerson contactPerson : contactPersonList) {
				if (contactPerson.getFirstName().equals(firstName) && contactPerson.getLastName().equals(lastName))
					contactPersonList.remove(contactPerson);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBookSystem addressBookSystem = new AddressBookSystem();
		Scanner consoleInputObject = new Scanner(System.in);
		System.out.println("Enter the Name of the Address Book ");
		String addressBookName = consoleInputObject.next();
		addressBookSystem.addAddressBookList(addressBookName);
		System.out.println("Enter the name of the Address Book in which you want to add new contact ");
		addContactPerson(consoleInputObject.next());
		System.out.println("Enter the First Name and Last Name of the contact which you want to delet");
		String contactFirstName = consoleInputObject.next();
		String contactLastName = consoleInputObject.next();
		deleteContactPerson(contactFirstName, contactLastName);
	}
}

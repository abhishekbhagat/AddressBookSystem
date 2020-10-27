package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.bridgelabz.address.AddressBookCSV;
import com.bridgelabz.address.ContactPerson;

import java.util.Map.Entry;

public class AddressBookSystem {
	public static Map<String, List<ContactPerson>> addressBookNameMap = new TreeMap<>();
	private static Map<String, ContactPerson> cityMap = new TreeMap<>();
	private static Map<String, ContactPerson> stateMap = new TreeMap<>();
	public static final String CSV_FILE_PATH = "F:\\ContactPersonCSV";

	/**
	 * uc6
	 * 
	 * @param addressBookName
	 */
	public void addAddressBookList(String addressBookName) {
		if (addressBookNameMap.isEmpty()) {
			List<ContactPerson> newContactPersonList = new ArrayList<ContactPerson>();
			addressBookNameMap.put(addressBookName, newContactPersonList);
		} else {
			List<ContactPerson> newContactPersonList = addressBookNameMap.get(addressBookName);
			if (!(newContactPersonList == null))
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
		if (contactPersonList == null) {
			System.out.println("Invalid address book name");
		} else {
			ContactPerson contactPerson = getContactPerson();
			if (contactPersonList.size() == 0) {
				contactPersonList.add(contactPerson);
				new AddressBookCSV().writeCsvFile(contactPerson, CSV_FILE_PATH);
				cityMap.put(contactPerson.getCity(), contactPerson);
				stateMap.put(contactPerson.getState(), contactPerson);
			} else {
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
					new AddressBookCSV().writeCsvFile(contactPerson, CSV_FILE_PATH);
					cityMap.put(contactPerson.getCity(), contactPerson);
					stateMap.put(contactPerson.getCity(), contactPerson);

				}
			}
		}
	}

	public static ContactPerson getContactPerson() {
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
		String phoneNumber = consoleInput.next();
		System.out.println("Enter the email ");
		String email = consoleInput.next();
		ContactPerson contactPerson = new ContactPerson(firstName, lastName, address, city, state, zip, phoneNumber,
				email);
		return contactPerson;
	}

	/**
	 * uc8
	 * 
	 * @param fname
	 * @param choice
	 * @param cityOrState
	 */
	public static void searchPersonAcrossCity(String city) {
		Predicate<ContactPerson> searchCity = contactPerson -> contactPerson.getCity().equals(city);
		addressBookNameMap.values().forEach(contactPersonList -> contactPersonList.stream().filter(searchCity)
				.forEach(contactPerson -> System.out.println(contactPerson)));
	}

	/**
	 * uc8
	 * 
	 * @param state
	 */
	public static void searchPersonAcrossState(String state) {
		Predicate<ContactPerson> searchState = contactPerson -> contactPerson.getState().equals(state);
		addressBookNameMap.values().forEach(contactPersonList -> contactPersonList.stream().filter(searchState)
				.forEach(contactPerson -> System.out.println(contactPerson)));
	}

	/**
	 * uc9
	 * 
	 * @param city
	 */
	public static void viewPersonsByCity(String city) {
		for (Map.Entry<String, ContactPerson> entrycontactPerson : cityMap.entrySet()) {
			ContactPerson contactPerson = entrycontactPerson.getValue();
			if (contactPerson.getCity().equals(city))
				System.out.println(contactPerson);
		}
	}

	public static void viewPersonsByState(String state) {
		for (Map.Entry<String, ContactPerson> entry : stateMap.entrySet()) {
			ContactPerson contactPerson = entry.getValue();
			if (contactPerson.getState().equals(state))
				System.out.println(contactPerson);
		}
	}

	/**
	 * uc10
	 * 
	 * @param state
	 */
	public static void getNumber_OfContactPersonsByState(String state) {

		for (Map.Entry<String, List<ContactPerson>> entry : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = entry.getValue();
			System.out.println((int) contactPersonList.stream()
					.filter(ContactPerson -> ContactPerson.getState().equals(state)).count());
		}
	}

	public static void getNumber_OfContactPersonsByCity(String city) {

		for (Map.Entry<String, List<ContactPerson>> entry : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = entry.getValue();
			System.out.println((int) contactPersonList.stream()
					.filter(ContactPerson -> ContactPerson.getState().equals(city)).count());
		}
	}

	/**
	 * uc11
	 */
	public static void sortByName() {
		for (Map.Entry<String, List<ContactPerson>> entry : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = entry.getValue();
			List<ContactPerson> contactPersonListSortedByName = contactPersonList.stream()
					.sorted((ContactPerson c1, ContactPerson c2) -> c1.getFirstName().compareTo(c2.getFirstName()))
					.collect(Collectors.toList());
			for (ContactPerson contactPerson : contactPersonListSortedByName) {
				System.out.println(contactPerson);
			}
		}
	}

	/**
	 * uc12
	 * 
	 */
	public static void sortByCity() {
		for (Map.Entry<String, List<ContactPerson>> entry : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = entry.getValue();
			List<ContactPerson> contactPersonListSortedByCity = contactPersonList.stream()
					.sorted((ContactPerson c1, ContactPerson c2) -> c1.getCity().compareTo(c2.getCity()))
					.collect(Collectors.toList());
			for (ContactPerson contactPerson : contactPersonListSortedByCity) {
				System.out.println(contactPerson);
			}
		}
	}

	/**
	 * uc12
	 * 
	 */
	public static void sortByZip() {
		for (Map.Entry<String, List<ContactPerson>> entry : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = entry.getValue();
			List<ContactPerson> contactPersonListSortedByZip = contactPersonList.stream()
					.sorted((ContactPerson c1, ContactPerson c2) -> c1.getZip().compareTo(c2.getZip()))
					.collect(Collectors.toList());
			for (ContactPerson contactPerson : contactPersonListSortedByZip) {
				System.out.println(contactPerson);
			}
		}
	}

	/**
	 * uc12
	 * 
	 */
	public static void sortByState() {
		for (Map.Entry<String, List<ContactPerson>> entry : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = entry.getValue();
			List<ContactPerson> contactPersonListSortedByState = contactPersonList.stream()
					.sorted((ContactPerson c1, ContactPerson c2) -> c1.getState().compareTo(c2.getState()))
					.collect(Collectors.toList());
			for (ContactPerson contactPerson : contactPersonListSortedByState) {
				System.out.println(contactPerson);
			}
		}
	}

	public static void deleteContactPerson(String firstName, String lastName) {
		for (Map.Entry<String, List<ContactPerson>> entry : addressBookNameMap.entrySet()) {
			List<ContactPerson> contactPersonList = entry.getValue();
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
		while (true) {
			System.out.println("1.Add Address Book");
			System.out.println("2.Exit");
			int choice = consoleInputObject.nextInt();
			if (choice == 1) {
				System.out.println("Enter the Name of the Address Book ");
				String addressBookName = consoleInputObject.next();
				addressBookSystem.addAddressBookList(addressBookName);
			} else
				break;
		}
		while (true) {
			System.out.println("1.Add New Contact Person");
			System.out.println("Exit");
			int choice = consoleInputObject.nextInt();
			if (choice == 1) {
				System.out.println("Enter the Name of the Address Book ");
				addContactPerson(consoleInputObject.next());
			} else
				break;
		}
		System.out.println("Do you want to read data from file(y/n)");
		char choice = consoleInputObject.next().charAt(0);
		if (choice == 'y' || choice == 'Y')
			new AddressBookFileIOService().readDataFromFile();
		System.out.println("Enter the name of the Address Book in which you want to add new contact ");
		addContactPerson(consoleInputObject.next());
		System.out.println("Enter the First Name and Last Name of the contact which you want to delet");
		String contactFirstName = consoleInputObject.next();
		String contactLastName = consoleInputObject.next();
		deleteContactPerson(contactFirstName, contactLastName);
		searchPersonAcrossCity("dhanbad");
		viewPersonsByState("jharkhand");
		getNumber_OfContactPersonsByCity("dhanbad");
		sortByName();
	}
}

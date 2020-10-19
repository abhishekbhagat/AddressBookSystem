package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AddressBookSystem {
	private static Map<String, List<ContactPerson>> addressBookNameMap = new TreeMap<>();

	public void addAddressBookList(String addressBookName) {
		if (addressBookNameMap.get(addressBookName).size() != 0)
			System.out.println("Already exist");
		else {
			List<ContactPerson> newContactPersonList = new ArrayList<ContactPerson>();
			addressBookNameMap.put(addressBookName, newContactPersonList);
		}
	}

	public static void addContactPerson(String addressBookName) {
		List<ContactPerson> newContactPersonList = addressBookNameMap.get(addressBookName);
		if (newContactPersonList.size() == 0)
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
			newContactPersonList.add(contactPerson);
		}
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Name of the Address Book ");
		String addressBookName = sc.next();
		addressBookSystem.addAddressBookList(addressBookName);
		System.out.println("Enter the name of the Address Book in which you want to add new contact ");
		addContactPerson(sc.next());
		System.out.println("Enter the First Name and Last Name of the contact which you want to delet");
		String contactFirstName = sc.next();
		String contactLastName = sc.next();
		deleteContactPerson(contactFirstName, contactLastName);
	}
}

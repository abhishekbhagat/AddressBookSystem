package com.bridgelabz.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;

public class AddressBookJSON {
	public static void writeContactPerson(ContactPerson contactPerson) {
		try {
			Writer writer = Files.newBufferedWriter(Paths.get(AddressBookSystem.JSON_FILE_PATH));
			List<ContactPerson> contactList = new ArrayList<ContactPerson>();
			contactList.add(contactPerson);
			new Gson().toJson(contactList, writer);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void readContactPerson() {
		try {
			Files.walk(Paths.get(AddressBookSystem.JSON_FILE_PATH)).filter(Files::isRegularFile).forEach(file -> {
				List<ContactPerson> contactList = new ArrayList<ContactPerson>();
				try {
					Reader reader = Files.newBufferedReader(file.toAbsolutePath());
					contactList.addAll(Arrays.asList(new Gson().fromJson(reader, ContactPerson.class)));

				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.getMessage();
		}
	}
}

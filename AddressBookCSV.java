package com.bridgelabz.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class AddressBookCSV {
	private static List<ContactPerson> contactList;

	public static void writeCsvFile(ContactPerson contactPerson, String csvFilePath) {
		try {
			Writer writer = Files.newBufferedWriter(Paths.get(csvFilePath));
			StatefulBeanToCsv<ContactPerson> beanToCsv = new StatefulBeanToCsvBuilder<ContactPerson>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			contactList.add(contactPerson);
			try {
				beanToCsv.write(contactList);
				writer.close();
			} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void readContact() {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(AddressBookSystem.CSV_FILE_PATH));
			CSVReader csvReader = new CSVReader(reader);
			List<String[]> contactPersonRecords = csvReader.readAll();
			for (String[] contactPersonRecord : contactPersonRecords) {
				System.out.println("First Name " + contactPersonRecord[0]);

				System.out.println("last Name " + contactPersonRecord[1]);

				System.out.println("address " + contactPersonRecord[2]);

				System.out.println("city " + contactPersonRecord[3]);

				System.out.println(" state " + contactPersonRecord[4]);

				System.out.println("zip " + contactPersonRecord[5]);

				System.out.println(" phoneNumber " + contactPersonRecord[6]);

				System.out.println("email " + contactPersonRecord[7]);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// Ash Apsangi 11/27/2023
// This program identifies common names, counts boys and girls for each in 2005 and presents overall census data in the US based on the given files listing popular boy and girl names.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class nameCensus {
	public static void main(String[] args) {

		// Initializing count and total boy and girl names to 0
		int totBoys = 0, totGirls = 0, count;

		// Create file objects for girl and boy name files
		File girlFile = new File("girlnames.txt");
		File boyFile = new File("boynames.txt");

		HashMap<String, Integer> girlNames = new HashMap<String, Integer>(); // HashMap girlNames to store girl names
																				// and counts

		HashMap<String, Integer> boyNames = new HashMap<String, Integer>(); // HashMap boyNames to store boy names and
																			// counts

		ArrayList<String> commonNames = new ArrayList<String>(); // Storing common names (from boyNames and girlNames
																	// hashMaps) in an array list

		try (Scanner girlScanner = new Scanner(girlFile)) {

			while (girlScanner.hasNextLine()) {

				// Split into an array containing the girl names and count
				String[] nameInfo = girlScanner.nextLine().split(" ");

				// Extract the girl names and counts from the array that is created
				String nameGirl = nameInfo[0];
				int countGirl = Integer.parseInt(nameInfo[1]);

				totGirls += countGirl; // Calculating total number of girls in census

				girlNames.put(nameGirl, countGirl); // Add pair to hash map for girl Names
			}
		} catch (FileNotFoundException e) {

			// Handle file not found exception
			System.err.println("Error finding given file name containting girl names info.");

		}

		try (Scanner boyScanner = new Scanner(boyFile)) {
			while (boyScanner.hasNextLine()) {

				// Split into an array containing the boy name and count
				String[] nameInfo = boyScanner.nextLine().split(" ");

				// Extract the boy names and counts from the array that is created
				String nameBoy = nameInfo[0];
				int countBoy = Integer.parseInt(nameInfo[1]);

				boyNames.put(nameBoy, countBoy); // Add pair (name and count) to hash map for boy Names

				// Check if the boy name is already in the girl's HashMap
				if (girlNames.containsKey(nameBoy)) {

					// Add it to the array list of common names
					commonNames.add(nameBoy);
				}

				totBoys += countBoy; // Calculating total number of boys in census
			}

		} catch (FileNotFoundException e) {
			// Handle file not found exception
			System.err.println("Error finding given file name containing boy names info.");
		}

		// Print the total number of common names including each common name to console
		System.out.println(commonNames.size() + " common names: " + String.join(", ", commonNames) + "\n");

		// Print the total number of boys and girls recorded in the census.
		System.out.println(totBoys + " boys in census and " + totGirls + " girls in census\n");

		// Iterate through loop and print each common boy and girl names and their
		// totals
		for (count = 0; count < commonNames.size(); count++) {
			String name = commonNames.get(count);
			System.out.println(name + ": " + boyNames.get(name) + " boys and " + girlNames.get(name) + " girls\n");

		}

	}
}

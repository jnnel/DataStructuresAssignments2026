package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import computer.Computer;

/**
 * Driver class to test methods from Computer class.
 * 
 * Note: For my tests, I used the test file in project
 * folder called computers.txt.
 * 
 * When asked for file name, you don't need to put a path in if it is in project folder (not sub-folder)
 * Enter File Name: computers.txt
 */
public class ComputerArrayDriver {

	Computer[] itemArr;
	
	public ComputerArrayDriver(Computer[] itemArr) {
		
		this.itemArr = itemArr;
	}
	
	/**
	 * Find the number of lines in text file.
	 * @param file	A text file with String representation of Computer on each line.
	 * @return the number of lines in text file, representing Computers.
	 */
	public static int fileSize(File file) {
		int count = 0;
		try {
			
			Scanner fileScan = new Scanner(file);
			
			// read from file and count the number of lines in file
			while(fileScan.hasNextLine()) {	
				fileScan.nextLine();
				count++;
			}
			fileScan.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	/**
	 * Returns the average of the Integer price attribute of all computers in input array a.
	 * @param a An array of Computers.
	 * @return average of prices for all computers in input array a.
	 */
	public int overallAvg(Computer[] a) {
		
		int average = 0;
		for(Computer computer: a) {
				average += computer.getPrice();
		}
		average = average / a.length;
		return average;
	}
	
	/**
	 * Counts the number of Computers in the input array, Computer[] a,
	 * that are equal to the input Computer o.
	 * @param a array of Computers.
	 * @param o	Computer instance for comparing to array of Computers.
	 * @return the number of Computers in input array a that are equal to Computer o, as an integer.
	 */
	public int count(Computer[] a, Computer o) {
		
		int counter = 0;
		for(Computer c: a) {
			if(c.equals(o)) {
				counter++;
			}
		}
		
		return counter;
	}
	
	/**
	 * The average of the Integer price attribute of Computers with
	 * String attribute equal to the input string.
	 * @param a	Array of Computers.
	 * @param s	computer model name.
	 * @return
	 */
	public int groupAvg(Computer[] a, String s) {
		
		int average = 0;
		int counter = 0;
		for(Computer c: a) {
			if(c.getModel().equalsIgnoreCase(s)) {
				average += c.getPrice();
				counter++;
			}
		}
		average = average / counter;
		
		return average;
	}
	
	/**
	 * Find an array of Computer objects that contains all objects
	 * with price less than input Integer.
	 * @param a Array of Computers.
	 * @param i	integer price of computer in cents.
	 * @return	an array of Computer objects that contains all objects 
	 * 			in Computer[] a with price less than i.
	 */
	public Computer[] lessThan(Computer[] a, Integer i) {
		
		// subset of array must be at most as long as the full array
		Computer[] selectComputers = new Computer[a.length];
		
		int index = 0; // current index of selectComputers array
		for(Computer c: a) {
			if(c.getPrice() < i) {
				selectComputers[index] = c;
				index++;
			}
		}
		
		// only return an array without null values (empty slots) in selectComputers array
		Computer[] trimmedArray = new Computer[index];
		for(int j=0; j < index; j++) {
			trimmedArray[j] = selectComputers[j];
		}
		
		return trimmedArray;
	}
	
	/**
	 * Add to the price of each Computer in an array.
	 * @param a Array of Computers.
	 * @param s computer model name as String. We only edit Computers in a with model name, s.
	 * @param n	Integer value, in cents, that we add to each Computer in Computer[] a array 
	 * 			with model name, s.
	 */
	public void groupEdit(Computer[] a, String s, Integer n) {
		
		for(Computer c: a) {
			if(c.getModel().equalsIgnoreCase(s)) {
				c.setPrice(c.getPrice() + n);
			}
		}
	}

	/**
	 * Unit tests for Computer class methods.
	 */
	public static void main(String[] args) {
		
		// Instantiate Scanner for console input
		Scanner consoleScan = new Scanner(System.in);

		// Ask user for file name
		System.out.print("Enter File Name: ");
		String input = consoleScan.nextLine();
		
		// Obtain absolute path to working directory of current project
		// and merge absolute path with file name
		File currentDir = new File(input);
		String path = currentDir.getAbsolutePath();
		
		// With the absolute path included, create a File class for text file
		File file = new File(path);
		
		// Count number of object entries in file and use for size of Computer array
		int numComputers = fileSize(file);
		System.out.println("Size of file: " + numComputers);
		Computer[] ComputerArr = new Computer[numComputers];
		
		/* Retrieve information from text file, instantiate new Computers,
		 * and add these to ComputerArr.
		 */
		try {
			
			Scanner fileScan = new Scanner(file);
			
			// read from file, instantiate Computer objects and add to ComputerArr
			String model;
			Integer price;
			int computerArrIndex = 0;
			while(fileScan.hasNext()) {
				model = fileScan.next();	// String instance variable 1st
				price = fileScan.nextInt();	// Integer instance variable 2nd
				
				ComputerArr[computerArrIndex] = new Computer(model, price);
				computerArrIndex++;
			}
			fileScan.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		consoleScan.close();
		
		// Print all Computer objects onto the screen
		for(Computer computer: ComputerArr) {
				System.out.println(computer.toString());
		}
		System.out.println();
		
		// instance of ComputerArrayDriver to test Computer class methods
		ComputerArrayDriver driver = new ComputerArrayDriver(ComputerArr);
		
		// Test overallAvg method
		System.out.println("Average Integer attribute: " + driver.overallAvg(ComputerArr));
		System.out.println();
		
		// Test count method
		String name = "Dell16Plus";
		Integer price = 71999;
		Computer dell16Plus71999 = new Computer(name, price);
		int numDell16Plus71999 = driver.count(ComputerArr, dell16Plus71999);
		System.out.println("Number of Dell 16 Plus Laptops priced at 71999 cents = " +
							numDell16Plus71999);
		System.out.println();
		
		// Test groupAvg method
		String model = "ASUSZenbookS16";
		int asusZenbookS16Average = driver.groupAvg(ComputerArr, model);
		System.out.println("Average Price of ASUS Zenbook S 16 Laptops: " +
							asusZenbookS16Average);
		System.out.println();
		
		// Test lessThan method
		int maxPrice = 150000;
		Computer[] lessThan = driver.lessThan(ComputerArr, maxPrice);
		System.out.println("Number of Computers with Price less than " +
								maxPrice + ": " + lessThan.length);
		for(Computer c: lessThan) {
			System.out.println(c.toString());
		}
		System.out.println();
		
		// Test groupEdit method
		model = "AppleMacbookPro14";
		Integer priceChange = 15000;
		driver.groupEdit(ComputerArr, model, priceChange);
		System.out.println("Change all " + model + " Laptops' price by " +
							priceChange);
		for(Computer computer: ComputerArr) { // Print all Computer objects with name (String model) onto the screen
				if(computer.getModel().equalsIgnoreCase(model)) {
					System.out.println(computer.toString());
				}
		}
	}
}

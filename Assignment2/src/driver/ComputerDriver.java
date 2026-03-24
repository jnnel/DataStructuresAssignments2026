package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import collection.ComputerSortedArraySet;
import computer.Computer;
import computer.MacComputer;
import computer.WindowsComputer;

public class ComputerDriver {
	
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
		
		// Count number of object entries in file and use for size of ComputerSortedArraySet
		int numComputers = fileSize(file);
		System.out.println("Size of file: " + numComputers);
		ComputerSortedArraySet computerSet = new ComputerSortedArraySet(numComputers);
		
		/* Retrieve information from text file, instantiate new Computers,
		 * and add these to ComputerArr.
		 */
		try {
			
			Scanner fileScan = new Scanner(file);
			
			// read from file, instantiate Computer objects and add to ComputerArr
			String model;
			Integer price;
			Integer type;	// 1 for WindowsComputer, 2 for MacComputer, 3 for neither
			Integer ram;	// RAM instance variable for WindowComputer
			Boolean isLaptop;	// is this Mac a laptop?
			
			//int computerArrIndex = 0;
			while(fileScan.hasNext()) {
				type = fileScan.nextInt();  // integer representing type of object
				model = fileScan.next();	// String instance variable 1st
				price = fileScan.nextInt();	// Integer instance variable 2nd
				
				if(type.equals(1)) {	// Windows Computer
					ram = fileScan.nextInt();
					computerSet.insert(new WindowsComputer(model, price, ram));
				}
				else if(type.equals(2)) {	// Mac Computer
					isLaptop = fileScan.nextBoolean();
					computerSet.insert(new MacComputer(model, price, isLaptop));
				}
				else if(type.equals(3)) {	// Computer (neither Windows nor Mac)
					computerSet.insert(new Computer(model, price));
				}
				//computerArrIndex++;
			}
			fileScan.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		consoleScan.close();
		
		
		System.out.println();
		
		
		// Print all Computer objects onto the screen
		System.out.println("Print out all " + computerSet.size() + " elements in ComputerSortedArraySet: \n\n" + computerSet + "\n");
		System.out.println();
		
		// TEST indexOf method
		System.out.println("TEST indexOf METHOD!\n");
		
		MacComputer mac = new MacComputer("AppleMacBookAir13", 99900, true);
		System.out.println("1.	Index Of [" + mac + "] is " + computerSet.indexOf(mac));
		
		Computer metroPCS = new Computer("MetroTMobileMotorolaMoto", 9988);
		System.out.println("2.	Index Of [" + metroPCS + "] is " + computerSet.indexOf(metroPCS));
		
		WindowsComputer dell = new WindowsComputer("Dell16Plus", 81999, 32);
		System.out.println("3.	Index Of [" + dell + "] is " + computerSet.indexOf(dell));
		
		System.out.println();
		
		
		// TEST remove method
		System.out.println("TEST remove METHOD!\n");
		
		Computer iPhone = new Computer("AppleiPhone17Pro", 129900);
		System.out.println("1.	Was Computer [" + iPhone + "] removed?:  " + computerSet.remove(iPhone) + "\n");
		System.out.println("Print out all " + computerSet.size() + " elements in ComputerSortedArraySet: \n" + computerSet + "\n");
		System.out.println();
		
		WindowsComputer lenovo = new WindowsComputer("LenovoThinkPadE16Gen2AMD", 205900, 8);
		System.out.println("2.	Was Computer [" + lenovo + "] removed?:  " + computerSet.remove(lenovo) + "\n");
		System.out.println("Print out all " + computerSet.size() + " elements in ComputerSortedArraySet: \n" + computerSet + "\n");
		System.out.println();
		
		MacComputer iMac = new MacComputer("AppleiMac24", 129900, false);
		System.out.println("3.	Was Computer [" + iMac + "] removed?:  " + computerSet.remove(iMac) + "\n");
		System.out.println("Print out all " + computerSet.size() + " elements in ComputerSortedArraySet: \n" + computerSet + "\n");
		System.out.println();
		
		WindowsComputer asus = new WindowsComputer("AsusVivobook14", 44999, 16);	// not in computers.txt
		System.out.println("4.	Was Computer [" + asus + "] removed?:  " + computerSet.remove(asus) + "\n");
		System.out.println("Print out all " + computerSet.size() + " elements in ComputerSortedArraySet: \n" + computerSet + "\n");
		System.out.println();
		
		
		// TEST grab method
		System.out.println("TEST grab METHOD!\n");
		System.out.println("Current ComputerSortedArraySet size is " + computerSet.size());
		
		int index = 0;
		System.out.println("1.	Return Computer stored at index " + index + ": " + computerSet.grab(index));
		
		index = 5;
		System.out.println("2.	Return Computer stored at index " + index + ": " + computerSet.grab(index));
		
		index = computerSet.size()-1;
		System.out.println("3.	Return Computer stored at index " + index + ": " + computerSet.grab(index));
		
		index = computerSet.size();	// index equal to the size of collection (outside of stored Computers)
		System.out.println("4.	Return Computer stored at index " + index + " (== current set size) : " + computerSet.grab(index));
		System.out.println("\n");
		
		
		// TEST categorySet method
		System.out.println("TEST categorySet METHOD!\n");
		int type;
		ComputerSortedArraySet subset;
		
		System.out.println("First, print out all " + computerSet.size() + " current elements in ComputerSortedArraySet: \n" + computerSet);
		System.out.println();
		
		type = 1;
		subset = computerSet.categorySet(type);
		System.out.println("1.	Set of Category type " + type + "\n" + subset + "\n");
		
		type = 2;
		subset = computerSet.categorySet(type);
		System.out.println("2.	Set of Category type " + type + "\n" + subset + "\n");
		
		type = 3;
		subset = computerSet.categorySet(type);
		System.out.println("3.	Set of Category type " + type + "\n" + subset + "\n");
		
		
	}
}

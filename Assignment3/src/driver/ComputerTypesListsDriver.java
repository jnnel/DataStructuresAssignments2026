package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import computer.MacComputer;
import computer.WindowsComputer;
import list.ComputerOrderedList;
import list.ComputerTypesLists;

public class ComputerTypesListsDriver {

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
	 * Unit tests for ComputerTypesLists methods.
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
		
		// Count number of object entries in file
		int numComputers = fileSize(file);
		System.out.println("Size of file: " + numComputers);
		
		ComputerTypesLists typesLists = new ComputerTypesLists();
		
		/* Retrieve information from text file, instantiate new Computers,
		 * and add these to the ComputerTypesLists instance.
		 */
		try {
			
			Scanner fileScan = new Scanner(file);
			
			// read from file, instantiate Computer objects and add to ComputerTypesLists
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
					typesLists.add(new WindowsComputer(model, price, ram));
				}
				else if(type.equals(2)) {	// Mac Computer
					isLaptop = fileScan.nextBoolean();
					typesLists.add(new MacComputer(model, price, isLaptop));
				}
			}
			fileScan.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		consoleScan.close();
		
		
		System.out.println();
			
		// Print all Computer objects from both lists in ComputerTypesLists
		ComputerOrderedList windowsComputerList = typesLists.getList('a');
		ComputerOrderedList macComputerList = typesLists.getList('b');
		
		System.out.println("Print out all elements in both lists of ComputerTypesLists: \n");
		
		System.out.println("a. Print out all " + typesLists.sizeOf(windowsComputerList) + " elements in windowsComputerList: \n");
		typesLists.display(windowsComputerList);
		System.out.println();
		
		System.out.println("b. Print out all " + typesLists.sizeOf(macComputerList) + " elements in macComputerList: \n");
		typesLists.display(macComputerList);
		System.out.println();
		
		//		TEST indexOf METHOD!
		System.out.println("TEST indexOf METHOD!\n");
		
		// test case 1 (Windows)
		WindowsComputer dell = new WindowsComputer("Dell16Plus", 71999, 16);
		System.out.println("1.	Index of Windows Computer [" + dell + "] is:  " + typesLists.indexOf(dell) + "\n");
		
		// test case 2 (Mac)
		MacComputer macMini = new MacComputer("AppleMacMini", 58399, false);
		System.out.println("2.	Index of Mac Computer [" + macMini + "] is:  " + typesLists.indexOf(macMini) + "\n");
		
		//		TEST get METHOD!
		int getIndex = 1;
		System.out.println("TEST get METHOD!\n");
		
		// test case 1
		getIndex = 9;
		System.out.println("1.	Windows Computer at index " + getIndex + "  is:  " + typesLists.get(windowsComputerList, getIndex) + "\n");
		
		// test case 2
		getIndex = 3;
		System.out.println("2.	Mac Computer at index " + getIndex + "  is:  " + typesLists.get(macComputerList, getIndex) + "\n");
		
		System.out.println();
		
		//		TEST add(int index, Computer element) METHOD! (demonstrate using determineIndex along with add method explicitly)
		int addIndex = 0;
		boolean isAdded = false;
		System.out.println("TEST add(int index, Computer element) METHOD!\n");
		
		// test case 1
		WindowsComputer omniDesk = new WindowsComputer("HPOmniDesk", 69999, 16);
		addIndex = typesLists.determineIndex(omniDesk); // find the proper index to add computer in sorted order
		isAdded = typesLists.add(addIndex, omniDesk);
		System.out.println("1.	Was Computer [" + omniDesk + "] added?:  " + isAdded + "\n");
		
		System.out.println("a. Print out all " + typesLists.sizeOf(windowsComputerList) + " elements in windowsComputerList: \n");
		typesLists.display(windowsComputerList);
		System.out.println();
		
		System.out.println("b. Print out all " + typesLists.sizeOf(macComputerList) + " elements in macComputerList: \n");
		typesLists.display(macComputerList);
		System.out.println();
		
		// test case 2
		MacComputer appleMacStudio = new MacComputer("AppleMacStudio", 191899, false);
		addIndex = typesLists.determineIndex(appleMacStudio); // find the proper index to add computer in sorted order
		isAdded = typesLists.add(addIndex, appleMacStudio);
		System.out.println("1.	Was Computer [" + appleMacStudio + "] added?:  " + isAdded + "\n");
		
		System.out.println("a. Print out all " + typesLists.sizeOf(windowsComputerList) + " elements in windowsComputerList: \n");
		typesLists.display(windowsComputerList);
		System.out.println();
		
		System.out.println("b. Print out all " + typesLists.sizeOf(macComputerList) + " elements in macComputerList: \n");
		typesLists.display(macComputerList);
		System.out.println();
		
		//		TEST remove(Computer target) METHOD!
		boolean isRemoved = false;
		System.out.println("TEST remove(Computer target) METHOD!\n");
		
		// test case 1
		WindowsComputer dellXPS = new WindowsComputer("DellXPS16", 174999, 32);
		isRemoved = typesLists.remove(dellXPS);
		System.out.println("1.	Was Computer [" + dellXPS + "] removed?:  " + isRemoved + "\n");
		
		System.out.println("a. Print out all " + typesLists.sizeOf(windowsComputerList) + " elements in windowsComputerList: \n");
		typesLists.display(windowsComputerList);
		System.out.println();
		
		System.out.println("b. Print out all " + typesLists.sizeOf(macComputerList) + " elements in macComputerList: \n");
		typesLists.display(macComputerList);
		System.out.println();
		
		// test case 2
		MacComputer iMac24 = new MacComputer("AppleiMac24", 129900, false);
		isRemoved = typesLists.remove(iMac24);
		System.out.println("1.	Was Computer [" + iMac24 + "] removed?:  " + isRemoved + "\n");
		
		System.out.println("a. Print out all " + typesLists.sizeOf(windowsComputerList) + " elements in windowsComputerList: \n");
		typesLists.display(windowsComputerList);
		System.out.println();
		
		System.out.println("b. Print out all " + typesLists.sizeOf(macComputerList) + " elements in macComputerList: \n");
		typesLists.display(macComputerList);
		System.out.println();
		
		//		TEST remove(ComputerOrderedList iol, int index) METHOD!
		int removeIndex = 1;
		System.out.println("TEST remove(ComputerOrderedList iol, int index) METHOD!\n");
		
		// test case 1 (Windows)
		removeIndex = windowsComputerList.size();
		isRemoved = typesLists.remove(windowsComputerList, removeIndex);
		System.out.println("1.	Was Windows Computer at index " + removeIndex + " removed? [tail node]:  " + isRemoved + "\n");
		
		System.out.println("a. Print out all " + typesLists.sizeOf(windowsComputerList) + " elements in windowsComputerList: \n");
		typesLists.display(windowsComputerList);
		System.out.println();
		
		System.out.println("b. Print out all " + typesLists.sizeOf(macComputerList) + " elements in macComputerList: \n");
		typesLists.display(macComputerList);
		System.out.println();
		
		// test case 2 (Mac)
		removeIndex = 3;
		isRemoved = typesLists.remove(macComputerList, removeIndex);
		System.out.println("1.	Was Mac Computer at index " + removeIndex + " removed?:  " + isRemoved + "\n");
		
		System.out.println("a. Print out all " + typesLists.sizeOf(windowsComputerList) + " elements in windowsComputerList: \n");
		typesLists.display(windowsComputerList);
		System.out.println();
		
		System.out.println("b. Print out all " + typesLists.sizeOf(macComputerList) + " elements in macComputerList: \n");
		typesLists.display(macComputerList);
		System.out.println();
	}
}

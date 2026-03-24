package driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import list.ComputerOrderedList;
import computer.MacComputer;
import computer.WindowsComputer;

public class ComputerOrderedListDriver {

	
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
	 * Unit tests for ComputerOrderedList methods.
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
		ComputerOrderedList computerList = new ComputerOrderedList();
		
		/* Retrieve information from text file, instantiate new Computers,
		 * and add these to ComputerOrderedList.
		 */
		try {
			
			Scanner fileScan = new Scanner(file);
			
			// read from file, instantiate Computer objects and add to ComputerOrderedList
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
					computerList.addOrdered(new WindowsComputer(model, price, ram));
				}
				else if(type.equals(2)) {	// Mac Computer
					isLaptop = fileScan.nextBoolean();
					computerList.addOrdered(new MacComputer(model, price, isLaptop));
				}
			}
			fileScan.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		consoleScan.close();
		
		
		System.out.println();
			
		// Print all Computer objects onto the screen
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		// TEST remove(Computer target) method
		System.out.println("TEST remove(Computer target) METHOD!\n");
		
		WindowsComputer dell = new WindowsComputer("Dell16Plus", 71999, 16);
		System.out.println("1.	Was Computer [" + dell + "] removed?:  " + computerList.remove(dell) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		MacComputer macMini = new MacComputer("AppleMacMini", 58399, false);
		System.out.println("2.	Was Computer [" + macMini + "] removed?:  " + computerList.remove(macMini) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		WindowsComputer acerNitro = new WindowsComputer("AcerNitroV15GamingLaptop", 104999, 16);
		System.out.println("3.	Was Computer [" + acerNitro + "] removed?:  " + computerList.remove(acerNitro) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		WindowsComputer asus = new WindowsComputer("AsusVivobook14", 44999, 16);	// not in computers.txt
		System.out.println("4.	Was Computer [" + asus + "] removed?:  " + computerList.remove(asus) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		// TEST remove(int index) method
		System.out.println("TEST remove(int index) METHOD!\n");
		
		int removeIndex = 1; // remove head node
		System.out.println("1.	Was Computer at index " + removeIndex + " removed? [head node]:  " + computerList.remove(removeIndex) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		removeIndex = 7;
		System.out.println("2.	Was Computer at index " + removeIndex + " removed?:  " + computerList.remove(removeIndex) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		removeIndex = computerList.size();
		System.out.println("3.	Was Computer at index " + removeIndex + " removed? [tail node]:  " + computerList.remove(removeIndex) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		removeIndex = computerList.size()+1;
		System.out.println("4.	Was Computer at index " + removeIndex + " removed? [ > list size ]:  " + computerList.remove(removeIndex) + "\n");
		System.out.println("Print out all " + computerList.size() + " elements in ComputerOrderedList: \n");
		computerList.display();
		System.out.println();
		
		// TEST indexOf method
		System.out.println("TEST indexOf METHOD!\n");
		
		MacComputer mac = new MacComputer("AppleMacBookAir13", 99900, true);
		System.out.println("1.	Index Of [" + mac + "] is " + computerList.indexOf(mac));
		
		dell = new WindowsComputer("Dell16Plus", 81999, 32);
		System.out.println("2.	Index Of [" + dell + "] is " + computerList.indexOf(dell));
		
		asus = new WindowsComputer("AsusVivobook14", 44999, 16);	// not in computers.txt
		System.out.println("3.	Index Of [" + asus + "] is " + computerList.indexOf(asus));
		
		System.out.println();
		
		// TEST get method
		System.out.println("TEST get METHOD!\n");
		
		int index = 1;
		System.out.println("1.	Return Computer stored at index " + index + ": " + computerList.get(index));
		
		index = 5;
		System.out.println("2.	Return Computer stored at index " + index + ": " + computerList.get(index));
		
		index = computerList.size()-1;
		System.out.println("3.	Return Computer stored at index " + index + ": " + computerList.get(index));
		
		index = computerList.size()+1;	// index outside of list
		System.out.println("4.	Return Computer stored at index " + index + " (index outside of list) : " + computerList.get(index));
		System.out.println("\n");
		
		
	}
}

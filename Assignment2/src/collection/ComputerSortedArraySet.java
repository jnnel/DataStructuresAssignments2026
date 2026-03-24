package collection;

import computer.Computer;
import computer.MacComputer;
import computer.WindowsComputer;

/**
 * Sorted collection that disallows duplicate elements
 * and maintains a sorted order.
 */
public class ComputerSortedArraySet {

	private Computer[] computerAry;	// the array of Computers in this set class
	private Integer max;	// the maximum allowable number of Computers in the set
	
	/**
	 * Constructor for ComputerSortedArraySet.
	 * @param Integer max
	 */
	public ComputerSortedArraySet(Integer max) {
		
		this.max = max;
		this.computerAry = new Computer[max];
	}
	
	/**
	 * Inserts an object of type Computer in the set 
	 * such that (1) the method maintains the sorted 
	 * order according to the compareTo method and
	 * (2) duplicates are not allowed.
	 * @param Computer c
	 */
	public void insert(Computer c) {
		
		int compare;
		for(int i = 0; i < max; i++) {
			
			if(computerAry[i] == null) {
				computerAry[i] = c;
				return;
			}
			else {
				
				if(isEqual(c, computerAry[i])) {
					return;
				}
				
				compare = c.compareTo(computerAry[i]);
				if(compare <= 0) {
					
					// shift elements i,... to the right by one place
					shiftRightOne(i);
					
					// then add new element to array
					computerAry[i] = c;
					return;
				}
			}
		}
	}
	
	/**
	 * Return the number of Computer instances in the SortedArraySet collection.
	 * @return
	 */
	public int size() {
		
		int numComputers = 0;
		for(Computer c: computerAry) {
			if(c != null) {
				numComputers++;
			}
		}
		
		return numComputers;
	}
	
	/**
	 * Returns a String representation of the collection that
	 * includes all Computers. The output is in a tabular format.
	 * WindowsComputer and MacComputer instances will have 
	 * all of 3 of their instance variables included, while
	 * Computer instances only will have 2 variables included.
	 */
	@Override
	public String toString() {
		
		String output = "";
		for(Computer c: computerAry) {
			if(c != null) {
				if(c instanceof WindowsComputer) {
					output += (((WindowsComputer) c).toString() + "\n");
				}
				else if(c instanceof MacComputer) {
					output += (((MacComputer) c).toString() + "\n");
				}
				else {
					output += (c.toString() + "\n");
				}
			}
		}
		
		return output;
	}
	
	/**
	 * Returns an int that represents the array index at
	 * which the input Computer c is found, or -1 if the
	 * Computer c is not found. The equality of Computers
	 * is determined by the equals method.
	 * @param Computer c
	 * @return
	 */
	public int indexOf(Computer c) {
		
		int index = -1;
		for(int i = 0; i < max; i++) {
			if(computerAry[i] != null) {
				if(isEqual(c, computerAry[i])) {
					index = i;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Search the collection for a Computer
	 * that equals the input Computer c and, if found,
	 * remove this Computer c from the set. Return true if
	 * Computer c is removed and false otherwise. This 
	 * method will maintain the sorted order.
	 * @param Comoputer c
	 * @return boolean
	 */
	public boolean remove(Computer c) {
		
		boolean isRemoved = false;
		for(int i = 0; i < max; i++) {
			
			if(computerAry[i] != null) {
				if(isEqual(c, computerAry[i])) {
					// remove Computer equal to c
					computerAry[i] = null;
					
					// shift elements i+1,... to the left by one place
					shiftLeftOne(i);
					
					isRemoved = true;
					return isRemoved;
				}
			}
		}
		
		return isRemoved;
		
	}
	
	/**
	 * Return a Computer that is stored at position,
	 * index, in the set or null if index is beyond
	 * the size of the collection. This method does
	 * not delete this Computer from the set.
	 * @param int index
	 * @return Computer located at index
	 */
	public Computer grab(int index) {
		
		if(index > max-1) {
			return null;
		}
		else {
			return computerAry[index];
		}
	}
	
	/**
	 * Return as output a ComputerSortedArraySet that contains
	 * only the specific subitem type. Assume 1 represents
	 * a WindowsComputer, 2 represents a MacComputer, and
	 * 3 represents Computers that are neither WindowComputers
	 * nor MacComputers. Then, for example, categorySet(1)
	 * returns a set of items of type WindowsComputer. The method
	 * returns null if the input is any number other than 
	 * 1, 2, or 3. The method returns an empty set if there 
	 * are no items of the given type.
	 * @param type
	 * @return
	 */
	public ComputerSortedArraySet categorySet(int type) {
		
		if (type != 1 && type != 2 && type != 3) {
			return null;
		}
		
		ComputerSortedArraySet set = new ComputerSortedArraySet(max);
		for(Computer c: computerAry) {
			if(c != null) {
				if(type == 1 && c instanceof WindowsComputer) {
					set.insert(c);
				}
				else if(type == 2 && c instanceof MacComputer) {
					set.insert(c);
				}
				else if(type == 3 && 
						!(c instanceof WindowsComputer) &&
						!(c instanceof MacComputer)) {
					set.insert(c);
				}
			}
		}
		
		return set;
	}
	
	/**
	 * Checks for equality of two Computer objects, checking the
	 * class of each object, using the appropriate equals method.
	 * @param Computer c1
	 * @param Computer c2
	 * @return boolean (true if equal, false if not)
	 */
	private boolean isEqual(Computer c1, Computer c2) {
		
		boolean equal = false;
		boolean isWindows1 = c1 instanceof WindowsComputer;
		boolean isWindows2 = c2 instanceof WindowsComputer;
		boolean isMac1 = c1 instanceof MacComputer;
		boolean isMac2 = c2 instanceof MacComputer;
		
		
		if(c1.equals(c2)) {
			if(isWindows1) {
				if(isWindows2) {	// if both c1 and c2 are both Windows, use WindowsComputer equals
					WindowsComputer w1 = (WindowsComputer)c1;
					WindowsComputer w2 = (WindowsComputer)c2;
					equal = w1.equals(w2);
				}
			}
			else if(isMac1) {
				if(isMac2) {	// if c1 and c2 are both Macs, use MacComputer equals
					MacComputer m1 = (MacComputer)c1;
					MacComputer m2 = (MacComputer)c2;
					equal = m1.equals(m2);
				}
			}
			else if(!(isWindows1 || isWindows2 ||
					isMac1 || isMac2)) {	// neither c1 nor c2 is a subclass, so they must be equal
				equal = true;
			}
		}
		
		return equal;
	}
	
	// should an error be thrown if array full or quietly allow it? ask professor
	/**
	 * Shift elements in instance array computerAry to the right by one position, 
	 * starting at int index.
	 * @param index
	 */
	private void shiftRightOne(int index) {
		for(int i = max-2; i >= index; i--) {
			computerAry[i+1] = computerAry[i];
			computerAry[i] = null;
		} 
	}
	
	/**
	 * Shift elements in instance array computerAry to the left by one position,
	 * starting at int index.
	 * @param index
	 */
	private void shiftLeftOne(int index) {
		for(int i = index; i < max-1; i++) {
			computerAry[i] = computerAry[i+1];
			computerAry[i+1] = null;
		}
	}
}

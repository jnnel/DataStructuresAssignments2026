package list;

import computer.Computer;
import computer.MacComputer;
import computer.WindowsComputer;

/**
 * This class maintains two ComputerOrderedLists, one
 * ordered list for WindowsComputers and the other 
 * ordered list for MacComputers, where WindowsComputer
 * and MacComputer are subclasses of Computer.
 */
public class ComputerTypesLists {

	private ComputerOrderedList windowsComputerList; // ordered list that only stores WindowsComputers
	private ComputerOrderedList macComputerList; // ordered list that only stores MacComputers
	
	public ComputerTypesLists() {
		
		windowsComputerList = new ComputerOrderedList();
		macComputerList = new ComputerOrderedList();
	}
	
	/**
	 * Adds Computer element to the correct position of
	 * the correct list, of the two ComputerOrderedLists
	 * in this class. If the Computer element is not a
	 * WindowsComputer or a MacComputer, this method
	 * throws a new ClassNotFoundException and catches it.
	 * @param Computer element
	 */
	public void add(Computer element) {
		
		if(element instanceof WindowsComputer) {
			windowsComputerList.addOrdered(element);
		}
		else if(element instanceof MacComputer) {
			macComputerList.addOrdered(element);
		}
		else {
			try {
				throw new ClassNotFoundException();
				
			} catch(ClassNotFoundException e) {
				System.out.println("Element not instance of either subclass.");
			}
		}
	}
	
	/**
	 * Returns the number of nodes in the given list.
	 * @param ComputerOrderedList iol
	 * @return number of nodes, an int
	 */
	public int sizeOf(ComputerOrderedList iol) {
		
		if(iol == null) {
			return 0;
		}
		else {
			return iol.size();
		}
	}
	
	/**
	 * Display the contents of the specified list.
	 * @param ComputerOrderedList iol
	 */
	public void display( ComputerOrderedList iol ) {
		iol.display();
	}
	
	/**
	 * Adds Computer element to the correct list
	 * at the given index where the head node is 
	 * at index 1. If index is greater than the list 
	 * size, then the element is added as the last 
	 * element in the linked list. The method does not 
	 * do anything if index is less than 1, or if element
	 * is not of subclass WindowsComputer or MacComputer.
	 * @param int index
	 * @param Computer element
	 * @return boolean: true if Computer is added and false otherwise.
	 */
	public boolean add( int index, Computer element ) {
		
		boolean added = false;
		if( element instanceof WindowsComputer ) {
			added = windowsComputerList.add(index, element);
		}
		else if( element instanceof MacComputer ) {
			added = macComputerList.add(index, element);
		}
		
		return added;
	}
	
	/**
	 * Removes on occurrence of Computer target is there 
	 * is at least one instance of target in the correct
	 * list. The method returns true if Computer target is
	 * removed and false otherwise. All other computers in 
	 * the list should remain in the same order.
	 * @param Computer target
	 * @return boolean
	 */
	public boolean remove( Computer target ) {
		
		boolean removed = false;
		if( target instanceof WindowsComputer) {
			removed = windowsComputerList.remove(target);
		}
		else if( target instanceof MacComputer ) {
			removed = macComputerList.remove(target);
		}
		
		return removed;
	}
	
	/**
	 * Removes the Computer at the position index in the
	 * ComputerOrderedList iol where the head node is at
	 * index 1. The method returns true if an element is
	 * removed and false if no element is removed because
	 * index is negative or beyond the list length. This
	 * method does not change the order of the other
	 * elements in the list.
	 * @param ComputerOrderedList iol
	 * @param int index
	 * @return boolean
	 */
	public boolean remove( ComputerOrderedList iol, int index ) {
		
		boolean removed = false;
		if( iol == null ) {
			return removed;
		}
		else {
			removed = iol.remove(index);
			return removed;
		}
	}
	
	/**
	 * Returns the index of the first occurrence of 
	 * target in the appropriate ordered list. Returns
	 * -1 if Computer target is not in the list.
	 * @param Computer target
	 * @return int
	 */
	public int indexOf( Computer target ) {
		
		int index = -1;
		
		if( target instanceof WindowsComputer ) {
			index = windowsComputerList.indexOf(target);
		}
		else if( target instanceof MacComputer ) {
			index = macComputerList.indexOf(target);
		}
		
		return index;
	}
	
	/**
	 * Returns the ComputerOrderedList of the given type
	 * ('a' for a Windows ComputerOrderedList or 'b' for a
	 * Mac ComputerOrderedList). The character should be
	 * case-insensitive. Return null if type is invalid.
	 * @param char type
	 * @return ComputerOrderedList or null
	 */
	public ComputerOrderedList getList( char type ) {
		
		char typeLower = Character.toLowerCase(type);
		
		if( typeLower == 'a') {
			return windowsComputerList;
		}
		else if( typeLower == 'b') {
			return macComputerList;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Returns the Computer at position index in the 
	 * specified list. Returns null if index is less
	 * than 1 or greater than the size of the list.
	 * @param ComputerOrderedList iol
	 * @param int index
	 * @return Computer or null
	 */
	public Computer get( ComputerOrderedList iol, int index ) {
		
		if( iol == null ) {
			return null;
		}
		else {
			Computer c = iol.get(index);
			return c;
		}
	}
	
	/**
	 * Find the index that parameter Computer c *would*
	 * be at *if* Computer c is added the appropriate list, 
	 * Windows or Mac ComputerOrderedList This method does
	 * not actually add Computer c either list. Note: a computer with
	 * a value equal to a node in a list will never become the list's tail.
	 * @param Computer c
	 * @return int
	 */
	public int determineIndex(Computer c) {
	
		int index = -1;
		if( c instanceof WindowsComputer ) {
			index = windowsComputerList.determineIndex(c);
			return index;
		}
		else if( c instanceof MacComputer ) {
			index = macComputerList.determineIndex(c);
			return index;
		}
		
		return index;
	}
}

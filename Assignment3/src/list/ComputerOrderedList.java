package list;

import computer.Computer;
import computer.MacComputer;
import computer.WindowsComputer;

// first index is 1
/**
 * This class is a collection class that uses
 * a linked list to store a collection of 
 * instances of class Computer. The
 * ComputerOrderedList supports operations to 
 * add/remove/read items from a specific
 * position in the list. 
 * Note: the first index value in the
 * ComoputerOrderdList
 */
public class ComputerOrderedList {
	
	private ComputerNode head;	// a reference variable to the head of the list
	private ComputerNode tail;	// a reference variable to the tail of the list
	
	public ComputerOrderedList() {
		
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Adds a Computer instance to its proper order
	 * in the list.
	 * @param Computer c
	 */
	public void addOrdered(Computer c) {
		
		int index = determineIndex(c); // find the index at which to add c to the list
		add(index, c);
	}
	
	/**
	 * Adds a Computer instance to the end of the list.
	 * @param Computer c
	 */
	public void add(Computer c) {
		
		if(tail == null) {
			head = new ComputerNode(c, null);
			tail = head;
		}
		else {
			tail.setLink(new ComputerNode(c, null));
			tail = tail.getLink();
		}
	}
	
	/**
	 * Access the number of nodes in the ComputerOrderedList.
	 * @return the number of nodes in the list
	 */
	public int size() {
		
		ComputerNode cursor = head;
		int numNodes = 0;
		while(cursor != null) {
			numNodes++;
			cursor = cursor.getLink();
		}
		return numNodes;
	}
	
	/**
	 * Adds a Computer to the list at the given index,
	 * where the head node is at index 1. If index is greater
	 * than the list size, then the Computer is added as the
	 * last element in the linked list. The method does not do
	 * anything if index is less than 1.
	 * @param int index
	 * @param Computer c
	 * @return boolean. The method returns true if the Computer
	 * is added and false otherwise.
	 */
	public boolean add(int index, Computer c) {
		
		if( index < 1 ) {	// cannot add c with index < 1
			return false;
		}
		else if( index == 1 ) {	// add c as the head
			
			head = new ComputerNode(c, head);
			
			if(size() == 1) {
				tail = head;
			}
			
			return true;

		}
		else if ( index > size() ) {	// add c at the end of the list (to become the new tail)
			
			add(c);
			return true;
		}
		else {	// add c somewhere between the head and the tail of the list
			
			if ( head == null) {
				return false;
			}
			
			ComputerNode cursor = head;
			int count = 2; // the node after the head is the earliest c can be added
			while( cursor.getLink() != null ) {

				if( count == index ) {
					cursor.setLink( new ComputerNode(c, cursor.getLink()) );
					return true;
				}
				
				cursor = cursor.getLink();
				count++;
			}
			
			return false;
		}
	}
	
	/**
	 * Displays the contents of the list, one Computer per line.
	 */
	public void display() {
		
		ComputerNode cursor = head;
		while(cursor != null) {
			System.out.println(cursor.getData());
			cursor = cursor.getLink();
		}
	}
	
	/**
	 * Removes one occurrence of target Computer from the list, if any. 
	 * All other Computers in the list should remain in the same order.
	 * @param Computer target
	 * @return boolean. The method returns true if a Computer is
	 * removed and false otherwise.
	 */
	public boolean remove(Computer target) {
		
		if( target == null || head == null) {
			return false;
		}
		
		if( isEqual( target, head.getData()) ) {
			head = head.getLink();
			return true;
		}
		
		ComputerNode cursor = head;
		ComputerNode nextNode = null;
		while( cursor.getLink() != null ) {
			
			nextNode = cursor.getLink();
			if( isEqual( target, nextNode.getData()) ) {
				cursor.setLink(nextNode.getLink());
				return true;
			}
			
			cursor = cursor.getLink();
		}
		
		return false;
	}
	
	/**
	 * Removes the Computer located at position index in the list,
	 * where the head node is at index 1.
	 * @param int index
	 * @return boolean. The method returns true if the Computer
	 * is added and false otherwise.
	 */
	public boolean remove(int index) {
		
		if( index < 1 || index > size() || head == null) {
			return false;
		}
		
		if( index == 1 ) {	// index of head node
			head = head.getLink();
			return true;
		}
		else {
			
			ComputerNode cursor = head;
			int nextIndex = 2; // index of the next node after cursor
			while( cursor.getLink() != null && nextIndex <= index) {
				
				if( index == nextIndex ) {
					cursor.setLink(cursor.getLink().getLink());
					return true;
				}
				
				cursor = cursor.getLink();
				nextIndex++;
			}
		}
		
		return false;
	}
	
	/**
	 * Get the index of a target Computer.
	 * @param Computer target
	 * @return the int index of the first occurrence of 
	 * Computer target if any and returns -1 if target is
	 * not in the list.
	 */
	public int indexOf(Computer target) {
		
		if(target == null || head == null) {
			return -1;
		}
		
		if( isEqual( target, head.getData()) ) {
			return 1;
		}
		
		ComputerNode cursor = head;
		ComputerNode nextNode = null;
		int targetIndex = 2; // index of nextNode
		while( cursor.getLink() != null ) {
			
			nextNode = cursor.getLink();
			if( isEqual( target, nextNode.getData()) ) {
				return targetIndex;
			}
			
			cursor = cursor.getLink();
			targetIndex++;
		}
		return -1;
	}
	
	/**
	 * Get the Computer at position index in the list.
	 * @param int index
	 * @return Computer at position index or returns 
	 * null if index is less than 1 or greater than
	 * the size of the list.
	 */
	public Computer get(int index) {
		
		if(index < 1 || index > size() || head == null) {
			return null;
		}
		
		ComputerNode cursor = head;
		int getIndex = 1;
		while( cursor != null) {
			
			if(index == getIndex) {
				return cursor.getData();
			}
			
			cursor = cursor.getLink();
			getIndex++;
		}
		
		return null;
	}
	
	// return the index that parameter Computer c would
	// have if it was added to the ComputerOrderedList
	
	// note: a computer with a value equal to a node in the list will never become the tail!
	/**
	 * Find the index that parameter Computer c *would*
	 * be at *if* Computer c is added the list. This method
	 * does not add Computer c the list. Note: a computer with
	 * a value equal to a node in the list will never become the list's tail.
	 * @param Computer c
	 * @return int
	 */
	public int determineIndex(Computer c) {
		
		if( head == null ) {
			return 1;
		}
		else {
			if( c.compareTo(head.getData()) <= 0 ) {
				return 1;
			}
			
			ComputerNode cursor = head;
			ComputerNode nextNode = null;
			int index = 2;
			while( cursor.getLink() != null ) {
				
				nextNode = cursor.getLink();
				if( c.compareTo(nextNode.getData()) <= 0 ) {
					return index;
				}
				
				cursor = cursor.getLink();
				index++;
			}
			
			return index;
		}
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
}

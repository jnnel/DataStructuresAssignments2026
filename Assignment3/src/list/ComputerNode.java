package list;

import computer.Computer;

/**
 * This class represents a node in a linked list
 * that stores the Computer class and its subclasses.
 */
public class ComputerNode {
	
	private Computer data;	// the computer item stored at this Node
	private ComputerNode link; // a reference to the next node in the linked list
	
	public ComputerNode( Computer c, ComputerNode n ) {
		
		this.data = c;
		this.link = n;	
	}
	
	/**
	 * Get the Computer instance stored in this ComputerNode
	 * @return Computer class
	 */
	public Computer getData() {
		return data;
	}
	
	/**
	 * Get the link to the next ComputerNode
	 * @return reference to next ComputerNode
	 */
	public ComputerNode getLink() {
		return link;
	}
	
	/**
	 * Set the Computer instance stored in
	 * this ComputerNode
	 * @param Computer c
	 */
	public void setData(Computer c) {
		this.data = c;
	}
	
	/**
	 * Set the link to the next ComputerNode
	 * @param ComputerNode n
	 */
	public void setLink(ComputerNode n) {
		this.link = n;
	}
}

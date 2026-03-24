package computer;

/**
 * Class representation of a computer (laptop, personal computer, mobile device, etc.)
 */
public class Computer implements Comparable<Computer>{

	String model;	// include company and computer model name
	Integer price;	// price in cents
	
	/**
	 * Create a new computer.
	 * @param s The model name of the computer.
	 * @param n The price of the computer in cents.
	 */
	public Computer(String s, Integer n) {
		this.model = s;
		this.price = n;
	}
	
	/**
	 * Get the String representation of this Computer.
	 * @return model name of the computer as a String.
	 */
	public String getModel() {
		
		return model;
	}
	
	/**
	 * Get the price of the Computer.
	 * @return the price of the computer in cents.
	 */
	public Integer getPrice() {
		
		return price;
	}
	
	/**
	 * Set the model name of the Computer.
	 * @param s	Intended model name as a String.
	 */
	public void setModel(String s) {
		
		this.model = s;
	}
	
	/**
	 * Set the price for the Computer.
	 * @param n Intended price of computer in cents, as an Integer.
	 */
	public void setPrice(Integer n) {
		
		this.price = n;
	}
	
	/**
	 * Return String representation of Computer.
	 * @return one line of model name and price, tab-separated.
	 */
	@Override
	public String toString() {
		
		String output = model + "	" + price;
		return output;
	}
	
	/**
	 * Compare this Computer to another Computer for equality.
	 * @param o reference to Computer o to compare to this Computer.
	 * @return boolean (Is this Computer equal to Computer o? true/false)
	 */
	public boolean equals(Computer o) {
		
		boolean equal = false;
		if(this.model.equalsIgnoreCase(o.model) &&
				this.price.equals(o.price)) {
			equal = true;
		}
		
		return equal;
	}
	/**
	 * Compare this Computer to another Computer.
	 * @param c	reference to Computer c that is compared to this Computer.
	 * @return	integer < 0 if this computer < c, integer == 0 if this computer == c,
	 * 			or integer > 0 if this computer > 0.
	 * 			
	 * 			The comparisons are made component-wise between
	 * 			each instance variable, model and price. 
	 * 
	 * 			The Computers are sorted on the model attribute first (ignoring case), and
	 * 			then on the price attribute.
	 */
	@Override
	public int compareTo(Computer c) {
		int compareModel = this.model.compareToIgnoreCase(c.getModel());
		
		// if this.model == c.model, 
		if(compareModel == 0) {
			int comparePrice = this.price.compareTo(c.getPrice());
			
			return comparePrice;
		}
		else { // if this.model < c.model or this.model > c.model
			return compareModel;
		}
	}
}

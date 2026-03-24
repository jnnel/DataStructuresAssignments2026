package computer;

/**
 * Class representation of a MacComputer, which
 * is a subclass of Computer.
 */
public class MacComputer extends Computer{

	Boolean isLaptop;	// Is this a Mac laptop or desktop?
	
	/**
	 * Create a new instance of MacComputer
	 * @param s The model name of the computer (String)
	 * @param n1 The price of the computer in cents (Integer)
	 * @param n2 A Boolean wrapper variable indicating whether this
	 * 			 MacComputer is a laptop.
	 */
	public MacComputer(String s, Integer n1, Boolean n2) {
		
		super(s, n1);	// call superclass Computer's constructor
		this.isLaptop = n2;
	}
	
	/**
	 * Return Boolean variable indicating whether this 
	 * MacComputer is a laptop (true or false)
	 * @return Boolean isLaptop
	 */
	public Boolean getIsLaptop() {
		
		return isLaptop;
	}
	
	/**
	 * Set Boolean variable indicating whether this 
	 * MacComputer is a laptop (true or false)
	 * @param i
	 */
	public void setIsLaptop(Boolean i) {
		
		this.isLaptop = i;
	}
	
	/**
	 * Return String representation of WindowsComputer.
	 * @return one line of String model name, Integer price,
	 * 		   and Boolean isLaptop (tab-separated).
	 */
	@Override
	public String toString() {
		
		String output = super.toString() + "	" + isLaptop;
		return output;
	}
	
	/**
	 * Check this MacComputer and another MacComputer for equality.
	 * @param m reference to MacComputer m to compare to this Computer.
	 * @return boolean (Is this MacComputer equal to MacComputer m? true/false)
	 */
	public boolean equals(MacComputer m) {
		
		boolean macComputerEqual = false;
		boolean computerEqual = super.equals(m);
		
		if(computerEqual) {
			if(this.isLaptop.equals(m.getIsLaptop())) {
				macComputerEqual = true;
			}
		}
		
		return macComputerEqual;
	}
}

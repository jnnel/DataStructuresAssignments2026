package computer;

/**
 * Class representation of a WindowsComputer, which
 * is a subclass of Computer.
 */
public class WindowsComputer extends Computer {

	Integer ram;	// Random Access Memory (RAM) in gigabytes
	
	/**
	 * Create a new instance of WindowsComputer
	 * @param s The model name of the computer (String)
	 * @param n1 The price of the computer in cents (Integer)
	 * @param n2 The RAM of the computer (Integer)
	 */
	public WindowsComputer(String s, Integer n1, Integer n2) {
		
		super(s, n1);	// call superclass Computer's constructor
		this.ram = n2;
	}
	
	/**
	 * Get the RAM on the WindowsComputer as an Integer.
	 * @return Integer RAM
	 */
	public Integer getRAM() {
		
		return ram;
	}
	
	/**
	 * Set the RAM of the WindowsComputer as an Integer.
	 * @param Integer r
	 */
	public void setRAM(Integer r) {
		
		this.ram = r;
	}
	
	/**
	 * Return String representation of WindowsComputer.
	 * @return one line of model name, price, and RAM (tab-separated).
	 */
	@Override
	public String toString() {
		
		String output = super.toString() + "	" + ram;
		return output;
	}
	
	/**
	 * Check this WindowsComputer and another WindowsComputer for equality.
	 * @param w reference to WindowsComputer w to compare to this Computer.
	 * @return boolean (Is this WindowsComputer equal to WindowsComputer w? true/false)
	 */
	public boolean equals(WindowsComputer w) {
		
		boolean WindowsComputerEqual = false;
		boolean computersEqual = super.equals(w);
		
		if(computersEqual) {
			if(this.ram.equals(w.getRAM())) {
				WindowsComputerEqual = true;
			}
		}
		
		return WindowsComputerEqual;
	}
}
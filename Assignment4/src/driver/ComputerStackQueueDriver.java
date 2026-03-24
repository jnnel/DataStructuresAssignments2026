package driver;

import java.util.Stack;

import computer.Computer;
import computer.MacComputer;
import computer.WindowsComputer;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Class contains static methods that manipulate elements
 * in Stack and Queue of the Java Collection. A main method
 * will run unit tests for each public static method.
 */
public class ComputerStackQueueDriver {
	
	/**
	 * This method returns an integer representation of the 
	 * price instance variable of the Computer class for 
	 * all computers in the Stack s. The most significant
	 * digit represents the top of the stack and the least
	 * significant digit represents the bottom of the stack.
	 * @param Stack<Computer> s
	 * @return An integer representation of price instance variables
	 * of the Computers in Stack<Computer> s.
	 */
	public static int stackToInt( Stack<Computer> s ) {
		
		Stack<Computer> copy = new Stack<Computer>();
		
		Computer item;
		String intString = "";
		while(!s.isEmpty()) {
			item = s.pop();
			intString += item.getPrice();
			copy.push(item);
		}
		
		while(!copy.isEmpty()) {
			s.push(copy.pop());
		}
 		
		return Integer.parseInt(intString);
	}
	
	/**
	 * This method pops *count* items from Stack<Computer> s. 
	 * The input stack is changed because some Computers are popped.
	 * @param s - Stack<Computer>, a stack of Computer instances
	 * @param count - integer representing the number of Computers
	 * to pop from the stack.
	 * @return Sum of the integer attribute, price, of the popped 
	 * Computers. If the stack has less than count Computers, this method
	 * pops the entire stack and returns -1. 
	 */
	public static int popSome( Stack<Computer> s, int count ) {
		
		if( s.isEmpty() ) {
			 
			if( count > 0 ) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else {
			
			if( count <= 0 ) { // should I throw an error if count < 0?
				return 0;
			}
			else {
				
				Computer computer;
				int sumOfPrices = 0;
				for(int i = 0; i < count; i++) {
					
					if( s.isEmpty() ) { // popped all Computers from stack ==> count > number of Computers in stack
						return -1;
					}
					else {
						computer = s.pop();
						sumOfPrices += computer.getPrice();
					}
				}
				
				return sumOfPrices;
			}
			
		}
	}
	
	/**
	 * Removes all occurrences of the input computer from
	 * the stack and returns the number of extracted computers.
	 * All other computers in the stack remain unchanged and in
	 * the same order.
	 * @param s - Stack<Computer>, a stack of Computer instances
	 * @param c - Computer instance; all computers in the stack equivalent to this
	 * are removed. 
	 * @return The number of extracted Computers from the Stack<Computer> s
	 */
	public static int extractFromStack( Stack<Computer> s, Computer c ) {
		
		Stack<Computer> copy = new Stack<Computer>();
		int numExtract = 0;
		
		Computer stackComputer;
		while( !s.isEmpty() ) {
			stackComputer = s.pop();
			if( !isEqual(stackComputer, c) ) {
				copy.push(stackComputer);
			}
			else {
				numExtract++;
			}
		}
		
		while( !copy.isEmpty() ) {
			s.push(copy.pop());
		}
		
		return numExtract;
		
	}
	
	/**
	 * Determines if the two Stacks s1 and s2 are equal. The two
	 * stacks are equal if they have the same Computers in the same order.
	 * @param s1 - Stack
	 * @param s2 - Stack
	 * @return Returns boolean true or false based on whether the
	 * two input stacks are equal.
	 */
	public static boolean equalStacks( Stack<Computer> s1, Stack<Computer> s2 ) {
		
		boolean isEqual = true;
		
		if( s1.isEmpty() && s2.isEmpty() ) {
			return isEqual;
		}
		else if( s1.isEmpty() || s2.isEmpty() ) {
			return !isEqual;
		}
		else if( s1.size() != s2.size() ) {
			return !isEqual;
		}
		
		Stack<Computer> copy1 = new Stack<Computer>();
		Stack<Computer> copy2 = new Stack<Computer>();
		
		Computer c1; // reference to a Computer from Stack s1
		Computer c2; // reference to a Computer from Stack s2
		while( isEqual && !s1.isEmpty() && !s2.isEmpty() ) {
			
			c1 = s1.pop();
			c2 = s2.pop();
			
			if(!isEqual(c1, c2)) {
				isEqual = false;
			}
			
			copy1.push(c1);
			copy2.push(c2);
		}
		
		while( !copy1.isEmpty() && !copy2.isEmpty() ) {
		
			s1.push(copy1.pop());
			s2.push(copy2.pop());
		}
		
		return isEqual;
		
	}
	
	/**
	 * Updates the input queue, q, by replacing each occurrence
	 * of Computer oldVal with Computer newVal. All other values 
	 * in the input queue remain the same and in the same order.
	 * @param q - Queue, with type parameter Computer
	 * @param oldVal - Computer, to be replaced
	 * @param newVal - Computer, which will replace
	 * @return Returns the number of replaced Computers
	 */
	public static int replace( Queue<Computer> q, Computer oldVal,
								Computer newVal ) {
		
		Queue<Computer> copy = new LinkedList<Computer>();
		
		Computer item;
		int numReplaced = 0;
		while( !q.isEmpty() ) {
			item = q.remove();
			
			if( isEqual(item, oldVal) ) {
				copy.add(newVal);
				numReplaced++;
			}
			else {
				copy.add(item);
			}
		}
		
		while( !copy.isEmpty() ) {
			q.add(copy.remove());
		}
		
		return numReplaced;
	}
	
	/**
	 * Swaps the contents of the Stack s and the Queue q 
	 * such that the top element in the stack becomes the
	 * front element in the queue, and the front element in
	 * the queue becomes the bottom element in the stack.
	 * @param s - Stack of Computers
	 * @param q - Queue of Computers
	 */
	public static void swap( Stack<Computer> s, Queue<Computer> q ) {
		
		// save initial queue size, so the proper number 
		// of elements are removed and pushed to the stack
		int queueSwapCount = q.size();
		
		// if stack isn't empty, pop elements off and add to queue
		while( !s.isEmpty() ) {
			q.add(s.pop());
		}
		
		// remove the original contents of queue and push them onto the stack
		while( queueSwapCount > 0 ) {
			s.push(q.remove());
			queueSwapCount--;
		}
	}
	
	/**
	 * Splits the contents of the input Queue q into an array
	 * of Queues, with the first array entry being a Queue of WindowsComputer
	 * and the second array entry being a Queue of MacComputer. After
	 * the method, the input Queue q will only include the computers 
	 * that are not of type WindowsComputer nor of type MacComputer, two subsets
	 * of class Computer.
	 * @param q - Queue of Computers
	 * @return array containing two Queues, a Queue of WindowsComputer and a Queue of 
	 * MacComputer, respectively
	 */
	@SuppressWarnings("unchecked")
	public static Queue<Computer>[] split( Queue<Computer> q ) {
		
		Queue<Computer> windowsQ = new LinkedList<Computer>();
		Queue<Computer> macQ = new LinkedList<Computer>();
		Queue<Computer> computerQ = new LinkedList<Computer>();
		
		Computer item;
		while( !q.isEmpty() ) {
			item = q.remove();
			
			if( item instanceof WindowsComputer ) {
				windowsQ.add(item);
			}
			else if( item instanceof MacComputer ) {
				macQ.add(item);
			}
			else {
				computerQ.add(item);
			}
		}
		
		// add computers that are neither WindowsComputer
		// nor MacComputer back to the original queue
		while( !computerQ.isEmpty() ) {
			q.add(computerQ.remove());
		}
		
		Queue<Computer>[] queueArray = new Queue[2];
		queueArray[0] = windowsQ;
		queueArray[1] = macQ;
		
		return queueArray;
	}
	
	/**
	 * Creates a String representation of input Stack, such that
	 * the Stack elements are displayed vertically, with a new line
	 * between each Computer element, ordered from the top of the stack
	 * at the top of the String to the bottom of the stack at the bottom
	 * of the String.
	 * @param s - Stack of Computer
	 * @return String representation of input Stack
	 */
	private static String displayStack( Stack<Computer> s ) {
		
		if( s.isEmpty() ) {
			return null;
		}
		
		Stack<Computer> copy = new Stack<Computer>();
		
		String output = "";
		Computer item;
		while( !s.isEmpty() ) {
			item = s.pop();
			
			if( item instanceof WindowsComputer ) {
				output += (((WindowsComputer)item).toString() + "\n");
			}
			else if( item instanceof MacComputer ) {
				output += (((MacComputer)item).toString() + "\n");
			}
			else if( item instanceof Computer ) {
				output += (item.toString() + "\n");
			}
			
			copy.push(item);
		}
		
		while( !copy.isEmpty() ) {
			s.push(copy.pop());
		}
		
		return output;
	}
	
	/**
	 * Creates a String representation of input Queue, such that
	 * the Queue elements are displayed vertically, with a new line
	 * between each Computer element, ordered from the front of the queue
	 * at the top of the String to the rear of the queue at the bottom
	 * of the String.
	 * @param q - Stack of Computer
	 * @return String representation of input Stack
	 */
	private static String displayQueue( Queue<Computer> q) {
		
		if( q.isEmpty() ) {
			return null;
		}
		
		Queue<Computer> copy = new LinkedList<Computer>();
		
		String output = "";
		Computer item;
		while( !q.isEmpty() ) {
			item = q.remove();
			
			if( item instanceof WindowsComputer ) {
				output += (((WindowsComputer)item).toString() + "\n");
			}
			else if( item instanceof MacComputer) {
				output += (((MacComputer)item).toString() + "\n");
			}
			else if( item instanceof Computer) {
				output += (item.toString() + "\n");
			}
			
			copy.add(item);
		}
		
		while( !copy.isEmpty() ) {
			q.add(copy.remove());
		}
		
		return output;
		
	}
	
	/**
	 * Checks for equality of two Computer objects, checking the
	 * class of each object, using the appropriate equals method.
	 * @param Computer c1
	 * @param Computer c2
	 * @return boolean (true if equal, false if not)
	 */
	private static boolean isEqual(Computer c1, Computer c2) {
		
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
	
	/**
	 * Run unit tests for Stack and Queue classes from Java Collections
	 */
	public static void main(String[] args) {
		
		stackTest();
		queueTest();
	} 
	
	/**
	 * Unit tests for Stack methods
	 */
	public static void stackTest() {
		
		// declare a Stack variable
		Stack<Computer> computerStack;
		
		// ***************************** TEST stackToInt METHOD! ************************************
		System.out.println("********* TEST stackToInt METHOD! *********\n");
		int intOfPrices;
		
		// *********** TEST CASE 1 (Stack with at least four Computers)
		// Note: I use smaller prices to avoid overflow
		computerStack = new Stack<Computer>();
		computerStack.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack.push(new MacComputer("AppleMacBookAir13", 99, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 20));
		
		intOfPrices = stackToInt(computerStack);
		System.out.println("*** stackToInt Test Case 1 ***\n");
		System.out.println("Input Stack: \n" + displayStack(computerStack));
		System.out.println("stackToInt: " + intOfPrices + "\n");
		
		// *********** TEST CASE 2 (Stack with one Computer)
		// Note: I use smaller prices to avoid overflow
		computerStack = new Stack<Computer>();
		computerStack.push(new WindowsComputer("LenovoThinkPadE16Gen2AMD", 25, 8));
		
		intOfPrices = stackToInt(computerStack);
		System.out.println("*** stackToInt Test Case 2 ***\n");
		System.out.println("Input Stack: \n" + displayStack(computerStack));
		System.out.println("stackToInt: " + intOfPrices + "\n");
		
		// *********** TEST CASE 3 (Stack with multiple Computers, where at least one has integer value 0)
		// Note: I use smaller prices to avoid overflow
		computerStack = new Stack<Computer>();
		computerStack.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack.push(new Computer("LenovoChromebookPlus14", 0));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack.push(new MacComputer("AppleMacMini", 65, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		intOfPrices = stackToInt(computerStack);
		System.out.println("*** stackToInt Test Case 3 ***\n");
		System.out.println("Input Stack: \n" + displayStack(computerStack));
		System.out.println("stackToInt: " + intOfPrices + "\n");
		
		// ***************************** TEST popSome METHOD! ************************************
		System.out.println("********* TEST popSome METHOD! *********\n");
		int count;
		int sumOfPrices;
		
		// *********** TEST CASE 1 (Stack has *count* items)
		count = 4;
		computerStack = new Stack<Computer>();
		computerStack.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack.push(new MacComputer("AppleMacBookAir13", 99, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 20));
		
		System.out.println("*** popSome Test Case 1 ***\n");
		System.out.println("Count == " + count + "\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		sumOfPrices = popSome(computerStack, count);
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Sum of prices from popped Computers: " + sumOfPrices + "\n");
		
		// *********** TEST CASE 2 (Stack has multiple Computers and count < number of Computers)
		count = 2;
		computerStack = new Stack<Computer>();
		computerStack.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack.push(new MacComputer("AppleMacBookAir13", 99, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 20));
		
		System.out.println("*** popSome Test Case 2 ***\n");
		System.out.println("Count == " + count + "\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		sumOfPrices = popSome(computerStack, count);
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Sum of prices from popped Computers: " + sumOfPrices + "\n");
		
		// *********** TEST CASE 3 (Stack has multiple Computers and count > number of Computers)
		count = 5;
		computerStack = new Stack<Computer>();
		computerStack.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack.push(new MacComputer("AppleMacBookAir13", 99, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 20));
		
		System.out.println("*** popSome Test Case 3 ***\n");
		System.out.println("Count == " + count + "\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		sumOfPrices = popSome(computerStack, count);
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Sum of prices from popped Computers: " + sumOfPrices + "\n");
		
		// *********** TEST CASE 4 (Empty stack and count > 1)
		count = 2;
		computerStack = new Stack<Computer>();
		
		System.out.println("*** popSome Test Case 4 ***\n");
		System.out.println("Count == " + count + "\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack) + "\n");
		sumOfPrices = popSome(computerStack, count);
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Sum of prices from popped Computers: " + sumOfPrices + "\n");
		
		// *********** TEST CASE 5 (Non-empty stack and count = 0)
		count = 0;
		computerStack = new Stack<Computer>();
		computerStack.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack.push(new MacComputer("AppleMacBookAir13", 99, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 20));
		
		System.out.println("*** popSome Test Case 5 ***\n");
		System.out.println("Count == " + count + "\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		sumOfPrices = popSome(computerStack, count);
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Sum of prices from popped Computers: " + sumOfPrices + "\n");
		
		// *********** TEST CASE 6 (Non-empty stack and count is negative)
		count = -1;
		computerStack = new Stack<Computer>();
		computerStack.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack.push(new MacComputer("AppleMacBookAir13", 99, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 20));
		
		System.out.println("*** popSome Test Case 5 ***\n");
		System.out.println("Count == " + count + "\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		sumOfPrices = popSome(computerStack, count);
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Sum of prices from popped Computers: " + sumOfPrices + "\n");
		
		// ***************************** TEST extractFromStack METHOD! ************************************
		System.out.println("********* TEST extractFromStack METHOD! *********\n");
		int numExtract;
		Computer input = new Computer("LenovoChromebookPlus14", 0);
		
		// *********** TEST CASE 1 (Non-empty stack with one Computer matching the input Computer)
		computerStack = new Stack<Computer>();
		computerStack.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack.push(new Computer("LenovoChromebookPlus14", 0));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack.push(new MacComputer("AppleMacMini", 65, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		System.out.println("*** extractFrom Test Case 1 ***\n");
		System.out.println("Attempt to remove all Computers matching [" + input + "]\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		
		numExtract = extractFromStack(computerStack, input);
		
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Number of extracted Computers: " + numExtract + "\n");
		
		// *********** TEST CASE 2 (Non-empty stack with several Computers matching the input Computers)
		computerStack = new Stack<Computer>();
		computerStack.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack.push(new Computer("LenovoChromebookPlus14", 0));
		computerStack.push(new Computer("LenovoChromebookPlus14", 0));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack.push(new MacComputer("AppleMacMini", 65, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack.push(new Computer("LenovoChromebookPlus14", 0));
		
		System.out.println("*** extractFrom Test Case 2 ***\n");
		System.out.println("Attempt to remove all Computers matching [" + input + "]\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		
		numExtract = extractFromStack(computerStack, input);
		
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Number of extracted Computers: " + numExtract + "\n");
		
		// *********** TEST CASE 3 (Non-empty stack with no Computers matching the input Computer)
		computerStack = new Stack<Computer>();
		computerStack.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack.push(new MacComputer("AppleMacMini", 65, true));
		computerStack.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		System.out.println("*** extractFrom Test Case 3 ***\n");
		System.out.println("Attempt to remove all Computers matching [" + input + "]\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack));
		
		numExtract = extractFromStack(computerStack, input);
		
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Number of extracted Computers: " + numExtract + "\n");
		
		// *********** TEST CASE 4 (Empty stack)
		computerStack = new Stack<Computer>();
		
		System.out.println("*** extractFrom Test Case 4 ***\n");
		System.out.println("Attempt to remove all Computers matching [" + input + "]\n");
		System.out.println("Initial Stack: \n" + displayStack(computerStack) + "\n");
		
		numExtract = extractFromStack(computerStack, input);
		
		System.out.println("Final Stack: \n" + displayStack(computerStack) + "\n");
		System.out.println("Number of extracted Computers: " + numExtract + "\n");
		
		// ***************************** TEST equalStacks METHOD! ************************************
		System.out.println("********* TEST equalStacks METHOD! *********\n");
		boolean areStacksEqual;
		Stack<Computer> computerStack1;
		Stack<Computer> computerStack2;
		
		// *********** TEST CASE 1 (Non-empty stacks and equal)
		computerStack1 = new Stack<Computer>();
		computerStack2 = new Stack<Computer>();
		
		computerStack1.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack1.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack1.push(new MacComputer("AppleMacMini", 65, true));
		computerStack1.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		computerStack2.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack2.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack2.push(new MacComputer("AppleMacMini", 65, true));
		computerStack2.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		System.out.println("*** equalStacks Test Case 1 ***\n");
		System.out.println("Initial Stack1: \n" + displayStack(computerStack1));
		System.out.println("Initial Stack2: \n" + displayStack(computerStack2));
		
		areStacksEqual = equalStacks(computerStack1, computerStack2);
		
		System.out.println("Final Stack1: \n" + displayStack(computerStack1));
		System.out.println("Final Stack2: \n" + displayStack(computerStack2) + "\n");
		
		System.out.println("Are these two stacks equal?: " + areStacksEqual + "\n");
		
		// *********** TEST CASE 2 (Non-empty stacks and not equal)
		computerStack1 = new Stack<Computer>();
		computerStack2 = new Stack<Computer>();
		
		computerStack1.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack1.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack1.push(new MacComputer("AppleMacMini", 65, true));
		computerStack1.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		computerStack2.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack2.push(new MacComputer("AppleMacBookAir13", 99, true));
		computerStack2.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerStack2.push(new Computer("TuxedoInfinityBookS17", 20));
		
		System.out.println("*** equalStacks Test Case 2 ***\n");
		System.out.println("Initial Stack1: \n" + displayStack(computerStack1));
		System.out.println("Initial Stack2: \n" + displayStack(computerStack2));
		
		areStacksEqual = equalStacks(computerStack1, computerStack2);
		
		System.out.println("Final Stack1: \n" + displayStack(computerStack1));
		System.out.println("Final Stack2: \n" + displayStack(computerStack2) + "\n");
		
		System.out.println("Are these two stacks equal?: " + areStacksEqual + "\n");
		
		// *********** TEST CASE 3 (One of the input stacks is empty (returns false))
		computerStack1 = new Stack<Computer>();
		computerStack2 = new Stack<Computer>();
		
		computerStack1.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack1.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack1.push(new MacComputer("AppleMacMini", 65, true));
		computerStack1.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		System.out.println("*** equalStacks Test Case 3 ***\n");
		System.out.println("Initial Stack1: \n" + displayStack(computerStack1));
		System.out.println("Initial Stack2: \n" + displayStack(computerStack2) + "\n");
		
		areStacksEqual = equalStacks(computerStack1, computerStack2);
		
		System.out.println("Final Stack1: \n" + displayStack(computerStack1));
		System.out.println("Final Stack2: \n" + displayStack(computerStack2) + "\n");
		
		System.out.println("Are these two stacks equal?: " + areStacksEqual + "\n");
		
		// *********** TEST CASE 4 (Both input stacks are empty (returns true))
		computerStack1 = new Stack<Computer>();
		computerStack2 = new Stack<Computer>();
		
		System.out.println("*** equalStacks Test Case 4 ***\n");
		System.out.println("Initial Stack1: \n" + displayStack(computerStack1) + "\n");
		System.out.println("Initial Stack2: \n" + displayStack(computerStack2) + "\n");
		
		areStacksEqual = equalStacks(computerStack1, computerStack2);
		
		System.out.println("Final Stack1: \n" + displayStack(computerStack1) + "\n");
		System.out.println("Final Stack2: \n" + displayStack(computerStack2) + "\n");
		
		System.out.println("Are these two stacks equal?: " + areStacksEqual + "\n");
		
		// *********** TEST CASE 5 (Non-empty stacks and different lengths)
		computerStack1 = new Stack<Computer>();
		computerStack2 = new Stack<Computer>();
		
		computerStack1.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack1.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack1.push(new MacComputer("AppleMacMini", 65, true));
		computerStack1.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		computerStack2.push(new WindowsComputer("Dell16Plus", 71, 16));
		computerStack2.push(new MacComputer("AppleMacBookAir13", 5, true));
		computerStack2.push(new Computer("TuxedoInfinityBookS17", 22)); 
		computerStack2.push(new MacComputer("AppleMacMini", 65, true));
		computerStack2.push(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		System.out.println("*** equalStacks Test Case 5 ***\n");
		System.out.println("Initial Stack1: \n" + displayStack(computerStack1));
		System.out.println("Initial Stack2: \n" + displayStack(computerStack2));
		
		areStacksEqual = equalStacks(computerStack1, computerStack2);
		
		System.out.println("Final Stack1: \n" + displayStack(computerStack1));
		System.out.println("Final Stack2: \n" + displayStack(computerStack2) + "\n");
		
		System.out.println("Are these two stacks equal?: " + areStacksEqual + "\n");
	}
	
	/**
	 * Unit test for Queue methods.
	 */
	public static void queueTest() {
		
		// declare a Queue interface variable
		Queue<Computer> computerQueue;
		
		// ***************************** TEST replace METHOD! ************************************
		System.out.println("********* TEST replace METHOD! *********\n");
		WindowsComputer oldVal = new WindowsComputer("HPIntelCeleronN45002021", 17, 32);
		MacComputer newVal = new MacComputer("AppleMacMini", 60, false);
		int numReplaced;
		
		// *********** TEST CASE 1 (Queue has multiple entries, including one oldVal)
		computerQueue = new LinkedList<Computer>();
		computerQueue.add(new Computer("TuxedoInfinityBookS17", 20));
		computerQueue.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerQueue.add(new MacComputer("AppleMacBookAir13", 99, true));
		computerQueue.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("*** replace Test Case 1 ***\n");
		System.out.println("oldVal: [ " + oldVal + " ]");
		System.out.println("newVal: [ " + newVal + " ]\n");
		
		System.out.println("Input Queue: \n" + displayQueue(computerQueue));
		numReplaced = replace(computerQueue, oldVal, newVal);
		System.out.println("Output Queue: \n" + displayQueue(computerQueue));
		System.out.println("Number of Computers removed: " + numReplaced + "\n");
		
		// *********** TEST CASE 2 (Queue has multiple entries, including multiple oldVal)
		computerQueue = new LinkedList<Computer>();
		computerQueue.add(new Computer("TuxedoInfinityBookS17", 20));
		computerQueue.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerQueue.add(new MacComputer("AppleMacBookAir13", 99, true));
		computerQueue.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerQueue.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("*** replace Test Case 2 ***\n");
		System.out.println("oldVal: [ " + oldVal + " ]");
		System.out.println("newVal: [ " + newVal + " ]\n");
		
		System.out.println("Input Queue: \n" + displayQueue(computerQueue));
		numReplaced = replace(computerQueue, oldVal, newVal);
		System.out.println("Output Queue: \n" + displayQueue(computerQueue));
		System.out.println("Number of Computers removed: " + numReplaced + "\n");
		
		// *********** TEST CASE 3 (oldVal is first in the queue)
		computerQueue = new LinkedList<Computer>();
		computerQueue.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		computerQueue.add(new Computer("TuxedoInfinityBookS17", 20));
		computerQueue.add(new MacComputer("AppleMacBookAir13", 99, true));
		computerQueue.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("*** replace Test Case 3 ***\n");
		System.out.println("oldVal: [ " + oldVal + " ]");
		System.out.println("newVal: [ " + newVal + " ]\n");
		
		System.out.println("Input Queue: \n" + displayQueue(computerQueue));
		numReplaced = replace(computerQueue, oldVal, newVal);
		System.out.println("Output Queue: \n" + displayQueue(computerQueue));
		System.out.println("Number of Computers removed: " + numReplaced + "\n");
		
		// *********** TEST CASE 4 (oldVal is last in the queue)
		computerQueue = new LinkedList<Computer>();
		computerQueue.add(new Computer("TuxedoInfinityBookS17", 20));
		computerQueue.add(new MacComputer("AppleMacBookAir13", 99, true));
		computerQueue.add(new WindowsComputer("Dell16Plus", 71, 16));
		computerQueue.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		
		System.out.println("*** replace Test Case 4 ***\n");
		System.out.println("oldVal: [ " + oldVal + " ]");
		System.out.println("newVal: [ " + newVal + " ]\n");
		
		System.out.println("Input Queue: \n" + displayQueue(computerQueue));
		numReplaced = replace(computerQueue, oldVal, newVal);
		System.out.println("Output Queue: \n" + displayQueue(computerQueue));
		System.out.println("Number of Computers removed: " + numReplaced + "\n");
		
		// *********** TEST CASE 5 (Queue has multiple entries, not including oldVal)
		computerQueue = new LinkedList<Computer>();
		computerQueue.add(new Computer("TuxedoInfinityBookS17", 20));
		computerQueue.add(new Computer("LenovoChromebookPlus14", 0));
		computerQueue.add(new MacComputer("AppleMacBookAir13", 99, true));
		computerQueue.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("*** replace Test Case 5 ***\n");
		System.out.println("oldVal: [ " + oldVal + " ]");
		System.out.println("newVal: [ " + newVal + " ]\n");
		
		System.out.println("Input Queue: \n" + displayQueue(computerQueue));
		numReplaced = replace(computerQueue, oldVal, newVal);
		System.out.println("Output Queue: \n" + displayQueue(computerQueue));
		System.out.println("Number of Computers removed: " + numReplaced + "\n");
		
		// *********** TEST CASE 6 (Queue is empty)
		computerQueue = new LinkedList<Computer>();
		
		System.out.println("*** replace Test Case 6 ***\n");
		System.out.println("oldVal: [ " + oldVal + " ]");
		System.out.println("newVal: [ " + newVal + " ]\n");
		
		System.out.println("Input Queue: \n" + displayQueue(computerQueue) + "\n");
		numReplaced = replace(computerQueue, oldVal, newVal);
		System.out.println("Output Queue: \n" + displayQueue(computerQueue) + "\n");
		System.out.println("Number of Computers removed: " + numReplaced + "\n");
		
		// ***************************** TEST swap METHOD! ************************************
		System.out.println("********* TEST swap METHOD! *********\n");
		Stack<Computer> stackSwap;
		Queue<Computer> queueSwap;
		
		// *********** TEST CASE 1 (Non-empty stack and queue, both the same length)
		System.out.println("*** swap Test Case 1 ***\n");
		stackSwap = new Stack<Computer>();
		queueSwap = new LinkedList<Computer>();
		
		stackSwap.push(new MacComputer("AppleiMac24", 13, false));
		stackSwap.push(new WindowsComputer("LenovoThinkPadE16Gen2AMD", 20, 8));
		stackSwap.push(new WindowsComputer("AcerNitroV15GamingLaptop", 11, 16));
		stackSwap.push(new Computer("GalaxyZFlip7", 10));
		
		queueSwap.add(new Computer("TuxedoInfinityBookS17", 20));
		queueSwap.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		queueSwap.add(new MacComputer("AppleMacBookAir13", 99, true));
		queueSwap.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("Initial Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Initial Queue: \n" + displayQueue(queueSwap) + "\n");
		swap(stackSwap, queueSwap);
		System.out.println("Final Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Final Queue: \n" + displayQueue(queueSwap) + "\n");
		
		// *********** TEST CASE 2 (Non-empty stack and queue, not of the same length)
		System.out.println("*** swap Test Case 2 ***\n");
		stackSwap = new Stack<Computer>();
		queueSwap = new LinkedList<Computer>();
		
		stackSwap.push(new MacComputer("AppleiMac24", 13, false));
		stackSwap.push(new WindowsComputer("LenovoThinkPadE16Gen2AMD", 20, 8));
		stackSwap.push(new WindowsComputer("AcerNitroV15GamingLaptop", 11, 16));
		stackSwap.push(new Computer("GalaxyZFlip7", 10));
		stackSwap.push(new Computer("MotorolaRazrUltra2025", 10));
		
		queueSwap.add(new Computer("TuxedoInfinityBookS17", 20));
		queueSwap.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		queueSwap.add(new MacComputer("AppleMacBookAir13", 99, true));
		queueSwap.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("Initial Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Initial Queue: \n" + displayQueue(queueSwap) + "\n");
		swap(stackSwap, queueSwap);
		System.out.println("Final Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Final Queue: \n" + displayQueue(queueSwap) + "\n");
		
		// *********** TEST CASE 3 (Empty stack and non-empty queue)
		System.out.println("*** swap Test Case 3 ***\n");
		stackSwap = new Stack<Computer>();
		queueSwap = new LinkedList<Computer>();
		
		queueSwap.add(new Computer("TuxedoInfinityBookS17", 20));
		queueSwap.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		queueSwap.add(new MacComputer("AppleMacBookAir13", 99, true));
		queueSwap.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("Initial Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Initial Queue: \n" + displayQueue(queueSwap) + "\n");
		swap(stackSwap, queueSwap);
		System.out.println("Final Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Final Queue: \n" + displayQueue(queueSwap) + "\n");
		
		// *********** TEST CASE 4 (Empty queue and non-empty stack)
		System.out.println("*** swap Test Case 4 ***\n");
		stackSwap = new Stack<Computer>();
		queueSwap = new LinkedList<Computer>();
		
		stackSwap.push(new MacComputer("AppleiMac24", 13, false));
		stackSwap.push(new WindowsComputer("LenovoThinkPadE16Gen2AMD", 20, 8));
		stackSwap.push(new WindowsComputer("AcerNitroV15GamingLaptop", 11, 16));
		stackSwap.push(new Computer("GalaxyZFlip7", 10));
		stackSwap.push(new Computer("MotorolaRazrUltra2025", 10));
		
		System.out.println("Initial Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Initial Queue: \n" + displayQueue(queueSwap) + "\n");
		swap(stackSwap, queueSwap);
		System.out.println("Final Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Final Queue: \n" + displayQueue(queueSwap) + "\n");
		
		// *********** TEST CASE 5 (Empty queue and empty stack)
		System.out.println("*** swap Test Case 5 ***\n");
		stackSwap = new Stack<Computer>();
		queueSwap = new LinkedList<Computer>();
		
		System.out.println("Initial Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Initial Queue: \n" + displayQueue(queueSwap) + "\n");
		swap(stackSwap, queueSwap);
		System.out.println("Final Stack: \n" + displayStack(stackSwap) + "\n");
		System.out.println("Final Queue: \n" + displayQueue(queueSwap) + "\n");
		
		// ***************************** TEST split METHOD! ************************************
		System.out.println("********* TEST split METHOD! *********\n");
		Queue<Computer> inputQ;
		Queue<Computer>[] splitQ;
		
		// *********** TEST CASE 1 (Input queue has a mix of Computer, WindowsComputer, MacComputer entries)
		System.out.println("*** split Test Case 1 ***\n");
		inputQ = new LinkedList<Computer>();
		
		inputQ.add(new MacComputer("AppleiMac24", 13, false));
		inputQ.add(new WindowsComputer("LenovoThinkPadE16Gen2AMD", 20, 8));
		inputQ.add(new WindowsComputer("AcerNitroV15GamingLaptop", 11, 16));
		inputQ.add(new Computer("GalaxyZFlip7", 10));
		inputQ.add(new Computer("MotorolaRazrUltra2025", 10));
		inputQ.add(new Computer("TuxedoInfinityBookS17", 20));
		inputQ.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		inputQ.add(new MacComputer("AppleMacBookAir13", 99, true));
		inputQ.add(new WindowsComputer("Dell16Plus", 71, 16));
		inputQ.add(new MacComputer("AppleMacbookPro14", 16, true));
		
		System.out.println("Initial Input Queue: \n" + displayQueue(inputQ) + "\n");
		splitQ = split(inputQ);
		System.out.println("Final Input Queue: \n" + displayQueue(inputQ) + "\n");
		System.out.println("Output Queue<Computer> array:\n");
	
		System.out.println("array[0] WindowsComputer Queue: \n" + displayQueue(splitQ[0]) + "\n");
		System.out.println("array[1] MacComputer Queue: \n" + displayQueue(splitQ[1]) + "\n");
		
		// *********** TEST CASE 2 (Input queue has only WindowsComputer entries)
		System.out.println("*** split Test Case 2 ***\n");
		inputQ = new LinkedList<Computer>();
		
		inputQ.add(new WindowsComputer("LenovoThinkPadE16Gen2AMD", 20, 8));
		inputQ.add(new WindowsComputer("AcerNitroV15GamingLaptop", 11, 16));
		inputQ.add(new WindowsComputer("HPIntelCeleronN45002021", 17, 32));
		inputQ.add(new WindowsComputer("Dell16Plus", 71, 16));
		
		System.out.println("Initial Input Queue: \n" + displayQueue(inputQ) + "\n");
		splitQ = split(inputQ);
		System.out.println("Final Input Queue: \n" + displayQueue(inputQ) + "\n");
		System.out.println("Output Queue<Computer> array:\n");
	
		System.out.println("array[0] WindowsComputer Queue: \n" + displayQueue(splitQ[0]) + "\n");
		System.out.println("array[1] MacComputer Queue: \n" + displayQueue(splitQ[1]) + "\n");
		
		// *********** TEST CASE 3 (Input queue has only MacComputer entries)
		System.out.println("*** split Test Case 3 ***\n");
		inputQ = new LinkedList<Computer>();
		
		inputQ.add(new MacComputer("AppleiMac24", 13, false));
		inputQ.add(new MacComputer("AppleMacBookAir13", 99, true));
		inputQ.add(new MacComputer("AppleMacbookPro14", 16, true));
		
		System.out.println("Initial Input Queue: \n" + displayQueue(inputQ) + "\n");
		splitQ = split(inputQ);
		System.out.println("Final Input Queue: \n" + displayQueue(inputQ) + "\n");
		System.out.println("Output Queue<Computer> array:\n");
	
		System.out.println("array[0] WindowsComputer Queue: \n" + displayQueue(splitQ[0]) + "\n");
		System.out.println("array[1] MacComputer Queue: \n" + displayQueue(splitQ[1]) + "\n");
		
		// *********** TEST CASE 4 (Input queue has only Computer entries)
		System.out.println("*** split Test Case 4 ***\n");
		inputQ = new LinkedList<Computer>();
		
		inputQ.add(new Computer("GalaxyZFlip7", 10));
		inputQ.add(new Computer("MotorolaRazrUltra2025", 10));
		inputQ.add(new Computer("TuxedoInfinityBookS17", 20));
		
		System.out.println("Initial Input Queue: \n" + displayQueue(inputQ) + "\n");
		splitQ = split(inputQ);
		System.out.println("Final Input Queue: \n" + displayQueue(inputQ) + "\n");
		System.out.println("Output Queue<Computer> array:\n");
	
		System.out.println("array[0] WindowsComputer Queue: \n" + displayQueue(splitQ[0]) + "\n");
		System.out.println("array[1] MacComputer Queue: \n" + displayQueue(splitQ[1]) + "\n");
		
		// *********** TEST CASE 5 (Input queue is empty)
		System.out.println("*** split Test Case 5 ***\n");
		inputQ = new LinkedList<Computer>();
		
		System.out.println("Initial Input Queue: \n" + displayQueue(inputQ) + "\n");
		splitQ = split(inputQ);
		System.out.println("Final Input Queue: \n" + displayQueue(inputQ) + "\n");
		System.out.println("Output Queue<Computer> array:\n");
	
		System.out.println("array[0] WindowsComputer Queue: \n" + displayQueue(splitQ[0]) + "\n");
		System.out.println("array[1] MacComputer Queue: \n" + displayQueue(splitQ[1]) + "\n");
		
	}
}

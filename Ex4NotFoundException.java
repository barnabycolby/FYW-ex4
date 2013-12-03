package ex4;

/**
 * An Exception intended to represent situations where an object being searched
 * for in an array was not found
 * @author Barnaby
 *
 */
@SuppressWarnings("serial")
public class Ex4NotFoundException extends Exception {
	/**
	 * Constructor
	 * @param object A string that describes the object that could not be found
	 */
	public Ex4NotFoundException(){
		super("Object being searched for within the array was not found.");
	}
}

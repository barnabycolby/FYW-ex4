package ex4;

import java.util.Arrays;

/**
 * Performs binary searches on a sorted array
 * @author Barnaby
 *
 */
public class Ex4BinarySearch {
	/**
	 * Private Constructor
	 */
	private Ex4BinarySearch(){
		//Does nothing because it should not be called
	}
	
	/**
	 * Verifies that a pair of indexes identify a subrange of an array
	 * @param length The arrays length
	 * @param start The index of the start of the range
	 * @param rangeEndPlusOne The index one element past the end of the range
	 * @throws IllegalArgumentException Thrown when the pair of indexes identify a range with a negative length
	 * @throws ArrayIndexOutOfBoundsException Thrown when the range extends past the start or end of the array
	 */
	public static void rangeCheck(int length, int start, int rangeEndPlusOne){
		//Throws an exception if the range has a negative length
		if(rangeEndPlusOne < start){
			throw new IllegalArgumentException("The pair of indexes identify a range with a negative length.");
		} else if((start < 0) || (rangeEndPlusOne > length)){
			throw new ArrayIndexOutOfBoundsException("Range extends past the start or end of the array.");
		}
	}
	
	/**
	 * Performs a binary search on a subrange of an array of Comparable objects
	 * @param array A sorted array of comparable objects to search
	 * @param rangeStart An index that defines the start of the subRange
	 * @param rangeEndPlusOne An index that defines the end of the subRange plus one
	 * @param object The object to search the array for
	 * @return Returns the index in the subrange of the array where the object being searched for was found, assuming that it is found
	 * @throws Ex4NotFoundException Thrown if the object being searched for could not be found
	 * @throws ClassCastException Thrown if the object could not be compared with another object because it had the wrong class
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static int binarySearchHelper(Comparable[] array, int rangeStart,
			int rangeEndPlusOne, Comparable object) throws Ex4NotFoundException {
		rangeCheck(array.length, rangeStart, rangeEndPlusOne );
		
		//Stores the length of the range
		int rangeLength = rangeEndPlusOne - rangeStart;
		
		//Base case
		if(rangeLength == 0){
			throw new Ex4NotFoundException();
		}
		
		//Choose an index as close as possible to the middle of the subrange
		int middle = (rangeLength / 2) + rangeStart;
		
		//Check whether the object at that index is the object we're looking for
		int result = array[middle].compareTo(object);
		if(result == 0){
			return middle;
		} else if(result < 0){
			return binarySearchHelper(array, rangeStart, middle, object);
		} else {
			return binarySearchHelper(array, middle + 1, rangeEndPlusOne, object);
		}
	}
	
	/**
	 * Searches for a comparable object in an array of comparable objects by performing binary search
	 * @param array A sorted array of comparable objects to perform the search on
	 * @param object The object to search the array for
	 * @return Returns the index of the object in the array, assuming it is found
	 * @throws Ex4NotFoundException When the object can not be found in the array
	 */
	@SuppressWarnings("rawtypes")
	public static int binarySearch(Comparable[] array, Comparable object) throws Ex4NotFoundException {
		return binarySearchHelper(array, 0, array.length, object);
	}
	
	/**
	 * Checks whether an array of Comparable objects contains a specified object or not
	 * @param array The array of comparable objects to search
	 * @param object The comparable object to look for
	 * @return Returns true if the array contains the object and false if it doesn't
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean arrayContains(Comparable[] array, Comparable object){
		//Copy the array to preserve the original
		Comparable[] copy = Arrays.copyOf(array, array.length);
		
		//Sorts the array into ascending order
		Arrays.sort(copy);
		
		//Search the sorted array and return true if the object can be found and false otherwise
		try {
			//Search the sorted array for the object
			binarySearch(copy, object);
		} catch (Ex4NotFoundException e) {
			//Object could not be found in the array
			return false;
		}
		
		//Exception was not thrown therefore the object must have been found
		return true;
	}
	
	/**
	 * The main function
	 * Tests the binarySearch and arrayContains functions
	 * @param args The arguments passed to the program
	 */
	public static void main(String[] args){	
		//Test binarySearch
		String[] array = { "1","2","3","4","5" };
		//Element should be found
		try {
			System.out.println(binarySearch(array, "3"));
		} catch (Ex4NotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		//Element should not be found
		try {
			System.out.println(binarySearch(array, "6"));
		} catch (Ex4NotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		//Test arrayContains
		String[] array2 = { "4","3","5","1","2" };
		//Element should be found
		System.out.println(arrayContains(array2, "3"));
		
		//Element should not be found
		System.out.println(arrayContains(array2, "6"));
	}
}

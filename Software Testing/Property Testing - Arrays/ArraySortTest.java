import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * This is deliverable 4 for CS 1632 at  the University of Pittsburgh.
 * We are testing Java's sort on arrays, to confirm that it acts as expected.
 * 
 * Being a part of Java's builtin utilities library, we are not expecting to find
 * any errors.
 */
public class ArraySortTest
{	
	Random random = new Random();
	ArrayList<int []> arraysToBeTested = new ArrayList<int []>();
	
	@Before
	public void setUp() {
		
		int[] addArray;
		
		//100 arrays of random lengths with random ints at every index,
		//All put into the arraylist indicated up top
		for(int i = 0; i < 100; i++) {
			addArray = new int[random.nextInt(99) + 1];
			for(int j = 0; j < addArray.length; j++) {
				addArray[j] = random.nextInt(100);
			}
			this.arraysToBeTested.add(addArray);
		}
	}
	
	/**
	 * Arrays should be the same size after the sort as they are before it
	 */
	@Test
	public void testArraysSameSize() {
		for(int i = 0; i < arraysToBeTested.size(); i++) {
			int[] testArraySorted = arraysToBeTested.get(i);
			Arrays.sort(testArraySorted);
			int[] testArrayNotSorted = arraysToBeTested.get(i);
			
			//Compares the length of all arrays with their sorted counterparts
			assertEquals(testArraySorted.length, testArrayNotSorted.length);
		}
	}
	
	/**
	 * Arrays of integers should still be exclusively integers, even after
	 * being sorted. This method confirms that all arrays still only contain ints
	 */
	@Test
	public void testArraysSameClassType() {
		for(int i = 0; i < arraysToBeTested.size(); i++) {
			int[] testArray = arraysToBeTested.get(i);
			Arrays.sort(testArray);
			//Determines that every array is an instance of int[] still after sort
			assertTrue(testArray instanceof int[]);
		}
	}
	
	/**
	 * After an array is sorted, we should be able to confirm that a sort was
	 * actually accomplished. If not, the sort did not work at all
	 */
	@Test
	public void testArraySorted() {
		for(int i = 0; i < arraysToBeTested.size(); i++) {
			int[] testArray = arraysToBeTested.get(i);
			Arrays.sort(testArray);
			int difference = testArray[0] - testArray[testArray.length - 1];
			//Checks that the last number is greater than the first
			assertTrue(difference <= 0);
		}
	}
}
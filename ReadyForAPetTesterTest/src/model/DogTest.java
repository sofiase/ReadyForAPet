package model;


import junit.framework.TestCase;
import Model.Dog;

public class DogTest extends TestCase {
	Dog tdog = new Dog("olle");
	
	public DogTest() {
	} 
	
	
	protected void setUp() throws Exception{
		super.setUp();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * testMethodgetName Method 
	 *  	
	 *  Test for the method getName in the class Dog 
	 */

	public void testMethodgetName () {
		assertEquals("olle",tdog.getName());}

} 



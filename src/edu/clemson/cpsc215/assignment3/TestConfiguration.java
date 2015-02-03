/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/28/14
 * 
 * Tests the functions of the Configuration class
 */

package edu.clemson.cpsc215.assignment3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestConfiguration {
	private Configuration config, config2, config3;

	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		config.setEAddr("wrnewbe");
		config.setIPAddr("numbers");
		config.setpWrd("password");
		
		config2 = new Configuration();
		config2.setEAddr("adifferentuser");
		config2.setIPAddr("somedifferentnumbers");
		config2.setpWrd("pass");
		
		config3 = new Configuration();
		config3.setEAddr("hello?");
		config3.setIPAddr("phone");
		config3.setpWrd("passwurd");
	}

	@Test
	public void testGetEAddr() {
		assertEquals("wrnewbe", config.getEAddr());
		assertEquals("adifferentuser", config2.getEAddr());
		assertEquals("hello?", config3.getEAddr());
	}

	@Test
	public void testSetEAddr(){
		config.setEAddr("email");
		config2.setEAddr("client");
		config3.setEAddr("tests");
		
		assertEquals("email", config.getEAddr());
		assertEquals("client", config2.getEAddr());
		assertEquals("tests", config3.getEAddr());
	}

	@Test
	public void testGetIPAddr(){
		assertEquals("numbers", config.getIPAddr());
		assertEquals("somedifferentnumbers", 
				config2.getIPAddr());
		assertEquals("phone", config3.getIPAddr());
	}

	@Test
	public void testSetIPAddr(){
		config.setIPAddr("867");
		config2.setIPAddr("5309");
		config3.setIPAddr("Some other funny thing");
		
		assertEquals("867", config.getIPAddr());
		assertEquals("5309", config2.getIPAddr());
		assertEquals("Some other funny thing", 
				config3.getIPAddr());
		
	}

	@Test
	public void testGetPWrd(){
		assertEquals("password", config.getpWrd());
		assertEquals("pass", config2.getpWrd());
		assertEquals("passwurd", config3.getpWrd());
	}

	@Test
	public void testSetPWrd(){
		config.setpWrd("another clever password");
		config2.setpWrd("something else");
		config3.setpWrd("12345");
		
		assertEquals("another clever password",
				config.getpWrd());
		assertEquals("something else", config2.getpWrd());
		assertEquals("12345", config3.getpWrd());
	}
}


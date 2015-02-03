/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/28/14
 * 
 * Tests each function of the Contact class
 */

package edu.clemson.cpsc215.assignment3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestContact {
	private Contact me, myself, i;

	@Before
	public void setUp() throws Exception {
		me = new Contact("William Newberry", "wrnewbe@clemson.edu", 
				                  "1313 Mockingbird Lane", "1-800-IAM-TEST");
		myself = new Contact("William Newberry", "wrnv@att.net", 
                "1313 Mockingbird Lane", "1-800-IAM-TEST");
		i = new Contact("William Newberry", "goremiser@gmail.com", 
                "1313 Mockingbird Lane", "1-800-IAM-TEST");
	}

	@Test
	public void testGetName() {
		String tmp = me.getName();
		String tmp2 = myself.getName();
		String tmp3 = i.getName();
		
		assertEquals("William Newberry", tmp);
		assertEquals("William Newberry", tmp2);
		assertEquals("William Newberry", tmp3);
	}
	
	@Test
	public void testSetName() {
		me.setName("Trevor");
		myself.setName("Jacob");
		i.setName("Yates");
		
		assertEquals("Trevor", me.getName());
		assertEquals("Jacob", myself.getName());
		assertEquals("Yates", i.getName());
		
	}
	
	@Test
	public void testGetEmail() {
		String tmp = me.getEmail();
		String tmp2 = myself.getEmail();
		String tmp3 = i.getEmail();
		
		assertEquals("wrnewbe@clemson.edu", tmp);
		assertEquals("wrnv@att.net", tmp2);
		assertEquals("goremiser@gmail.com", tmp3);
	}
	
	@Test
	public void testSetEmail() {
		me.setEmail("someemail@gmail.com");
		myself.setEmail("someemail@gmail.com");
		i.setEmail("someemail@gmail.com");
		
		assertEquals("someemail@gmail.com", me.getEmail());
		assertEquals("someemail@gmail.com", myself.getEmail());
		assertEquals("someemail@gmail.com", i.getEmail());
	}
	
	@Test
	public void testGetAddress() {
		String tmp = me.getAddress();
		String tmp2 = myself.getAddress();
		String tmp3 = i.getAddress();
		
		assertEquals("1313 Mockingbird Lane", tmp);
		assertEquals("1313 Mockingbird Lane", tmp2);
		assertEquals("1313 Mockingbird Lane", tmp3);
	}
	
	@Test
	public void testSetAddress() {
		me.setAddress("123 Sesame Street");
		myself.setAddress("4222 Clinton Way");
		i.setAddress("221 B Baker Street");
		
		assertEquals("123 Sesame Street", me.getAddress());
		assertEquals("4222 Clinton Way", myself.getAddress());
		assertEquals("221 B Baker Street", i.getAddress());
	}
	
	@Test
	public void testGetPhone() {
		String tmp = me.getPhone();
		String tmp2 = me.getPhone();
		String tmp3 = me.getPhone();
		
		assertEquals("1-800-IAM-TEST", tmp);
		assertEquals("1-800-IAM-TEST", tmp2);
		assertEquals("1-800-IAM-TEST", tmp3);
	}
	
	@Test
	public void testSetPhone() {
		me.setPhone("1-800-NUM-BERS");
		myself.setPhone("1-800-NUM-BERS");
		i.setPhone("1-800-NUM-BERS");
		
		assertEquals("1-800-NUM-BERS", me.getPhone());
		assertEquals("1-800-NUM-BERS", myself.getPhone());
		assertEquals("1-800-NUM-BERS", i.getPhone());
	}

}

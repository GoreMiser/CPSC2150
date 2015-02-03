/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/28/14
 * 
 * Tests the serialization of the data store by saving 3 different contacts,
 * reading them back in, and asserting that they are what we put in.
 */

package edu.clemson.cpsc215.assignment3;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDataStore {
	private static DataStore data;
	private static ContactTableModel mod;
	private static Contact lisa, robin, william;
	private static Configuration admin;

	@BeforeClass
	public static void setUp() throws Exception {
		
		data = DataStore.getInstance();
		
		File [] configFiles = data.getConfigFile().listFiles();
		File [] contactFiles = data.getContactFile().listFiles();
		for(int i = 0; i < configFiles.length; ++i)
			configFiles[i].delete();
		for(int i = 0; i < contactFiles.length; ++i)
			contactFiles[i].delete();
		
		mod = new ContactTableModel();
		
		data.setModel(mod);
		
		admin = new Configuration();
		
		lisa = new Contact("Lisa", "ljnewber", "Somewhere",
				  "Something");
		robin = new Contact("Robin", "wrnewberIV", "Somewhere",
				  "Something");
		william = new Contact("William", "wrnewberV", "Someplace",
				  "Some numbers");
		
		admin.setEAddr("admin@gmail.com");
		admin.setIPAddr("smtp.gmail.com");
		admin.setPort("587");
		admin.setpWrd("admin");
		
		data.add(lisa);
		data.add(robin);
		data.add(william);
		data.setConfig(admin);		
	}
	
	/**
	 * Test will reset all files in the contact and configuration folders
	 * in order to restore after the test
	 */
	@AfterClass
	public static void restore() {
		File [] configFiles = data.getConfigFile().listFiles();
		File [] contactFiles = data.getContactFile().listFiles();
		for(int i = 0; i < configFiles.length; ++i)
			configFiles[i].delete();
		for(int i = 0; i < contactFiles.length; ++i)
			contactFiles[i].delete();
	}
	
	@Test
	public void testSerialization(){
		data.saveData();
		data.getContactList().clear();
		data.loadData();
		
		assertEquals(data.getContactList().get(0).getName(), "Lisa");
		assertEquals(data.getContactList().get(1).getName(), "Robin");
		assertEquals(data.getContactList().get(2).getName(), "William");
	}
}

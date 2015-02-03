/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The DataStore class is a singleton used to hold all information regarding
 * the program during run-time and holds a list of contacts as well as the 
 * current configuration in use.  The DataStore does not actually perform any
 * saving or loading procedures but calls these procedures at program startup
 * and shutdown.
 */

package edu.clemson.cpsc215.assignment3;

import java.io.File;
import java.util.ArrayList;

public class DataStore {
	private static DataStore instance = null;
	
	//put contacts in based on email
	private static ArrayList<Contact> modelContacts; //for the table model
	
	private static Configuration config;
	
	//and our serial files.  Hooray!
	private static File configFolder;
	private static File contactFolder;
	
	private ContactTableModel mod;
	
	//static initializer because Singleton Pattern
	static {
		instance = new DataStore();
		modelContacts = new ArrayList<Contact>();
		config = new Configuration();
		configFolder = new File("data/Configuration/");
		if(!configFolder.exists()) configFolder.mkdirs();
		contactFolder = new File("data/Contacts/");
		if(!contactFolder.exists()) contactFolder.mkdirs();
	}
	
	public static DataStore getInstance() {
		return instance;
	}
	
	public Contact getContact(int key) {
		Contact c = new Contact();
		if(key != -1)
			c = modelContacts.get(key);
		return c;
	}
	
	/**
	 * for the table model
	 */
	public ArrayList<Contact> getContactList(){
		return modelContacts;
	}
	
	public void setConfig(Configuration admin) {
		config = admin;
	}
	
	public void setModel(ContactTableModel mod) {
		this.mod = mod;
	}
	
	public ContactTableModel getModel() {
		return mod;
	}
	
	public Configuration getConfig() {
		return config;
	}
	
	public void add(Contact newC) {
		modelContacts.add(newC);
	}
	
	public Contact remove(int i) {
		return modelContacts.remove(i);
	}
	
	/**
	 * Load/ save contacts using the ABC EProfile
	 */
	public void loadData() {
		File [] fList;
		try {
			//load the contacts
			fList = contactFolder.listFiles();
			for(int i = 0; i < fList.length; ++i) {
				Contact c = new Contact();
				c = (Contact)c.Load(fList[i]);
				add(c);
			}
		} catch (Exception e) {
			System.out.println("Couldn't load contacts...");
			e.printStackTrace();
		}
		try {
			//load the configuration
			fList = configFolder.listFiles();
			config = new Configuration();
			config = (Configuration)config.Load(fList[0]);
			
		} catch (Exception e) {
			System.out.println("Couldn't load configuration...");
		}
		mod.fireTableDataChanged();
	}
	
	public void saveData() {
		//save the configuration
		config.Save(configFolder.toString() + "/" + config.getEAddr() 
				+ ".ser");
		
		//save the contacts
		for(int i = 0; i < modelContacts.size(); ++i) {
			Contact c = modelContacts.get(i);
			c.Save(contactFolder.toString() + "/" + c.getEmail() + ".ser");
		}
	}

	public int contactSize() {
		return modelContacts.size();
	}
	
	public File getConfigFile() {
		return configFolder;
	}
	
	public File getContactFile() {
		return contactFolder;
	}

}
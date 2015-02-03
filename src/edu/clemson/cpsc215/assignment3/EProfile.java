/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * ABC for saving/loading contacts opens the file list of the specified 
 * configuration or contact folders and stores each file into the data store
 * or to disk
 */

package edu.clemson.cpsc215.assignment3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class EProfile {

	/**
	 * 
	 * @param sFile File given to store contacts/configuration
	 * save functionality stores serialized data into specified file
     *
	 */
	public void Save(String sFile) {
		try {
			//make the file
			File file = new File(sFile);
			
			//we need all these streams and wrappers of streams
			FileOutputStream fOut = 
					new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(fOut);
			
			//write the information to the file
			objOut.writeObject(this);
			
			//close the streams
			objOut.close();
			fOut.close();
		} catch (IOException e) {
			System.out.println("Error.  " +
					"Settings and contacts were not saved.");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param fList Files given to load list of contacts/configuration
	 * Load all files from the specified folder and store them in the DataStore
     *
	 */
	public EProfile Load(File fList) {
		//make a new object but don't initialize it to anything yet
		EProfile newJuan = null;
	
		try {
			//make appropriate input streams based on the file we passed in
			FileInputStream fIn = new FileInputStream(fList);
			ObjectInputStream objIn = new ObjectInputStream(fIn);
			newJuan = (EProfile)objIn.readObject();
			objIn.close();
			fIn.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException notFound) {
			notFound.printStackTrace();
		}
		return newJuan;
	}
}

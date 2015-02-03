/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The Driver class is in charge of running the entire program and is used
 * for startup of the MainFrame class, the DataStore singleton, the
 * ContactTableModel, and the Mediator.
 */

package edu.clemson.cpsc215.assignment3;

public class Driver {

	public static void main(String[] args) {
		
		DataStore data = DataStore.getInstance();
		ContactTableModel model = new ContactTableModel();
		data.setModel(model);
		MainFrame frame = new MainFrame(model);
		MediatorInterface med = frame.getMediator();
		
		/**
		 * We didn't want to save the password, so bring up the configuration
		 * dialog at program start up to input password/ip/port/email
		 */
		data.loadData();
		med.config();	
	}	
}

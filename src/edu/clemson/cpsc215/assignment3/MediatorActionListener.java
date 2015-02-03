/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Interface that outlines the execute function of the mediator class.
 * It gets the source of the action event, and calls the execute
 * function of the appropriate object
 */

package edu.clemson.cpsc215.assignment3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediatorActionListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Command comm = (Command) e.getSource();
		comm.execute();	
	}

}

/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The DeleteButton class is a subclass of the abstract base class:
 * AButton and is used in conjunction with the ContactTableModel and
 * DataStore classes in order to allow for the deletion of contacts
 * from the ContactTableModel and the DataStore along with the saved
 * serializable files through the use of a GUI.
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.JDialog;

public class DeleteButton extends AButton {

	private static final long serialVersionUID = 7865339112852543092L;
	MediatorInterface mediator;
	
	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param dialog Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public DeleteButton(MediatorActionListener al, MediatorInterface med, 
			JDialog dialog) {
		super(al, med, dialog, "Delete");		
	}

	@Override
	public void execute() {
		super.getMedIntFace().delete();
	}

	@Override
	public void setup() {
		super.getMedIntFace().setDelete(this);
	}
}

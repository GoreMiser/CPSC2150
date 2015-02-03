/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The EditButton is used to bring up the dialog window for editing contacts
 * and can only be activated after a contact has been selected via a single
 * mouse click to said contact from the ContactTableModel.
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.JDialog;

public class EditButton extends AButton {

	private static final long serialVersionUID = -5075450825171080719L;
	MediatorInterface mediator;
	
	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param dialog Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public EditButton(MediatorActionListener al, MediatorInterface med,
			JDialog dialog) {
		super(al, med, dialog, "Edit");	
	}

	@Override
	public void execute() {
		super.getMedIntFace().edit();
	}

	@Override
	public void setup() {
		super.getMedIntFace().setEdit(this);
	}
}

/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The EditingSave class is a subclass of the AButton abstract base class
 * and is a button specialized to save contact information that has been
 * edited via a dialog window to the DataStore in order to be saved
 * at program exit.
 */

package edu.clemson.cpsc215.assignment3;

public class EditingSave extends AButton {
	
	private static final long serialVersionUID = -244585965765856068L;
	
	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param dialog Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public EditingSave(MediatorActionListener al, MediatorInterface med,
			ContactEditingDlg dialog) {
		super(al, med, dialog, "Save");
	}

	@Override
	public void execute() {
		super.getMedIntFace().editSave((ContactEditingDlg) super.getDlg());
	}

	@Override
	public void setup() {
		super.getMedIntFace().setEditSave(this);
	}
}

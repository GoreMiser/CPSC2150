/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The AddSave class is used by the contact edit/save dialog box and allows
 * users to save contacts to the DataStore and put them into the 
 * ContactTableModel.
 */
package edu.clemson.cpsc215.assignment3;

public class AddSave extends AButton {

	private static final long serialVersionUID = -4733128191700660410L;
	
	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param dialog Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public AddSave(MediatorActionListener al, MediatorInterface med,
			ContactEditingDlg dialog) {
		super(al, med, dialog, "Save");
	}
	
	@Override
	public void execute() {
		super.getMedIntFace().addSave((ContactEditingDlg) super.getDlg());
	}
	@Override
	public void setup() {
		super.getMedIntFace().setAddSave(this);
	}

}

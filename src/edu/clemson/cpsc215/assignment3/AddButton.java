/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The AddButton class is a subclass of the abstract base class, AButton,
 * and allows for the user of the email client to add contacts to the
 * contact table model.
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.JDialog;

public class AddButton extends AButton {

	private static final long serialVersionUID = 7209645926886084932L;

	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param dialog Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public AddButton(MediatorActionListener al, MediatorInterface med, 
			JDialog dialog) {
		super(al, med, dialog, "Add");
	}

	/** 
	 * Execute the function in the mediator
	 */
	@Override
	public void execute() {
		super.getMedIntFace().add();
	}

	/** 
	 * Set the object in the mediator
	 */
	@Override
	public void setup() {
		super.getMedIntFace().setAdd(this);
	}
}

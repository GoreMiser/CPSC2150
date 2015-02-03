/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The CancelButton class is used to provide the user of the graphical user
 * interface with a cancel button and is used in several dialog windows.
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.JDialog;

public class CancelButton extends AButton {

	private static final long serialVersionUID = -3265868386707629158L;
	
	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param cancel Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public CancelButton(MediatorActionListener al, MediatorInterface med,
			JDialog cancel) {
		super(al, med, cancel, "Cancel");
	}
	
	public void execute() {
		super.getMedIntFace().Cancel(super.getDlg());
	}

	@Override
	public void setup() {
		super.getMedIntFace().setCancel(this);
	}

}

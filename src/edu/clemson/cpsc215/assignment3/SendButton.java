/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * Button used by the email transmission dialog to send the generated email
 * calls the super constructor and provide functions to set the object in
 * the mediator, and execute its functionality.
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.JDialog;

public class SendButton extends AButton {

	private static final long serialVersionUID = 6698415934382375615L;
	
	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param dialog Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public SendButton(MediatorActionListener al, MediatorInterface med,
			JDialog dialog) {
		super(al, med, dialog, "Send");
	}
	
	/**
	 * Execute the dialog's functionality
	 */
	public void execute() {
		super.getMedIntFace().sendEmail((EmailTransmissionDlg) super.getDlg());
	}

	/**
	 * function used to set the object in the mediator
	 */
	public void setup() {
		super.getMedIntFace().setSend(this);
	}
}

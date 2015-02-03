/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The ConfigSave class is a subclass of AButton and is used to save the
 * current configuration of the email client by the user via a GUI such as
 * a dialog window or JFrame.
 */

package edu.clemson.cpsc215.assignment3;

public class ConfigSave extends AButton {

	private static final long serialVersionUID = 785814367816264573L;
	
	/**
	 * 
	 * @param al Action Listener for button
	 * @param med Mediator object to control functionality
	 * @param dialog Dialog containing button
	 * 
	 * Calls the super constructor for the ABC
	 */
	public ConfigSave(MediatorActionListener al, MediatorInterface med, 
			ConfigurationDlg dialog) {
		super(al, med, dialog, "Save");
	}
	
	public void execute() {
		super.getMedIntFace().saveConfig((ConfigurationDlg) super.getDlg());
	}

	@Override
	public void setup() {
		super.getMedIntFace().setConfigSave(this);
	}

}

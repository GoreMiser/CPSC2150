
/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * Wraps the JMenu item to allow it to be passed a mediator and action
 * listener
 */

package edu.clemson.cpsc215.assignment3;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class HelpMenu extends JMenuItem implements Command {

	private static final long serialVersionUID = -1118861574569264173L;
	MediatorInterface med;
	
	/**
	 * 
	 * @param al Action listener for menu events
	 * @param med Mediator passed to control functionality
	 */
	public HelpMenu(ActionListener al, MediatorInterface med) {
		super("About");
		this.med = med;
		this.addActionListener(al);
		this.med.setHelp(this);
	}
	@Override
	public void execute() {
		med.help();
	}

}

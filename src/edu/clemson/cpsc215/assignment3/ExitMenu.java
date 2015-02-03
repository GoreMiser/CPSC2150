
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

public class ExitMenu extends JMenuItem implements Command {

	private static final long serialVersionUID = 7660755419865215022L;
	MediatorInterface med;
	
	/**
	 * 
	 * @param al action listener for menu
	 * @param med mediator passed to control functionality
	 */
	public ExitMenu(ActionListener al, MediatorInterface med) {
		super("Exit");
		this.med = med;
		this.addActionListener(al);
		this.med.setExit(this);	
	}

	@Override
	public void execute() {
		med.exit();
	}
}

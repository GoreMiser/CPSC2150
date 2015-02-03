/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The ConfigureMenu class is used to change their configuration
 * settings and is used in a menu bar.
 */

package edu.clemson.cpsc215.assignment3;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * Wrap the menu item in a class to allow for the passing of the mediator
 * and table model upon instantiation
 */
public class ConfigureMenu extends JMenuItem implements Command {

	private static final long serialVersionUID = 3905758755660261320L;
	private MediatorInterface med;
	
	/**
	 * 
	 * @param al Menu action listener
	 * @param med Mediator to execute functionality
	 */
	public ConfigureMenu(ActionListener al, MediatorInterface med) {
		super("Configure");
		this.med = med;
		this.addActionListener(al);
		this.med.setConfig(this);
	}

	@Override
	public void execute() {
		med.config();
	}
}

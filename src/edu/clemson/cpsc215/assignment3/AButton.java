/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The AButton class is an abstract base class used to package button 
 * functionality into a super class so that subclasses may be more
 * specialized but all used in similar situations. All buttons will
 * be instantiated in the same manner using this class. 
 */
package edu.clemson.cpsc215.assignment3;

import javax.swing.JButton;
import javax.swing.JDialog;

public abstract class AButton extends JButton implements Command{

	private static final long serialVersionUID = -8276778453355377104L;
	private MediatorActionListener medAct;
	private MediatorInterface medIntFace;
	private JDialog dialog;
	private String name;
		
	/**
	 * 
	 * @param mL Action listener for the button
	 * @param mI Mediator object for the button
	 * @param dlg Dialog that contains the button
	 * @param thing Button name
	 * 
	 * Call the super constructor to set the name, add the action listener, and
	 * set up the correct instantiation.
	 */
	public AButton(MediatorActionListener mL, MediatorInterface mI, JDialog 
			dlg, String thing) {
		super(thing);
		medAct = mL;
		medIntFace = mI;
		this.dialog = dlg;
		name = thing;
		
		this.addActionListener(mL);
		this.setup();
	}
	
	public abstract void execute();
	public abstract void setup();
	
	public MediatorInterface getMedIntFace(){
		return medIntFace;
	}
	
	public JDialog getDlg() {
		return dialog;
	}

	public MediatorActionListener getMedAct() {
		return medAct;
	}
	
	public String getName() {
		return this.name;
	}

}

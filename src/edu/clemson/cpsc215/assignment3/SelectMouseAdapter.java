/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * Class used to read the number of mouse clicks on items in the mainframe
 * Has access to the delete and edit buttons to enable them when there is
 * one click. Also has access to an email transmission dialog to bring up
 * the window when a row is double-clicked.
 */

package edu.clemson.cpsc215.assignment3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class SelectMouseAdapter extends MouseAdapter {
	private JButton delete;
	private JButton edit;
	private ContactTableModel mod;
	private EmailTransmissionDlg dialog;
	private MediatorInterface med;
	
	/**
	 * 
	 * @param delete Delete buttons should be disabled if a row is not selected
	 * @param edit Edit button should be disabled if a row is not selected
	 * @param m Use the table model to get the selected row
	 * @param med Mediator is passed to the email transmission dialog when a
	 * row is double clicked
	 */
	public SelectMouseAdapter(JButton delete, JButton edit, ContactTableModel
			m, MediatorInterface med) {
		this.delete = delete;
		this.edit = edit;
		this.mod = m;
		this.med = med;
	}
	
	/**
	 * If one row is clicked, the delete/ edit button are made available
	 * If a row is double clicked, the email transmission dialog is brought up
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 1) {
			JTable target = (JTable)e.getSource();
			int row = target.getSelectedRow();
			if(row > -1) {
				delete.setEnabled(true);
				edit.setEnabled(true);
				mod.setSelectedRow(row);
			}
		}
		if(e.getClickCount() == 2){
			dialog = new EmailTransmissionDlg(new JFrame(), med, mod);
		}
	}

	public EmailTransmissionDlg getDialog() {
		return dialog;
	}
}

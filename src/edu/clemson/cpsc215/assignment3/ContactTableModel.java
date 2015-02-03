/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The ContactTableModel is the class that is used to display the list
 * of contacts for the user to see and select individual contacts to
 * edit, delete, or send email to.  Contacts can be added to the 
 * ContactTableModel via the AddButton and the ContactEditingDlg classes
 * for input from graphical user interfaces.
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Trevor Overfelt, Jacob Collins, William Newberry
 *
 */
public class ContactTableModel extends AbstractTableModel {
	//Column names for the header
	private String [] columnNames = {"Name", "Email", "Address", "Phone"};
	private static final long serialVersionUID = 8481939920099756268L;
	//Set selected row to -1 initially to show that no row is selected
	private int selectedRow = -1;
	
	public void add(Contact c){
		DataStore.getInstance().add(c);
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
	 * For the header
	 */
	@Override
	public String getColumnName(int index) {
		return this.columnNames[index];
	}
	
	@Override
	public int getRowCount() {
		return DataStore.getInstance().contactSize();
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		switch(column){
		case 0:
			return DataStore.getInstance().getContactList().get(row).getName();
		case 1:
			return DataStore.getInstance().getContactList().get(row).
					getEmail();
		case 2:
			return DataStore.getInstance().getContactList().get(row).
					getAddress();
		case 3:
			return DataStore.getInstance().getContactList().get(row).
					getPhone();
		}
		return null;
	}
	
	public void setSelectedRow(int i) {
		selectedRow = i;
	}
	
	public int getSelectedRow() {
		return selectedRow;
	}
}

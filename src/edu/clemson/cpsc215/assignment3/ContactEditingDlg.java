/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The ContactEditingDlg class is used to create a dialog window where
 * the user can edit a selected contact from the ContactTableModel and
 * save the changes to the DataStore.
 */

package edu.clemson.cpsc215.assignment3;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactEditingDlg extends JDialog {

	private static final long serialVersionUID = -9182952873232681243L;
	
	private JLabel labelName, labelAddress, labelEmail, labelPhone;
	private JTextField fieldName,fieldAddress, fieldEmail, fieldPhone;
	private JButton saveButton;
	private CancelButton cancelButton;
	private GridBagConstraints constraints;
	private DataStore data = DataStore.getInstance();
	private MediatorInterface med;
	private MediatorActionListener al = new MediatorActionListener();
	private ContactTableModel mod;
	private Image image;
	private boolean add;
	
	/**
	 * 
	 * @param frame Gives the dialog possession of a JFrame
	 * @param mod Pass the table model
	 * @param med pass the mediator for functionality
	 * 
	 * Construct the dialog by setting its layout, constraints, and icon, also 
	 * passing a flag indicating whether the add or edit button was pressed
	 */
	public ContactEditingDlg(JFrame frame, ContactTableModel mod, 
			MediatorInterface med, boolean FLAG) {
		super(frame, "Add/Edit contacts", true);
		this.mod = mod;
		this.med = med;
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		try {
			image  = ImageIO.read(new File("SmashBall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIconImage(image);
		add = FLAG;
		setupForm();
		pack();
		setLocationRelativeTo(frame);
		this.setVisible(true);
	}
	
	/**
	 * Set up the dialog with appropriate labels and text fields, filling
	 * in the text fields with the selected row if the add flag is set to false
	 */
	private void setupForm() {
		labelName = new JLabel("Name:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(labelName, constraints);	
		fieldName = new JTextField(30);
		if(mod.getSelectedRow() >= 0 && add == false){
			fieldName.setText(data.getContact(
					mod.getSelectedRow()).getName());
		}
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldName, constraints);
		
		labelEmail = new JLabel("Email: ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(labelEmail, constraints);
		fieldEmail = new JTextField(30);
		if(mod.getSelectedRow() >= 0 && add == false){
			fieldEmail.setText(data.getContact(
					mod.getSelectedRow()).getEmail());
		}		
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldEmail, constraints);
		
		labelAddress = new JLabel("Address: ");
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(labelAddress, constraints);
		fieldAddress = new JTextField(30);
		if(mod.getSelectedRow() >= 0 && add == false){
			fieldAddress.setText(data.getContact(
					mod.getSelectedRow()).getAddress());
		}		
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldAddress, constraints);
		
		labelPhone = new JLabel("Phone Number: ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(labelPhone, constraints);
		fieldPhone = new JTextField(30);
		if(mod.getSelectedRow() >= 0 && add == false){
			fieldPhone.setText(data.getContact(
					mod.getSelectedRow()).getPhone());
		}		
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldPhone, constraints);
		
		JPanel buttonPanel = new JPanel();
		if(add == false)
			saveButton = new EditingSave(al, med, this);
		else
			saveButton = new AddSave(al, med, this);
		
		saveButton.setFont(new Font("Arial", Font.BOLD, 16));
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridheight = 2;
		constraints.gridwidth = 2;
		
		buttonPanel.add(saveButton);
		
		cancelButton = new CancelButton(new MediatorActionListener()
				, med, this);
		cancelButton.setFont(new Font("ariel", Font.BOLD, 14));
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, constraints);
	}
	
	public JTextField getFieldName() {
		return fieldName;
	}
	
	public JTextField getFieldEmail() {
		return fieldEmail;
	}
	
	public JTextField getFieldAddress() {
		return fieldAddress;
	}
	
	public JTextField getFieldPhone() {
		return fieldPhone;
	}
	
	public ContactTableModel getModel() {
		return mod;
	}

	/**
	 * Clear the fields after the save button is pressed.
	 */
	public void clearFields() {
		fieldEmail.setText("");
		fieldName.setText("");
		fieldAddress.setText("");
		fieldPhone.setText("");
	}
	
	/**
	 * check to make sure provided email contains basic components like 
	 * "." or "@"
	 */
	public boolean validateFields() {
		boolean retVal = true;
		final Pattern email = java.util.regex.Pattern.compile("^[_A-Za-z0-9-]+"
				+ "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\."
				+ "[A-Za-z]{2,})$");

		if (!email.matcher(fieldEmail.getText()).matches()) {
			retVal = false;		
		}
		return retVal;
	}
}

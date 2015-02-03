/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The ConfigurationDlg is a class that creates a dialog box which
 * allows the user to edit his or her email address currently stored
 * by the Configuration class, along with the IP Address and the password.
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ConfigurationDlg extends JDialog{

	private static final long serialVersionUID = 5578922388424614964L;
	private JLabel labelEmail, labelPass, labelIp, labelPort;
	private JTextField fieldEmail, fieldIp, fieldPort;
	private JPasswordField fieldPass;
	private JButton saveButton;
	private CancelButton cancelButton;
	private GridBagConstraints constraints;
	private MediatorInterface med;
	private MediatorActionListener al = new MediatorActionListener();
	private Image image;
	private ContactTableModel mod;
	
	/**
	 * 
	 * @param frame Gives the dialog possession of a JFrame
	 * @param mod Pass the table model
	 * @param med pass the mediator for functionality
	 * 
	 * Construct the dialog by setting its layout, constraints, and icon
	 */
	public ConfigurationDlg(JFrame frame, ContactTableModel mod,
			MediatorInterface med) {
		super(frame, "Configuration settings", true);
		this.med = med;
		setLayout(new GridBagLayout());
		
		try {
			image  = ImageIO.read(new File("SmashBall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.mod = mod;
		this.setIconImage(image);
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
		setupForm();
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}
	
	/**
	 * Set up the form by instantiating all labels, text fields, and buttons,
	 * filling in the necessary saved information. When creating the buttons,
	 * pass the appropriate dialog object so it knows which one to pull info
	 * form and close
	 */
	
	private void setupForm() {
		labelIp = new JLabel("IP Address:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(labelIp, constraints);	
		fieldIp = new JTextField(30);
		fieldIp.setText(DataStore.getInstance().getConfig().getIPAddr());
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldIp, constraints);
		
		labelPort = new JLabel("Port number: ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(labelPort, constraints);	
		fieldPort = new JTextField(30);
		fieldPort.setText(DataStore.getInstance().getConfig().getPort());
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldPort, constraints);
		
		labelEmail = new JLabel("User Email:");
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(labelEmail, constraints);	
		fieldEmail = new JTextField(30);
		fieldEmail.setText(DataStore.getInstance().getConfig().getEAddr());
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldEmail, constraints);
		
		labelPass = new JLabel("Password: ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(labelPass, constraints);
		fieldPass = new JPasswordField(30);
		fieldPass.setText(DataStore.getInstance().getConfig().getpWrd());
		fieldPass.setEchoChar('*');
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(fieldPass, constraints);
		
		JPanel buttonPanel = new JPanel();
		saveButton = new ConfigSave(al, med, this);
		saveButton.setFont(new Font("Arial", Font.BOLD, 16));
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridheight = 2;
		constraints.gridwidth = 2;
		
		buttonPanel.add(saveButton);
		
		cancelButton = new CancelButton(al, med, this);
		cancelButton.setFont(new Font("ariel", Font.BOLD, 14));
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, constraints);
	}
	
	public JTextField getFieldEmail() {
		return fieldEmail;
	}
	
	public JPasswordField getFieldPass() {
		return fieldPass;
	}
	
	public JTextField getFieldIp() {
		return fieldIp;
	}
	
	public ContactTableModel getModel() {
		return mod;
	}
	
	public JTextField getFieldPort() {
		return fieldPort;
	}
	
	/**
	 * validate the pattern of the provided email, returning false and
	 * providing a pop-up if one is not valid.
	 */
	public boolean validateFields() {
		boolean retVal = true;
		final Pattern email = java.util.regex.Pattern.compile("^[_A-Za-z0-9-]+"
				+ "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\."
				+ "[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		if (!email.matcher(fieldEmail.getText()).matches())
			retVal = false;		
		
		if(!fieldIp.getText().contains("smtp."))
			retVal = false;
		return retVal;
	}
}

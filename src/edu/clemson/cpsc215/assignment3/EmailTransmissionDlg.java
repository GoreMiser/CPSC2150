/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The EmailTransmissionDlg class is used to prepare emails to be sent to 
 * contacts in the ContactTableModel.  The EmailTransmission dialog in this
 * program can only be activated via double clicking a contact.
 */

package edu.clemson.cpsc215.assignment3;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmailTransmissionDlg extends JDialog {

	private static final long serialVersionUID = 5449776992172622465L;
	private JLabel to, bcc, body, cc, subject, attach;
	private JTextArea bodyArea;
	private JTextField toField, bccField, ccField, subjectField, attachField;
	private JButton sender, cancel;
	private GridBagConstraints constraints;
	private MediatorInterface med;
	private ContactTableModel mod;
	private MediatorActionListener al = new MediatorActionListener();
	private Image image;
	
	/**
	 * 
	 * @param frame Gives the dialog possession of a JFrame
	 * @param tMod Pass the table model
	 * @param med pass the mediator for functionality
	 * 
	 * Construct the dialog by setting its layout, constraints, and icon
	 */
	public EmailTransmissionDlg(JFrame frem, MediatorInterface med, 
			ContactTableModel tMod) {
		super(frem, true);
		mod = tMod;
		this.med = med;
		setLayout(new GridBagLayout());
		
		try {
			image = ImageIO.read(new File("SmashBall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIconImage(image);
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weighty = 0.0;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(5, 5, 5, 5);
		setupForm();
		pack();
		setLocationRelativeTo(frem);
		setVisible(true);
	}
	
	/**
	 * Set up the dialog, including the labels, text fields, and button
	 * panel. Do not allow the TO: email to be edited.
	 */
	private void setupForm() {
		DataStore dat = DataStore.getInstance();
		//TO
		to = new JLabel("To:");
		constraints.gridy = 0;
		constraints.gridx = 0;
		add(to, constraints);
		toField = new JTextField(30);
		toField.setText(dat.getContact(mod.getSelectedRow()).getEmail());
		toField.setEditable(false);
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(toField, constraints);
		
		//BCC
		bcc = new JLabel("BCC:");
		constraints.gridy = 1;
		constraints.gridx = 0;
		add(bcc, constraints);
		bccField = new JTextField(30);
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(bccField, constraints);
		
		//CC
		cc = new JLabel("CC:");
		constraints.gridy = 2;
		constraints.gridx = 0;
		add(cc, constraints);
		ccField = new JTextField(30);
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(ccField, constraints);
		
		//SUBJECT
		subject = new JLabel("Subject:");
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(subject, constraints);
		subjectField = new JTextField(30);
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(subjectField, constraints);
			
		//ATTACH
		attach = new JLabel("Attachments:");
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(attach, constraints);
		attachField = new JTextField(30);
		attachField.setToolTipText("Input the path to your file here");
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(attachField, constraints);
		
		//BODY
		body = new JLabel("Body:");
		constraints.gridx = 3;
		constraints.gridy = 0;
		add(body, constraints);
		
		bodyArea = new JTextArea(10, 30);
		bodyArea.setLineWrap(true);
		constraints.gridheight = 4;
		constraints.gridwidth = 3;
		constraints.gridx = 3;
		constraints.gridy = 1;
		JScrollPane sPane = new JScrollPane(bodyArea);
		add(sPane, constraints);
		
		JPanel buttonPanel = new JPanel();
		sender = new SendButton(al, med, this);
		sender.setFont(new Font("Arial", Font.BOLD, 16));
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridheight = 2;
		constraints.gridwidth = 2;
		
		buttonPanel.add(sender);
		
		cancel = new CancelButton(al, med, this);
		cancel.setFont(new Font("Arial", Font.BOLD, 16));
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridheight = 2;
		constraints.gridwidth = 2;
		buttonPanel.add(cancel);
		
		add(buttonPanel, constraints);
	}
	
	public JTextField getRcpt() {
		return toField;
	}
	
	public JTextArea getBody() {
		return bodyArea;
	}
	
	public JTextField getCC() {
		return bccField;
	}
	
	public JTextField getBCC() {
		return bccField;
	}
	
	public JTextField getAttach() {
		return attachField;
			
	}
	
	public JTextField getSubj() {
		return subjectField;
	}

	/**
	 * Clear the text fields after the send button is pressed
	 */
	public void clearFields() {
		toField.setText("");
		bodyArea.setText("");
		attachField.setText("");
		subjectField.setText("");
		ccField.setText("");
		bccField.setText("");
	}
}

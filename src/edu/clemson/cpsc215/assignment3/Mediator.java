/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * The mediator holds a copy of all objects it is mediating. The objects that 
 * are mediated also hold a copy of the mediator. This serves as an 
 * intermediary between the two, and allows the mediator to call the
 * appropriate function by getting the instance of the object that listened
 * for an action.
 * 
 * "Don't call us, we'll call you."
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Mediator implements MediatorInterface {
	private AButton add, delete, edit, cancel, editSave, addSave, 
		configSave, sender;
	private ConfigureMenu config;
	private ExitMenu exit;
	private HelpMenu help;
	private ContactTableModel model;
	private JFrame frame;
	private ContactEditingDlg editDlg;
	private ConfigurationDlg configDlg;
	private SystemInformationDlg info;

	/**
	 * 
	 * @param mod Pass the table model 
	 * @param frame Passed to allow all sub windows to be centered relative 
	 * to the main table
	 */
	public Mediator(ContactTableModel mod, JFrame frame) {
		model = mod;
		this.frame = frame;
	}
	
	public void setEdit(AButton edit) {
		this.edit = edit;
		this.edit.setEnabled(false);
	}
	
	/**
	 * Create the contact editing dialog and disable the edit/delete
	 * buttons until a new row is clicked
	 */
	public void edit() {
		editDlg = new ContactEditingDlg(frame , model, this, false);
		edit.setEnabled(false);
		delete.setEnabled(false);
	}
	
	public void setAdd(AButton add) {
		this.add = add;
	}
	public void add() {
		editDlg = new ContactEditingDlg(frame , model, this, true);
	}
	
	public void setDelete(AButton delete) {
		this.delete = delete;
		this.delete.setEnabled(false);
	}
	
	/**
	 * Delete the selected row and update the table
	 */
	public void delete() {
		int x = JOptionPane.showConfirmDialog(null, "Are you sure?");
		DataStore instance = DataStore.getInstance();
		if(x == JOptionPane.YES_OPTION){
			instance.remove(model.getSelectedRow());
			model.fireTableDataChanged();
			model.setSelectedRow(-1);
			delete.setEnabled(false);
			edit.setEnabled(false);
		}
		else if(x == JOptionPane.NO_OPTION){
			//just exit the dialog
		}
	}

	public void setConfig(ConfigureMenu configureMenu) {
		config = configureMenu;
	}
	
	public void config() {
		configDlg = new ConfigurationDlg(frame, model, this);		
	}

	public void setHelp(HelpMenu helpMenu) {
		this.help = helpMenu;
	}

	public void help() {
		info = new SystemInformationDlg(frame);
	}

	public void setExit(ExitMenu exitMenu) {
		this.exit = exitMenu;
	}
	
	/**
	 * Save data and exit the program
	 */
	public void exit() {
		DataStore instance = DataStore.getInstance();
		instance.saveData();
		System.exit(0);
	}
	
	public void setCancel(AButton cancel) {
		this.cancel = cancel;
	}
	
	/**
	 * Call the cancel function on the dialog that we are currently using
	 */
	public void Cancel(JDialog cancel) {
		model.setSelectedRow(-1);
		cancel.dispose();
	}
	
	public void setEditSave(AButton save) {
		this.editSave = save;
	}

	/**
	 * Retrieve the text fields from the dialog and set the new contact
	 */
	public void editSave(ContactEditingDlg dialog) {
		if(dialog.getFieldEmail().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please provide an email"
					+ " address.", "Invalid Contact", 
					JOptionPane.INFORMATION_MESSAGE);
		} else if(!dialog.validateFields()) {
			JOptionPane.showMessageDialog(null, "Please provide a valid email"
					+ " address.", "Invalid Email", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			String email = dialog.getFieldEmail().getText();
			String name = dialog.getFieldName().getText();
			String address = dialog.getFieldAddress().getText();
			String phone = dialog.getFieldPhone().getText();
			
			Contact edit = DataStore.getInstance().getContact(model.
					getSelectedRow());
			edit.setAddress(address);
			edit.setEmail(email);
			edit.setName(name);
			edit.setPhone(phone);
			
			dialog.getModel().fireTableDataChanged();
			dialog.clearFields();
	
			dialog.dispose();
		}
	}
	
	public void setAddSave(AButton addSave) {
		this.addSave = addSave;
	}

	/**
	 * Check the fields of the editing dialog to make sure a valid email
	 * was provided, displaying a window if invalid. Create a new contact
	 * and add it to the table, then update
	 */
	public void addSave(ContactEditingDlg dialog) {
		if(dialog.getFieldEmail().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please provide an email"
					+ " address.", "Invalid Contact", 
					JOptionPane.INFORMATION_MESSAGE);
					
		} else if(!dialog.validateFields()) {
			JOptionPane.showMessageDialog(null, "Please provide a valid email"
					+ " address.", "Invalid Email", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			String email = dialog.getFieldEmail().getText();
			String name = dialog.getFieldName().getText();
			String address = dialog.getFieldAddress().getText();
			String phone = dialog.getFieldPhone().getText();
			
			DataStore instance = DataStore.getInstance();
			Contact edit = new Contact();
			
			edit.setAddress(address);
			edit.setName(name);
			edit.setEmail(email);
			edit.setPhone(phone);
			instance.add(edit);
			dialog.getModel().fireTableDataChanged();
			dialog.clearFields();
	
			dialog.dispose();
		}
	}
	
	public void setConfigSave(AButton configSave) {
		this.configSave = configSave;
	}
	
	/**
	 * Allow the user to change their configuration settings
	 * Input the ip address such as "smtp.gmail.com" to be used for
	 * email transmission, the email address of the user, and the password
	 * for authentication. Provide output if valid email/ password is
	 * not provided
	 */
	public void saveConfig(ConfigurationDlg dialog) {
		String pass = new String(dialog.getFieldPass().getPassword());
		String ip = dialog.getFieldIp().getText();
		String port = dialog.getFieldPort().getText();
		
		if(pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please provide a password"
					, "Invalid Password", 
					JOptionPane.INFORMATION_MESSAGE);
		}else if(ip.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please provide an ip "
						+ "configuration" +
						"\n Example: smtp.gmail.com", "Invalid Configuration", 
						JOptionPane.INFORMATION_MESSAGE);
		}else if(port.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please provide a valid port "
					+ "number"
					, "Invalid Configuration", 
					JOptionPane.INFORMATION_MESSAGE);
		} else if(!dialog.validateFields()) {
			JOptionPane.showMessageDialog(null, "Configuration invalid, please"
					+ " check your settings.", "Invalid Configuration", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			String email = dialog.getFieldEmail().getText();
			
			DataStore instance = DataStore.getInstance();
			
			instance.getConfig().setpWrd(pass);
			instance.getConfig().setEAddr(email);
			instance.getConfig().setIPAddr(ip);
			instance.getConfig().setPort(port);
		
			dialog.dispose();
		}
	}
	
	public void setSend(AButton sendButton) {
		this.sender = sendButton;
	}
	
	/**
	 * Send the email by passing all of the text fields to an instance of
	 * a class that creates the email
	 */
	public void sendEmail(EmailTransmissionDlg emailDialog) {
		MailMan danny = new MailMan(DataStore.getInstance().getConfig());
		danny.makeNSend(emailDialog.getBody().getText(), emailDialog.
				getAttach().getText(), emailDialog.getSubj().getText(),
				emailDialog.getRcpt().getText(), emailDialog.getCC().
				getText(), emailDialog.getBCC().getText());
		emailDialog.clearFields();
		emailDialog.dispose();
	}
	
	//Getters setters
	public AButton getAdd() {
		return add;
	}
	
	public AButton getCancel() {
		return cancel;
	}
	
	public AButton getEditSave() {
		return editSave;
	}
	
	public AButton getAddSave() {
		return addSave;
	}
	
	public AButton getConfigSave() {
		return configSave;
	}
	
	public AButton getSender() {
		return sender;
	}
	
	public ConfigureMenu getConfig() {
		return config;
	}
	
	public ExitMenu getExit() {
		return exit;
	}
	
	public HelpMenu getHelp() {
		return help;
	}
	
	public ContactEditingDlg getEditDlg() {
		return editDlg;
	}
	
	public ConfigurationDlg getConfigDlg() {
		return configDlg;
	}
	
	public SystemInformationDlg getInfo() {
		return info;
	}
	
}

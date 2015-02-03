/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * Contains all the the functionality used by the mediator class
 * Includes the setting of objects as well as the execution of
 * their functions
 */

package edu.clemson.cpsc215.assignment3;

import javax.swing.JDialog;

public interface MediatorInterface {
		public void add();
		public void delete();
		public void edit();
		public void setAdd(AButton add);
		public void setDelete(AButton delete);
		public void setEdit(AButton edit);
		public void setConfig(ConfigureMenu configureMenu);
		public void config();
		public void setHelp(HelpMenu helpMenu);
		public void help();
		public void setExit(ExitMenu exitMenu);
		public void exit();
		public void Cancel(JDialog cancel);
		public void setCancel(AButton cancel);
		public void setEditSave(AButton save);
		public void editSave(ContactEditingDlg dialog);
		public void setAddSave(AButton addSave);
		public void addSave(ContactEditingDlg dialog);
		public void setConfigSave(AButton configSave);
		public void saveConfig(ConfigurationDlg dialog);
		public void sendEmail(EmailTransmissionDlg dialog);
		public void setSend(AButton sendButton);
}

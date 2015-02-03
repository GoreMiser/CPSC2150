/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * Displays information about the application and its designers. This
 * Dialog is modal, disabling focus until the window is exited.
 */

package edu.clemson.cpsc215.assignment3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SystemInformationDlg extends JDialog {

	private static final long serialVersionUID = 3556583394307479664L;
	private String info;
	
	/**
	 * 
	 * @param frame Gives the dialog possession of the frame
	 */
	public SystemInformationDlg(JFrame frame) {
		info = "\nVersion: 2.0 \n\nThis is the TVCMail email client.\n" +
				"Made by TVC Corporation For Human Improvement.\n\n" +
				"CopyRight:  2014\n\n";
		BufferedImage image;
		try {
			image = ImageIO.read(new File("SmashBall.png"));
			ImageIcon icon = new ImageIcon(image);
			JOptionPane.showMessageDialog(
					frame,
					info,
					"About", JOptionPane.INFORMATION_MESSAGE,
					icon);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * Create the basic window for the program used to hold all of the contacts,
 * menu items for setting configurations, viewing the system info, and 
 * exiting the program, as well as buttons for adding/editing and deleting
 * the existing contacts.
 */

package edu.clemson.cpsc215.assignment3;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MainFrame extends JFrame {

	private JFrame frame;
	private MediatorInterface med;
	private ContactTableModel m;
	private static final long serialVersionUID = 7397735497772946654L;

	/**
	 * 
	 * @param m Pass the table model for creation of our JTable
	 * and contact list
	 */
	public MainFrame(ContactTableModel m){
		/*create the frame to hold it all, name it, set default size, 
		give it a layout*/
		this.m = m;
		frame = new JFrame("TVCMail");
		med = new Mediator(m, frame);
		
		/*Set the action listener for saving and disposing the 
		  window upon close*/
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			/** 
			 * Delete the existing files in the folder and replace them with
			 * the existing files. 
			 */
			@Override
			public void windowClosing(WindowEvent arg0) {
				DataStore data = DataStore.getInstance();
				File [] configFiles = data.getConfigFile().listFiles();
				File [] contactFiles = data.getContactFile().listFiles();
				for(int i = 0; i < configFiles.length; ++i)
					configFiles[i].delete();
				for(int i = 0; i < contactFiles.length; ++i)
					contactFiles[i].delete();
				data.saveData();
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}});
		
		frame.setSize(854, 480);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setSize(600, 480);
		frame.setLocationRelativeTo(null);
		
		this.pack();
		this.setup();	
	}
	
	/**
	 * Set up the frame by adding all of the contacts, setting up the header
	 * and adding the buttons and menu items.
	 */
	public void setup() {
		try {
			Image image = ImageIO.read(new File("SmashBall.png"));
			frame.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//create the menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(255, 255, 255));
		
		//create the File menu
		JMenu firstMenu = new JMenu("File");
		ExitMenu exit = new ExitMenu(new MediatorActionListener(), med);
		firstMenu.add(exit);
		
		//create the Config menu
		JMenu secondMenu = new JMenu("Configuration");
		ConfigureMenu configure = new ConfigureMenu(new 
				MediatorActionListener(), med);
		secondMenu.add(configure);
		
		//create the Help menu
		JMenu thirdMenu = new JMenu("Help");
		HelpMenu help = new HelpMenu(new MediatorActionListener(), med);
		thirdMenu.add(help);
				
		//add each menu to the menuBar, place into the frame
		menuBar.add(firstMenu);
		menuBar.add(secondMenu);
		menuBar.add(thirdMenu);
		frame.setJMenuBar(menuBar);
		
		//create the table for the contacts and add it i
		JTable table = new JTable(m);
		
		//create the Add Edit and Delete buttons
		JButton addButton = new AddButton(new MediatorActionListener(),
				med, null);
		JButton editButton = new EditButton(new MediatorActionListener(), 
				med, null);
		JButton deleteButton = new DeleteButton(new MediatorActionListener(),
				med, null);
		
		//put the buttons in a panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		
		//add the panel below the table
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBackground(Color.BLUE);
		scroll.setViewportView(table);
		scroll.getViewport().setBackground(Color.DARK_GRAY);
		table.setBackground(Color.LIGHT_GRAY);
		JTableHeader header = table.getTableHeader();
		TableColumnModel tcm = header.getColumnModel();
		TableColumn tc1 = tcm.getColumn(0);
		TableColumn tc2 = tcm.getColumn(1);
		TableColumn tc3 = tcm.getColumn(2);
		TableColumn tc4 = tcm.getColumn(3);
		tc1.setHeaderValue(this.m.getColumnName(0));
		tc2.setHeaderValue(this.m.getColumnName(1));
		tc3.setHeaderValue(this.m.getColumnName(2));
		tc4.setHeaderValue(this.m.getColumnName(3));

		frame.getContentPane().add(scroll, BorderLayout.NORTH);
		
		table.addMouseListener(new SelectMouseAdapter(deleteButton, editButton,
				m, med));
		
		//make it visible
		frame.setVisible(true);
	}
	
	public MediatorInterface getMediator() {
		return this.med;
	}
}

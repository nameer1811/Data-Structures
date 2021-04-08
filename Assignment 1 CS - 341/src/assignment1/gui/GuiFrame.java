package assignment1.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import assignment1.Main;
import assignment1.customer.Customer;
import assignment1.video.RentalManager;
import assignment1.video.Video;

/**
 * 
 * This class is the GUI for the main program.
 *
 */
public class GuiFrame {

	// data members
	public static String DEFAULT_SEARCH_MESSAGE = "Search by Name or ID";

	private JFrame mainFrame;
	private JPanel mainPanel;
	private JButton videoButton;
	private JButton customerButton;
	private JButton changeCustomerButton;
	private JButton exitButton;

	private JPanel infoPanel;
	private JTextArea infoArea;
	private JButton infoButton;
	private JTextArea searchBar;

	private JPanel scrollPanel;
	private JScrollPane scroll;
	private JList<String> scrollList;

	private JLabel name;
	private JLabel vidList;
	private JLabel vidInfo;

	private Dimension WINDOW_DIM = new Dimension(950, 700); // the dimension of the main window
	private GuiMenu curMenu = null;

	// Constructor for the class.
	public GuiFrame() {
		mainFrame = new JFrame("Assignment 1 by Ashton, Fahad, and Ilea"); // Frame name in the top bar
		mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 20)); // Structure for our GUI
		mainFrame.setPreferredSize(WINDOW_DIM); // dimension for our window
		mainFrame.setMinimumSize(WINDOW_DIM); // setting the minimum size of the window
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setting the close operation for the window
		mainFrame.addComponentListener(new GuiComponentListener(this)); // adding hooks for component events
		mainFrame.addWindowStateListener(new GuiWindowStateListener(this)); // adding hooks for window state events
		mainFrame.addWindowListener(new GuiWindowListener()); // adding hooks for window events
		mainFrame.getContentPane().setBackground(GuiColors.MAIN_BACK); // setting the background color of main window

		Font butFont = new Font("Times New Roman", Font.BOLD, 20); // setting font into a variable to use it in multiple
																	// places

		name = new JLabel("Welcome " + (Main.isManager() ? "Manager!" : Main.getCurrentCustomer().getName() + "!")); // label
																														// for
																														// our
																														// top
																														// panel,
																														// if
																														// it
																														// is
																														// in
																														// manager
																														// side
																														// it
																														// will
																														// say
																														// Welcome
																														// Manager
																														// else
																														// it
																														// will
																														// say
																														// Welcome
																														// *Customer
																														// Name*
		vidList = new JLabel(Main.isManager() ? "List of Customers" : "List of Videos"); // label inside of main panel,
																							// if it is in manager side
																							// it will say List of
																							// Customers else it will
																							// say Welcome List of
																							// Videos
		vidInfo = new JLabel(Main.isManager() ? "Customer Information" : "Video Information"); // label inside of main
																								// panel, if it is in
																								// manager side it will
																								// say Customer
																								// Information else it
																								// will say Welcome
																								// Video Information
		vidInfo.setHorizontalAlignment(SwingConstants.CENTER); // setting horizontal alignment of vidInfo button
		vidList.setHorizontalAlignment(SwingConstants.CENTER); // setting horizontal alignment of vidList button
		name.setHorizontalAlignment(SwingConstants.CENTER); // setting horizontal alignment of name button
		// setting the font of the buttons
		name.setFont(butFont);
		vidList.setFont(butFont);
		vidInfo.setFont(butFont);

		videoButton = new JButton("ALL VIDEOS"); // creating a new button called All Videos
		videoButton.setAlignmentY(JComponent.LEFT_ALIGNMENT); // setting vertical alignment of the button
		videoButton.setFont(butFont); // setting font of the button
		videoButton.setFocusPainted(false); // setting the focus on the button to false
		videoButton.addMouseListener(new GuiMouseListener(this, GuiButton.VIDEO_MENU)); // adding a mouse listener to
																						// specifying which button it is
																						// with the GuiButton enum

		customerButton = new JButton(Main.isManager() ? "ALL CUSTOMERS" : "MY ACCOUNT");// creating a new button called
																						// All Customers/My Account
																						// depending on manager side or
																						// customer side
		customerButton.setAlignmentY(JComponent.RIGHT_ALIGNMENT); // setting vertical alignment of the button
		customerButton.setFont(butFont);// setting font of the button
		customerButton.setFocusPainted(false);// setting the focus on the button to false
		customerButton.addMouseListener(new GuiMouseListener(this, GuiButton.CUST_MENU));// adding a mouse listener
																							// specifying which button
																							// it is with the GuiButton
																							// enum

		changeCustomerButton = new JButton("CHANGE CUSTOMER");// creating a new button called Change Customer
		changeCustomerButton.setAlignmentY(JComponent.LEFT_ALIGNMENT); // setting vertical alignment of the button
		changeCustomerButton.setFont(butFont);// setting font of the button
		changeCustomerButton.setFocusPainted(false);// setting the focus on the button to false
		changeCustomerButton.addMouseListener(new GuiMouseListener(this, GuiButton.CUST_CHANGE));// adding a mouse
																									// listener
																									// specifying which
																									// button it is with
																									// the GuiButton
																									// enum
		changeCustomerButton.setVisible(!Main.isManager()); // it will not be visible in manager side

		exitButton = new JButton("EXIT");// creating a new button called Exit
		exitButton.setAlignmentY(JComponent.RIGHT_ALIGNMENT); // setting vertical alignment of the button
		exitButton.setFont(butFont);// setting font of the button
		exitButton.setFocusPainted(false);// setting the focus on the button to false
		exitButton.addMouseListener(new GuiMouseListener(this, GuiButton.EXIT));// adding a mouse listener specifying
																				// which button it is with the GuiButton
																				// enum

		mainPanel = new JPanel(); // creating a new JPanel called mainPanel
		mainPanel.setBackground(GuiColors.MAIN_PANEL); // setting the background color of the panel
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 20)); // structure for our panel

		infoPanel = new JPanel(); // creating a new JPanel called infoPanel
		infoPanel.setBackground(GuiColors.INFO_PANEL); // setting the background color of the panel

		infoArea = new JTextArea(); // creating a new JTextArea called infoArea
		infoArea.setEditable(false); // text area will not be editable
		infoArea.setFont(butFont); // setting the font of the text area
		infoArea.setText("Welcome " + (Main.isManager() ? "Manager!" : Main.getCurrentCustomer().getName() + "!")); // setting
																													// the
																													// default
																													// text
																													// of
																													// the
																													// text
																													// area.
																													// Changes
																													// depending
																													// on
																													// manager
																													// side
																													// or
																													// client
																													// side
		infoArea.setWrapStyleWord(true); // specifies to wrap texts by words

		infoButton = new JButton("VIDEO");// creating a new button called All Videos
		infoButton.setAlignmentY(JComponent.LEFT_ALIGNMENT); // setting vertical alignment of the button
		infoButton.setFont(butFont);// setting font of the button
		infoButton.setFocusPainted(false);// setting the focus on the button to false
		infoButton.addMouseListener(new GuiMouseListener(this, GuiButton.INFO_BUTTON));// adding a mouse listener
																						// specifying which button it is
																						// with the GuiButton enum
		infoButton.setVisible(!Main.isManager()); // it will not be visible in manager side

		searchBar = new JTextArea(); // creating a new JTextArea called searchBar
		searchBar.setEditable(true); // text area will be editable
		searchBar.setForeground(Color.LIGHT_GRAY); // setting the foreground color of the text area
		searchBar.setFont(new Font("Aerial", Font.BOLD, 12)); // setting the font and size of the texts
		searchBar.setWrapStyleWord(true); // specifies to wrap texts by words
		searchBar.addKeyListener(new SearchKeyListener(this)); // adding a key listener to search bar
		searchBar.addFocusListener(new SearchFocusListener()); // adding a focus listener to the search bar
		searchBar.setText(DEFAULT_SEARCH_MESSAGE); // setting a default message in the search bar

		scrollPanel = new JPanel(); // creating a new JPanel called scrollPanel
		scrollPanel.setBackground(GuiColors.SCROLL_PANEL); // setting the color of the background of the scrollPanel

		scroll = new JScrollPane(); // creating a new JScrollPane called scroll
		scroll.setAlignmentX(JComponent.RIGHT_ALIGNMENT); // setting the horizontal alignment of the pane
		scroll.setBackground(GuiColors.LIST_PANEL); // setting the color of the background of the scroll pane

		scrollList = new JList<String>(); // creating a new JList
		scrollList.setLayoutOrientation(JList.VERTICAL); // setting the orientation of the layout of the list
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // setting the selection method of the list
		scrollList.addMouseListener(new GuiMouseListener(this, GuiButton.VIDEO_LIST));// adding a mouse listener
																						// specifying which button it is
																						// with the GuiButton enum
		scrollList.setFont(butFont); // setting the font of the list

		/**
		 * Adding all the components in the window
		 */
		// adding components to JFrame called mainFrame
		mainFrame.add(name);
		mainFrame.add(videoButton);
		mainFrame.add(customerButton);
		mainFrame.add(mainPanel);
		mainFrame.add(changeCustomerButton);
		mainFrame.add(exitButton);
		// adding components to JPanel called mainPanel
		mainPanel.add(vidList);
		mainPanel.add(searchBar);
		mainPanel.add(vidInfo);
		mainPanel.add(scrollPanel);
		mainPanel.add(infoPanel);

		// adding components to JPanel called infoPanel
		infoPanel.add(infoArea);
		infoPanel.add(infoButton);

		scroll.getViewport().add(scrollList); // adding the list of information to the scroll panel
		scrollPanel.add(scroll); // adding scroll list to a JPanel called scrollPanel

		this.resize(WINDOW_DIM); // executing the size of all the components depending on the window size
		this.switchMenu(GuiMenu.MY_VIDEOS); // defaults to My Account/All Customers (depending on manager side or
											// customer side) and then we can switch around to All Videos when clicked

		mainFrame.pack(); // the window and its components to be sized to our preferred size
		mainFrame.setVisible(true); // shows the window
	}

	/**
	 * Method to resize the dimension of the components every time the window size
	 * changes
	 */
	private void resize(Dimension dim) {
		// setting the size of components
		videoButton.setPreferredSize(new Dimension((int) (dim.getWidth() / 3), 32));
		customerButton.setPreferredSize(new Dimension((int) (dim.getWidth() / 3), 32));
		mainPanel.setPreferredSize(new Dimension((int) (dim.getWidth()), (int) (dim.getHeight() - 188 - 50)));
		changeCustomerButton.setPreferredSize(new Dimension((int) (dim.getWidth() / 3), 32));
		exitButton.setPreferredSize(new Dimension((int) (dim.getWidth() / 3), 32));
		name.setPreferredSize(new Dimension((int) (dim.getWidth()), 30));
		vidList.setPreferredSize(new Dimension((int) (dim.getWidth() / 4 - 30), 20));
		vidInfo.setPreferredSize(new Dimension((int) (dim.getWidth() / 2) - 30, 20));

		scrollPanel
				.setPreferredSize(new Dimension((int) (dim.getWidth() / 2 - 16), (int) (dim.getHeight() - 188 - 138)));
		infoPanel.setPreferredSize(new Dimension((int) (dim.getWidth() / 2 - 32), (int) (dim.getHeight() - 188 - 138)));

		dim = scrollPanel.getPreferredSize();
		scroll.setPreferredSize(new Dimension((int) (dim.getWidth() - 36), (int) dim.getHeight() - 10));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		dim = infoPanel.getPreferredSize();
		infoArea.setPreferredSize(new Dimension((int) (dim.getWidth() - 36), (int) dim.getHeight() - 48));
		infoButton.setPreferredSize(new Dimension((int) (dim.getWidth() - 36), 32));
		searchBar.setPreferredSize(new Dimension((int) (dim.getWidth() / 2 - 30), 20));
	}

	/**
	 * Method to update the scroll list by filtering it as we type letters in the
	 * search bar
	 */
	private void updateFilter(String[] update) {
		String filter = searchBar.getText(); // variable containing the text in search bar
		scrollList.setListData(Main.filterList(update,
				filter.equals("") ? null : filter.equals(DEFAULT_SEARCH_MESSAGE) ? null : filter)); // checks if the
																									// search bar is
																									// empty or equal to
																									// the default
																									// message, if it is
																									// not it starts
																									// filtering
	}

	/**
	 * Method to switch from menus from All Videos to My Account/All Customers
	 * depending on customer side or manager side
	 */
	private void switchMenu(GuiMenu menu) {
		this.curMenu = menu; // setting the object to menu so that we can switch around menus
		switch (menu) // using switch statements to switch around the cases
		{
		case ALL_VIDEOS: // if the case is ALL_VIDEOS
		{
			infoButton.setText("Rent Video"); // set the infoButton text to Rent Video
			this.updateFilter(RentalManager.getInstance().getVidListIDs()); // filters the complete video list from
																			// rental manager
			break;
		}
		case MY_VIDEOS: // if the case is MY_VIDEOS
		{
			infoButton.setText("Return Video"); // set the infoButton text to Return Video
			if (Main.isManager()) // if it is in manager side
			{
				this.updateFilter(RentalManager.getInstance().getDropDownIds()); // filters the complete customer list
																					// from rental manager
			} else {
				this.updateFilter(Main.getCurrentCustomer().getRentedVidIDList()); // filters the complete video list
																					// from rental manager
			}
			break;
		}
		}
	}

	/**
	 * Class to add component listener to the GuiFrame
	 */
	private class GuiComponentListener implements ComponentListener {
		private GuiFrame frame; // data member

		// constructor
		private GuiComponentListener(GuiFrame fr) {
			frame = fr;
		}

		public void componentHidden(ComponentEvent arg0) {
		}

		public void componentMoved(ComponentEvent arg0) {
		}

		public void componentShown(ComponentEvent arg0) {
		}

		// method to resize the components
		public void componentResized(ComponentEvent arg0) {
			Dimension dim = arg0.getComponent().getSize(); // creating a dimension variable
			frame.resize(dim); // updating the frame size
		}

	}

	/**
	 * Class to add mouse listener to GuiFrame
	 *
	 */
	private class GuiMouseListener implements MouseListener {
		// data members
		private GuiFrame frame;
		private GuiButton buttonUsing;

		// constructor
		private GuiMouseListener(GuiFrame fr, GuiButton but) {
			this.frame = fr;
			this.buttonUsing = but;
		}

		// method to do what happens when we click the menus
		public void mousePressed(MouseEvent e) {
			switch (buttonUsing) // using switch statements
			{
			case CUST_MENU: // if the case is CUST_MENU
			{
				frame.switchMenu(GuiMenu.MY_VIDEOS); // menu switches to My Account menu
				// vidList and vidInfo changes text based on customer side or manager side
				vidList.setText(Main.isManager() ? "List of Customers" : "List of Videos");
				vidInfo.setText(Main.isManager() ? "Customer Information" : "Video Information");
				break;
			}
			case VIDEO_MENU: // if the case is VIDEO_MENU
			{
				frame.switchMenu(GuiMenu.ALL_VIDEOS); // menu switches to All Videos menu
				// vidList and vidInfo changes text based on customer side or manager side
				vidList.setText("List of Videos");
				vidInfo.setText("Video Information");
				break;
			}
			case CUST_CHANGE: // if the case is CUST_CHANGE
			{
				Main.setCurrentCustomer(); // setting the current customer

				if (this.frame.curMenu == GuiMenu.MY_VIDEOS) // if the current menu is equal to MY_VIDEOS
				{
					this.frame.updateFilter(Main.getCurrentCustomer().getRentedVidIDList()); // update the list with
																								// rented videos of the
																								// current customer
					this.frame.name.setText(
							"Welcome " + (Main.isManager() ? "Manager!" : Main.getCurrentCustomer().getName() + "!"));
				} // setting text based on manager side and customer side

				break;
			}
			case INFO_BUTTON: // if the case is INFO_BUTTON
			{
				// Renting
				if (this.frame.curMenu == GuiMenu.ALL_VIDEOS) // if the current menu is equal to ALL_VIDEOS
				{
					String selected = this.frame.scrollList.getSelectedValue(); // getting the currently selected
																				// element in the list
					if (selected != null && selected.length() > 0) // if selected is not null and the length of element
																	// is greater than 0
					{
						Video vid = RentalManager.getInstance()
								.getVideoByID(Integer.parseInt(selected.split(" - ")[1])); // rental manager finds it by
																							// the ID of the current
																							// selection and put it in
																							// video object
						RentalManager.getInstance().rentVideo(Main.getCurrentCustomer(), vid); // renting video by
																								// current customer and
																								// video object
						this.frame.infoArea.setText(vid.getInfoForGUI()); // setting text in the infoArea
					}
				}
				// Returning
				else {
					String selected = this.frame.scrollList.getSelectedValue(); // getting the currently selected
																				// element in the list
					if (selected != null && selected.length() > 0) // if selected is not null and length is greater than
																	// 0
					{
						Video vid = RentalManager.getInstance()
								.getVideoByID(Integer.parseInt(selected.split(" - ")[1])); // rental manager finds it by
																							// the ID of the current
																							// customer and put it in
																							// video object
						RentalManager.getInstance().returnVideo(vid); // returning video
						this.frame.infoArea.setText(vid.getInfoForGUI()); // setting text in the infoArea
						this.frame.updateFilter(Main.getCurrentCustomer().getRentedVidIDList()); // updating the list
																									// after we returned
																									// the video
					}
				}
				break;
			}
			case EXIT: // if the case is EXIT
			{
				RentalManager.getInstance().saveRentals(); // save the rentals
				System.exit(0); // exit program
				break;
			}
			case VIDEO_LIST: // if the case is VIDEO_LIST
			{
				String selected = this.frame.scrollList.getSelectedValue();// getting the currently selected element in
																			// the list
				if (selected != null && selected.length() > 0) // if selected is not null and length is greater than 0
				{
					if (Main.isManager() && this.frame.curMenu == GuiMenu.MY_VIDEOS) // if we are in manager and current
																						// menu is equal to MY_VIDEOS
					{
						Customer cust = RentalManager.getInstance()
								.getCustomerByID(Integer.parseInt(selected.split(" - ")[1])); // customer is selected
						this.frame.infoArea.setText(cust.getInfoForGUI()); // set text about the customer
					} else {
						Video vid = RentalManager.getInstance()
								.getVideoByID(Integer.parseInt(selected.split(" - ")[1])); // video is selected
						this.frame.infoArea.setText(vid.getInfoForGUI()); // set text about the video
					}
				}

				break;
			}
			}
		}

		// unused call backs from the interface
		public void mouseClicked(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

	/**
	 * Class to add WindowListener to GuiFrame
	 */
	private class GuiWindowListener implements WindowListener {

		public void windowClosing(WindowEvent arg0) {
			RentalManager.getInstance().saveRentals(); // when window closes save the current rentals
		}

		// unused call backs from the interface
		public void windowActivated(WindowEvent arg0) {
		}

		public void windowClosed(WindowEvent arg0) {
		}

		public void windowDeactivated(WindowEvent arg0) {
		}

		public void windowDeiconified(WindowEvent arg0) {
		}

		public void windowIconified(WindowEvent arg0) {
		}

		public void windowOpened(WindowEvent arg0) {
		}
	}

	/**
	 * Class to add WindowStateListener to GuiFrame
	 */
	private class GuiWindowStateListener implements WindowStateListener {
		private GuiFrame frame; // data member

		// constructor
		private GuiWindowStateListener(GuiFrame fr) {
			this.frame = fr;
		}

		private boolean isMaximized(int state) {
			return (state & frame.mainFrame.MAXIMIZED_BOTH) == frame.mainFrame.MAXIMIZED_BOTH; // bit flag check to see
																								// if the bit holds the
																								// check that maximizes
																								// both
		}

		public void windowStateChanged(WindowEvent e) {
			boolean isMaximized = isMaximized(e.getNewState()); // checking if it is maximized
			boolean wasMaximized = isMaximized(e.getOldState()); // checking if it was maximized

			if (isMaximized && !wasMaximized) // if it was not maximized and is maximized
			{
				this.frame.resize(e.getComponent().getSize()); // resize the frame by getting the current component size
			} else if (wasMaximized && !isMaximized) // if it was maximized and is not maximized
			{
				this.frame.resize(e.getComponent().getSize()); // resize the frame by getting the current component size
			}
		}

	}

	/**
	 * Class to add KeyListener to GuiFrame
	 */
	private class SearchKeyListener implements KeyListener {
		private GuiFrame frame; // data members

		// constructor
		private SearchKeyListener(GuiFrame frame) {
			this.frame = frame;
		}

		public void keyTyped(KeyEvent e) {
			updateList(); // update the list when key is typed
		}

		public void keyReleased(KeyEvent e) {
			updateList(); // update the list when key is released
		}

		public void keyPressed(KeyEvent e) {
		}

		private void updateList() {
			if (Main.isManager() && frame.curMenu == GuiMenu.MY_VIDEOS) {
				this.frame.updateFilter(RentalManager.getInstance().getDropDownIds());
			} else if (frame.curMenu == GuiMenu.ALL_VIDEOS) {
				this.frame.updateFilter(RentalManager.getInstance().getVidListIDs());
			} else {
				this.frame.updateFilter(Main.getCurrentCustomer().getRentedVidIDList());
			}
		}

	}

	/**
	 * Class to add FocusListener to GuiFrame
	 *
	 */
	private class SearchFocusListener implements FocusListener {
		public void focusGained(FocusEvent e) {
			searchBar.setFont(new Font("Aerial", Font.BOLD, 12)); // setting the font of the search bar
			searchBar.setText(""); // setting the text of search bar
			searchBar.setForeground(Color.BLACK); // setting the color of the text of the search bar
		}

		public void focusLost(FocusEvent e) {

			searchBar.setFont(new Font("Aerial", Font.BOLD, 12)); // setting the font of the search bar
			searchBar.setText(DEFAULT_SEARCH_MESSAGE); // setting the text of search bar
			searchBar.setForeground(Color.LIGHT_GRAY); // setting the color of the text of the search bar
		}

	}

	// identifiers for buttons
	private enum GuiButton {
		CUST_MENU, VIDEO_MENU, CUST_CHANGE, INFO_BUTTON, EXIT, VIDEO_LIST;
	}

	// identifiers for the current menu
	private enum GuiMenu {
		ALL_VIDEOS, MY_VIDEOS;
	}
}

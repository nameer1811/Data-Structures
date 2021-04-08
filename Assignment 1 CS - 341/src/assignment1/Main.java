package assignment1;

import javax.swing.JOptionPane;

import assignment1.customer.Customer;
import assignment1.gui.GuiFrame;
import assignment1.video.RentalManager;

/**
 * Assignment 1 for CS341 with professor Iyengar.
 * 
 * @author Ashton Schultz, Sheikh Fahad, Ilea Stone
 * @date 9.20.2020
 */
public class Main {
	// making and initializing the data members
	public static final String MANAGER_PASSWORD = "CS341";
	private static boolean IS_MANAGER = false;
	private static Customer CURRENT_CUSTOMER = null;

	public static void main(String... args) {
		// A login didn't happen if this isn't true
		if (RentalManager.getInstance().initializeData() != true) // if it cannot loadd the data
		{
			System.err.println("There was an error initializing the data for the program."); // print error message
			System.exit(0); // exit the program
		}

		if (loginWindow().isSelected()) // if the login process is done
		{
			new GuiFrame(); // create the GUI
		}

	}

	/**
	 * Method to check if in manager side or customer side
	 */
	public static boolean isManager() {
		return IS_MANAGER; // return is manager
	}

	/**
	 * Method to get the current customer, it will be null on manager side
	 */
	public static Customer getCurrentCustomer() {
		return CURRENT_CUSTOMER; // returning the current customer
	}

	/**
	 * Method to prompt the user to select which customer they are
	 */
	public static void setCurrentCustomer() {
		Object selected = JOptionPane.showInputDialog(null, "Choose", "Menu", JOptionPane.PLAIN_MESSAGE, null,
				RentalManager.getInstance().getDropDownIDs(), RentalManager.getInstance().getDropDownIDs()[0]); // choosing
																												// the
																												// customer
																												// from
																												// drop
																												// down
		if (selected != null) // if selected is not null
		{
			String id = selected.toString(); // id is selected
			Main.CURRENT_CUSTOMER = RentalManager.getInstance().getCustomerByDropDownID(id); // getting current customer
																								// object from the
																								// selected
		} else {
			System.out.println("No customer was selected.."); // if nothing is selected print message
		}
	}

	/**
	 * Method to the login process
	 */
	private static IntroState loginWindow() {
		IntroState enteredState = IntroState.FRESH; // default state
		while (!enteredState.isSelected()) // while the login process has not been completed
		{
			int result = askForManager(); // which button was pressed from JOptionPane
			if (result == JOptionPane.NO_OPTION) // if result is the no (Manager) button
			{
				while (true) {
					String enteredPass = askPass(enteredState == IntroState.INVALID_PASS); // asks for the password,
																							// shows message if wrong
																							// password
					if (enteredPass == null) // if password is null
					{
						enteredState = IntroState.INVALID_PASS; // goes back to the select for the customer or manager
																// screen
						break; // get out of the loop
					} else {
						boolean correctPassword = enteredPass.equals(MANAGER_PASSWORD); // if correct password is
																						// entered

						if (correctPassword) // if true
						{
							enteredState = IntroState.MANAGER; // enter manager side
							break; // get out of the loop
						} else {
							enteredState = IntroState.INVALID_PASS; // ask for the password again
						}
					}
				}
			} else if (result == JOptionPane.CLOSED_OPTION) // if 'X' is clicked
			{
				System.exit(0); // exit the program
				break; // get out of the loop
			} else {
				enteredState = IntroState.CUST; // enter the customer side
				break; // get out of the loop
			}
		}

		if (enteredState == IntroState.CUST) // if in customer side
		{
			// Entering client side.
			setCurrentCustomer(); // method to prompt the user to select which customer they are
			if (CURRENT_CUSTOMER == null) // if current customer is null
			{
				loginWindow(); // go back to login window
			}
		} else if (enteredState == IntroState.MANAGER) // if user is manager
		{
			IS_MANAGER = true; // Correct password entered, entering manager side.
		}

		return enteredState; // returning the entered state of the user
	}

	/**
	 * Method to ask for manager or customer
	 */
	private static int askForManager() {
		return JOptionPane.showOptionDialog(null, "Choose the login type:", "Login", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Customer", "Manager" }, null); // choosing the
																										// login type
	}

	/**
	 * Method for asking for the password
	 */
	private static String askPass(boolean retry) {
		return JOptionPane.showInputDialog(null,
				(retry ? "Incorrect password. Re-" : "") + "Enter the manager password to access the manager screen:",
				MANAGER_PASSWORD); // asking for the manager password
	}

	/**
	 * Method to filter list
	 */
	public static String[] filterList(String[] list, String filter) {
		String[] ret = new String[list.length]; // creating the returned filtered list

		for (int i = 0; i < list.length; ++i) {
			String entry = list[i]; // checking each entry to see if it does not contain the filter
			if (filter != null && !entry.toLowerCase().contains(filter.toLowerCase())) // if filter is not equal to null
																						// and is not case sensitive
			{
				entry = ""; // entry will be empty
			}
			ret[i] = entry; // setting the value in return filtered list
		}

		return ret; // return the ret
	}

//Identifiers for the states in the login process
	private enum IntroState {
		FRESH, INVALID_PASS, MANAGER, CUST;

		/**
		 * Method returning boolean if manager is selected or customer
		 */
		public boolean isSelected() {
			return this == MANAGER || this == CUST; // return true if manager or customer
		}
	}
}

package assignment1.video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import assignment1.customer.Customer;
import assignment1.linkedlist.CLinkedList;

/**
 * Class to manage the rentals and returns
 */
public class RentalManager {
	// data members
	private static RentalManager INSTANCE = new RentalManager();
	private CLinkedList<Video> loadedVideos = new CLinkedList<Video>();
	private CLinkedList<Customer> loadedCustomers = new CLinkedList<Customer>();
	private CLinkedList<Rental> loadedRentals = new CLinkedList<Rental>();
	private Object[] dropDownIDs;
	private String[] vidListIDs;

	// constructor
	private RentalManager() {
	}

	/**
	 * Get the instance of the class
	 */
	public static RentalManager getInstance() {
		return INSTANCE; // return the instance of the class
	}

	/**
	 * Method to get the loaded videos list
	 */
	public CLinkedList<Video> getLoadedVideos() {
		return loadedVideos; // return the list of loaded videos
	}

	/**
	 * Method to return the loaded customers
	 */
	public CLinkedList<Customer> getLoadedCustomers() {
		return loadedCustomers; // return the list of loaded customers
	}

	/**
	 * Method to check if the data was loaded and check if it was loaded succesfully
	 */
	public boolean initializeData() {
		if (loadVideos()) // if videos loaded is true
		{
			if (loadCustomers()) // if loaded customers is true
			{
				if (loadRentals()) // if loaded rentals is true
				{
					return true; // return true
				}
			}
		}

		return false; // return false
	}

	/**
	 * Method to check if the videos loaded successfully
	 */
	private boolean loadVideos() {
		File vidFile = new File("Videos.dat"); // storing the video file in a variable

		if (vidFile.exists()) // if the file exists
		{
			try {
				Scanner scan = new Scanner(vidFile); // reading the video file

				while (scan.hasNext()) // while the file has next
				{
					String line = scan.nextLine(); // read each line
					String vidName = line.substring(0, line.lastIndexOf(" ")); // store the name of the video
					int vidID = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1, line.length()));
					loadedVideos.add(new Video(vidID, vidName)); // store the id of the video
				}

				scan.close(); // close scanner

				vidListIDs = new String[loadedVideos.size()]; // initialize the list of videos for GUI

				for (int i = 0; i < vidListIDs.length; ++i) {
					vidListIDs[i] = loadedVideos.get(i).getVidListIdentifier(); // getting each video's identifier for
																				// the GUI
				}

			} catch (FileNotFoundException e) {
				return false; // return false if exception was caught
			}

			return true; // return true if loads
		} else {
			return false; // return false if it fails to load
		}
	}

	/**
	 * Method to check if the customers loaded successfully
	 */
	private boolean loadCustomers() {
		File custFile = new File("Customer.dat"); // storing the customers in a variable

		if (custFile.exists()) // if the file exists
		{
			try {
				Scanner scan = new Scanner(custFile); // read customer file
				while (scan.hasNext()) // while the file has next
				{
					String line = scan.nextLine(); // read each line
					StringTokenizer st = new StringTokenizer(line); // making new string tokenizer to split up words in
																	// lines
					String customerName = st.nextToken() + " " + st.nextToken(); // storing customer name
					int custID = Integer.parseInt(st.nextToken()); // storing customer id
					loadedCustomers.add(new Customer(customerName, custID)); // adding customer name and custoemr id in
																				// the list
				}
				scan.close(); // close scanner

			} catch (FileNotFoundException e) {
				return false; // return false
			}

			dropDownIDs = new Object[loadedCustomers.size()]; // initialize the list of customers for GUI

			for (int i = 0; i < dropDownIDs.length; ++i) {
				dropDownIDs[i] = loadedCustomers.get(i).getDropDownIdentifier(); // getting each customer's identifier
																					// for the GUI
			}

			return true; // return true if the customers loaded
		} else {
			return false; // return false if they did not
		}
	}

	/**
	 * Method to check if the rentals loadded successfully
	 */
	private boolean loadRentals() {
		File rentFile = new File("Rentals.dat"); // storing the rentals file in a variable

		if (rentFile.exists()) // while the file exists
		{
			try {
				Scanner scan = new Scanner(rentFile); // read the file
				Customer CBID = null; // initializing CBID to null
				while (scan.hasNext()) // while file has next
				{
					String line = scan.nextLine(); // read next line
					StringTokenizer st = new StringTokenizer(line); // creating a string tokenzier object
					int custID = Integer.parseInt(st.nextToken()); // parsing the customer id from the line of strings

					if (CBID == null || CBID.getCustomerID() != custID) // if CBID is null or customer id is not equal
																		// to custID
					{
						CBID = getCustomerByID(custID); // gets customer by loaded id
					}

					int vidID = Integer.parseInt(st.nextToken()); // getting the video id
					Video VBID = getVideoByID(vidID); // initializing VBID to get the video number by id
					CBID.rentVideo(VBID); // customer rents video
					this.loadedRentals.add(new Rental(CBID, VBID)); // add the rentals to list
				}
				scan.close(); // close scanner
			} catch (FileNotFoundException e) {
				return false; // return false
			}

			return true; // return true if successful
		} else {
			return false; // return false if not
		}
	}

	/**
	 * Method to save the rentals in the file
	 */
	public void saveRentals() {
		try {
			PrintWriter pr = new PrintWriter(new File("Rentals.dat")); // writing in the rentals file

			for (int i = 0; i < loadedRentals.size(); ++i) {
				Rental r = loadedRentals.get(i); // getting the rentals and storing in r
				pr.write(r.cust.getCustomerID() + " " + r.vid.getVideoNumber() + "\n"); // writing in the rental list
			}

			pr.flush(); // executing the write
			pr.close(); // close printwriter
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to get video by the video id
	 */
	public Video getVideoByID(int vID) {
		for (int i = 0; i < loadedVideos.size(); i++) {
			if (loadedVideos.get(i).getVideoNumber() == vID) // if video number is equal to video ID
			{
				return loadedVideos.get(i); // return the loaded videos from the list
			}
		}
		return null; // return null if there is not any
	}

	/**
	 * Method to get customer by the customer id
	 */
	public Customer getCustomerByID(int cNumber) {
		for (int i = 0; i < loadedCustomers.size(); i++) {
			if (loadedCustomers.get(i).getCustomerID() == cNumber) // if customer id is equal to customer number
			{
				return loadedCustomers.get(i); // return the loaded customers from the list
			}
		}
		return null; // return null if there is not any
	}

	/**
	 * Method to get the customers by the their id for the drop down menu
	 */
	public Customer getCustomerByDropDownID(String id) {
		for (int i = 0; i < loadedCustomers.size(); i++) {
			if (loadedCustomers.get(i).getDropDownIdentifier().equals(id)) // if the drop down id is equal to id
			{
				return loadedCustomers.get(i); // return the loaded drop down ids from the list
			}
		}

		return null; // return null if there is not any
	}

	/**
	 * Method to get drop down ids
	 */
	public Object[] getDropDownIDs() {
		return dropDownIDs; // returning the drop down ids
	}

	/**
	 * Method to get drop down ids for GUI
	 */
	public String[] getDropDownIds() {
		String[] ret = new String[dropDownIDs.length]; // initializing the array and its size
		System.arraycopy(dropDownIDs, 0, ret, 0, dropDownIDs.length); // casting the object array as string array
		return ret; // return array
	}

	/**
	 * Method to get video list ids
	 */
	public String[] getVidListIDs() {
		return vidListIDs; // return the ids from the video list
	}

	/**
	 * Method to rent videos
	 */
	public void rentVideo(Customer cust, Video vid) {
		cust.rentVideo(vid); // customer side renting video
		vid.setCurrentRenter(cust); // video side associating itself with the specific customer who rented the video
		this.loadedRentals.add(new Rental(cust, vid)); // adding the customers and videos to the list
	}

	/**
	 * Method to return videos
	 */
	public void returnVideo(Video vid) {
		for (int i = this.loadedRentals.size() - 1; i >= 0; --i) {
			Rental r = this.loadedRentals.get(i); // referencing each rental to see if the video is in the current
													// rental
			if (r.vid.getVideoNumber() == vid.getVideoNumber()) // if rental video number is equal to current video
																// number
			{
				r.cust.returnVideo(vid.getVideoNumber()); // video is being returned and notifying the customers
				this.loadedRentals.remove(i); // remove the video
				break;
			}
		}
	}

	/**
	 * Method to get rental history
	 */
	public CLinkedList<Customer> getRentalHistory(Video vid) {
		CLinkedList<Customer> ret = new CLinkedList<Customer>(); // creating a new list

		for (int i = 0; i < this.loadedRentals.size(); ++i) {
			Rental r = this.loadedRentals.get(i); // referencing each rental to see if the video is in the current
													// rental
			if (r.vid.getVideoNumber() == vid.getVideoNumber()) // if rental video number is equal to current video
																// number
			{
				ret.add(r.cust); // adding it to the new list
			}
		}

		return ret; // return the list
	}

	/**
	 * Class to hold information about customers and videos for each individual
	 * rentals for rental manager
	 */
	private class Rental {
		// data members
		Customer cust;
		Video vid;

		// constructor
		private Rental(Customer c, Video v) {
			cust = c;
			vid = v;
		}
	}
}
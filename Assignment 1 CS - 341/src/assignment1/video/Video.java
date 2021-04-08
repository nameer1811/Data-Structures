package assignment1.video;

import assignment1.Main;
import assignment1.customer.Customer;
import assignment1.linkedlist.CLinkedList;

/**
 * Class to hold information of the videos
 */
public class Video {
	// data members
	private String videoTitle;
	private Customer currentRenter;
	private int videoNumber, timesRented;

	// constructor
	public Video(int id, String title) {
		videoNumber = id;
		videoTitle = title;
	}

	/**
	 * Method to get current renter
	 */
	public Customer getCurrentRenter() {
		return currentRenter; // return the current renter
	}

	/**
	 * Method to set current renter
	 */
	public void setCurrentRenter(Customer currentRenter) {
		if (currentRenter != null) // if current renter is not equal to null
		{
			++timesRented; // increment times rented
		}

		this.currentRenter = currentRenter; // set current renter to the current renter
	}

	/**
	 * Method to get title of the videos
	 */
	public String getTitle() {
		return videoTitle; // return the title of the video
	}

	/**
	 * Method to get video number
	 */
	public int getVideoNumber() {
		return videoNumber; // return the id of the video
	}

	/**
	 * Method to get the amount of time video was rented
	 */
	public int getTimesRented() {
		return timesRented; // return the number of times the video was rented
	}

	/**
	 * Method to make the object to string
	 */
	public String toString() {
		return "[ TimesRented:" + getTimesRented() + ",VideoNumber:" + getVideoNumber() + ",VideoTitle:\"" + getTitle()
				+ "\"]"; // returning the whole string of the video object
	}

	/**
	 * Method to get the video title and video id
	 */
	public String getVidListIdentifier() {
		return this.getTitle() + " - " + this.getVideoNumber(); // returning the title of the video and id
	}

	/**
	 * Method to get information for the GUI
	 */
	public String getInfoForGUI() {
		if (Main.isManager()) // if it is in manager side
		{
			String ret = getTitle() + "\nVideo Number: " + getVideoNumber() + "\nTimes Rented: " + getTimesRented()
					+ "\nCurrently Rented : "
					+ (this.getCurrentRenter() != null ? getCurrentRenter().getName() : "Not Rented")
					+ "\nRental History:\n"; // string with video information, if the current renter is not equal to
												// null get name of the current renter or return "Not Rented"
			CLinkedList<Customer> hist = RentalManager.getInstance().getRentalHistory(this); // getting the rental
																								// history
			for (int i = 0; i < hist.size(); ++i) {
				Customer cust = hist.get(i); // set the hist to cust
				ret += "\t  " + cust.getName() + "\n"; // adding to ret
			}

			return ret; // returning ret
		} else {
			return getTitle() + "\nVideo Number: " + getVideoNumber() + "\nCurrently Rented - "
					+ (this.getCurrentRenter() != null ? "Yes" : "No"); // return the information of the video, if
																		// current renter is not equal to null then
																		// return "Yes" or else return "No"
		}
	}
}

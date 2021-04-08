package assignment1.customer;

import assignment1.linkedlist.CLinkedList;
import assignment1.video.Video;

/**
 * Class that hold information about customers
 */
public class Customer {
	// Data members
	private String name;
	private int customerID, totalRentals;
	private CLinkedList<Video> rentedVideos = new CLinkedList<Video>();

	// constructor
	public Customer(String name, int id) {
		this.name = name;
		customerID = id;
	}

	/**
	 * Method to rent videos
	 */
	public void rentVideo(Video video) {
		if (video.getCurrentRenter() != this) // if current renter is not equal to the current object
		{
			if (video.getCurrentRenter() != null) // if current renter is not equal to null
			{
				video.getCurrentRenter().returnVideo(video.getVideoNumber()); // return the video from current person
																				// renting the video and se ti tto the
																				// current object
			}

			rentedVideos.add(video); // add the video to the list
			video.setCurrentRenter(this); // setting the current renter to the current object
			++totalRentals; // increment total rentals
		}
	}

	/**
	 * Method to return videos
	 */
	public void returnVideo(int videoNumber) {
		for (int i = 0; i < rentedVideos.size(); ++i) {
			Video video = rentedVideos.get(i); // get the rented videos and put it in video

			if (video != null && video.getVideoNumber() == videoNumber) // if video is not null and video number is
																		// equal to the current number
			{
				rentedVideos.remove(i); // remove the rented video
				video.setCurrentRenter(null); // set the current to null
				break;
			}
		}
	}

	/**
	 * Method to get name
	 */
	public String getName() {
		return name; // returns name
	}

	/**
	 * Method to get Customer id
	 */
	public int getCustomerID() {
		return customerID; // returns customer id
	}

	/**
	 * Method to get the list of videos
	 */
	public CLinkedList<Video> getListOfVideos() {
		return rentedVideos; // return the list of rented videos
	}

	/**
	 * Method to get customer name and customer id
	 */
	public String getDropDownIdentifier() {
		return getName() + " - " + getCustomerID(); // returning customer name and id
	}

	/**
	 * Method to return customer information in a string
	 */
	public String toString() {
		return "[ Name:\"" + getName() + "\",CurrentRentals:" + getListOfVideos().size() + ",CustomerNumber:"
				+ getCustomerID() + "]"; // returning the customer information
	}

	/**
	 * Method to get rented video id list
	 */
	public String[] getRentedVidIDList() {

		String[] ret = new String[this.rentedVideos.size()]; // making a string array and putting the size as the size
																// of the rented video list

		for (int i = 0; i < ret.length; ++i) {
			String vidID = this.rentedVideos.get(i).getVidListIdentifier(); // getting the information from video and
																			// setting it to vidID
			ret[i] = vidID; // storing vidID into array form
		}

		return ret; // returning ret
	}

	/**
	 * Method to get information for GUI
	 */
	public String getInfoForGUI() {

		String ret = name + "\nCustomer ID: " + customerID + "\nTimes Rented: " + totalRentals
				+ "\nCurrently Rented : \n"; // making a string variable to store info
		for (int i = 0; i < this.rentedVideos.size(); ++i) {
			Video vid = this.rentedVideos.get(i); // getting rented videos and storing in vid
			ret += "\t       " + vid.getTitle() + "\n"; // adding video titles to ret
		}
		return ret; // returning the ret

	}
}

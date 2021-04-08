package myLLimplementation;

/**
 * authored by: Sheikh Fahad This class will insert/remove item at the start/end
 * of the items list
 *
 */
public class LL {

//	start, end, and other info + operations

	int numberofitemsinthecollection, totalSum, maximum, minimum;

	node start, end;

	public LL() {
		numberofitemsinthecollection = 0;
		start = null;
		end = null;
		minimum = Integer.MAX_VALUE; // setting the max value to minimum
		maximum = Integer.MIN_VALUE; // setting the min value to maximum

	}

	public void insertatstart(item i) {
		node newnode = new node(i);
		numberofitemsinthecollection++;

		totalSum = totalSum + i.value; // adding the total value every time a value is added

		// if minimum is greater than the value added to the list, set minimum to the
		// value
		if (minimum > i.value) {
			minimum = i.value;
		}

		// if maximum is less than the value added to the list, set maximum to the value
		if (maximum < i.value) {
			maximum = i.value;
		}

		if (start == null) {
			start = newnode;
			end = newnode;
		} else {
			newnode.next = start;
			start = newnode;
		}

	}

	public void insertatend(item i) {
		node newnode = new node(i);
		numberofitemsinthecollection++;
		totalSum = totalSum + i.value; // if maximum is less than the value added to the list, set maximum to the value

		// if minimum is greater than the value added to the list, set minimum to the
		// value
		if (minimum > i.value) {
			minimum = i.value;
		}
		// if maximum is less than the value added to the list, set maximum to the value
		if (maximum < i.value) {
			maximum = i.value;
		}

		if (end == null) {
			start = newnode;
			end = newnode;
		} else {
			end.next = newnode;
			end = newnode;
		}
	}

	public item removefromstart() {

		// if the start is not null
		if (start != null) {
			item remove = start.data; // get remove the start data
			// if start is the end
			if (start == end) {
				start = end = null; // set both to null since we already returned it
			} else {
				start = start.next; // set start to the next one
			}
			totalSum = totalSum - remove.value; // subtract the removed value from total

			// if minimum is equal to the removed value
			if (minimum == remove.value) {
				minimum = Integer.MAX_VALUE; // setting minimum to max value again
				node current = start;
				// while current is not null
				while (current != null) {
					// if current data's value is less than minimum
					if (current.data.value < minimum) {
						minimum = current.data.value; // set minimum to current data's value
					}
					current = current.next; // set current to the next object
				}

			}
			// if maximum is equal to the removed value
			if (maximum == remove.value) {
				maximum = Integer.MIN_VALUE; // setting maximum to min value again
				node current = start;
				// while current is not null
				while (current != null) {
					// if current data's value is greater than maximum
					if (current.data.value > maximum) {
						maximum = current.data.value; // set maximum to current data's value
					}
					current = current.next; // set current to the next object
				}
			}
			return remove; // return the remove
		} else {
			return null; // return null if there is nothing
		}

	}

	public item removefromend() {
		// if end is not null
		if (end != null) {
			item remove = end.data; // set remove to end data
			 // set current to start
			// if start is the end
			if (start == end) {
				start = end = null; // set both to null since we already returned it
			} else {
				node current = start;
				// while the current is not null
				while (current != null) {
					// if the next of the current's next is null
					if (current.next.next == null) {
						current.next = null; // set the next to the current to null
						end = current; // make end the current
					}
					current = current.next; // set current to the next of current
				}

			}
			totalSum = totalSum - remove.value; // subtract the removed value from total

			// if minimum is equal to the removed value
			if (minimum == remove.value) {
				minimum = Integer.MAX_VALUE; // setting minimum to max value again
				node current = start;
				// while current is not null
				while (current != null) {
					// if current data's value is less than minimum
					if (current.data.value < minimum) {
						minimum = current.data.value; // set minimum to current data's value
					}
					current = current.next; // set current to the next object
				}

			}
			// if maximum is equal to the removed value
			if (maximum == remove.value) {
				maximum = Integer.MIN_VALUE; // setting maximum to min value again
				node current = start;
				// while current is not null
				while (current != null) {
					// if current data's value is greater than maximum
					if (current.data.value > maximum) {
						maximum = current.data.value; // set maximum to current data's value
					}
					current = current.next; // set current to the next object
				}
			}
			return remove; // return remove
		}
		return null; // return null
	}

	public void insert(item i) {
		numberofitemsinthecollection++;

	}

	public boolean isempty() {
		if (numberofitemsinthecollection == 0)
			return true;
		else
			return false;
	}

	public int sum() {
		return totalSum; //returns the total sum
	}
	
	public int difference()
	{
		return maximum-minimum; //gives us the difference between maximum and minimum value
	}
	
	public void rotate ()
	{
		insertatstart(removefromend()); //inserts the last element removed from the list to the start
	}

}

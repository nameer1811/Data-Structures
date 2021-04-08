package assignment1.linkedlist;

/**
 * Class that is our custom linked list
 */
public class CLinkedList<I> {
	// Data members
	private CElement<I> headElement;
	private CElement<I> tailElement;
	private int size = 0;
	private int lastIndex;
	private CElement<I> lastElement;

	/**
	 * Method to add elements in the linked list
	 */
	public void add(I value) {
		CElement<I> newEl = new CElement<I>(value); // putting new element in newEl

		if (headElement == null) // if head element is null
		{
			headElement = newEl; // add the newEl to head element
		} else {
			tailElement.nextElement = newEl; // set the next element to tail element to newEl
		}

		tailElement = newEl; // setting newEl to tail element
		++size; // increasing size
	}

	/**
	 * Method to get elements in the linked list
	 */
	public I get(int index) {
		if (lastIndex == index - 1) // if last index in the list is equal to the previous index
		{
			return getNext(); // get next
		} else {
			CElement<I> current = headElement; // set current to head element
			for (int i = 0; i < index + 1; ++i) {
				if (current == null) // if current is cull
				{
					break; // get out of the loop
				} else {
					if (i == index) // if i is equal to index
					{
						lastIndex = i; // last index will be i
						lastElement = current; // last element will be current
						return current.value; // return current value
					}

					current = current.nextElement; // current will be set to the next element to the current
				}
			}

			return null;
		}
	}

	/**
	 * Method to get next item in the list
	 */
	public I getNext() {
		lastElement = lastElement.nextElement; // set last element to the next element of last element
		if (lastElement != null) // if last element is not null
		{
			++lastIndex; // increase index
		}
		return lastElement == null ? null : lastElement.value; // return last element as null if it is equal to null or
																// else return the last element
	}

	/**
	 * Method to remove element from the list
	 */
	public I remove(int index) {
		// initialized data members
		CElement<I> current = headElement;
		CElement<I> previous = null;

		for (int i = 0; i < index + 1; ++i) {
			if (current == null) // if current is null
			{
				break; // get out of the loop
			} else {
				if (i == index) // if i is equal to index value
				{
					if (previous != null)// if previous is not equal to null
					{
						previous.nextElement = current.nextElement; // set current to the next element to previous to
																	// next element
					} else {
						this.headElement = current.nextElement; // set element next to current to head element
					}
					--size; // decrease size
					lastIndex = -2;
					return current.value; // return current value
				}

				previous = current; // set current to previous
				current = current.nextElement; // set current to next element
			}
		}

		return null;
	}

	/**
	 * Method to get size of the element
	 */
	public int size() {
		return size; // returning the size
	}

	/**
	 * Method to make the list to string
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer(); // making a new string buffer object
		ret.append("[ "); // appending to the buffer
		for (int i = 0; i < size; ++i) {
			I cur = this.get(i); // getting each element
			ret.append("[Ind:" + i + ",Val:" + cur.toString() + "]"); // appending to the buffer
		}
		ret.append(" ]"); // appending to the buffer
		return ret.toString(); // returning the buffered string
	}

	/**
	 * Class that hold value and link to the next element
	 */
	private class CElement<T> {
		// data members
		private T value;
		private CElement<T> nextElement;

		// constructor
		private CElement(T value) {
			this.value = value;
		}
	}
}

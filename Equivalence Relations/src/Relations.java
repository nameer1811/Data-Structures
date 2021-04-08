/**
 * Class: Relations
 * 
 * @author Fahad
 *
 */
public class Relations {

	// Data members
	String store;
	Relations element;

	// constructor
	public Relations(String st) {
		store = st;
		element = null;
	}

	/**
	 * Method: getElement
	 * 
	 * @return ... the element
	 */
	public Relations getElement() {
		return element;
	}

	/**
	 * Mehtod: setElement
	 * 
	 * @param element ... setting the element
	 */
	public void setElement(Relations element) {
		if(this.element != null)
		{
			this.element.setElement(element);
		}
		else
		{
			this.element = element;
		}

	}

	/**
	 * Method: equalElemenet
	 * 
	 * @param store ... compares the store
	 * @return ... true or false based on the conditions
	 */
	public boolean equalElement(String store) {
		// if store is equal to store
		if (this.store.equals(store)) {
			return true; // returns true
		} else {
			// if element is not equal to null
			if (element != null) {
				// if element is equal to the stored value
				if (element.equalElement(store)) {
					return true; // return true
				}
			}
		}

		return false; // return false
	}

	/**
	 * Method: toString
	 */
	public String toString() {
		String result = store; // storing the store in result

		// if element is not null
		if (element != null) {
			// recursively add the elements in result
			result = result + ", " + element.toString();
		}

		return result; // return results
	}
}


public class KeyElement {

	// Data Member
		private KeyElement next; // reference to the next ListElement in the list
		private int Value; // Reference to an Integer object
		private String Key;
		// Constructor
		public KeyElement(String key, int value) {
			Key=key;
			Value = value;
			next = null;
		}

		public boolean isKey (String key)
		{
			if (key.equals(Key))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		// Getter method on the data members of the object

		public KeyElement getNext() {
			return next;
		}

		public void setNext(KeyElement next) {
			this.next = next;
		}

		public int getValue() {
			return Value;
		}

		public void setValue(Integer value) {
			Value = value;
		}

		public String getKey() {
			return Key;
		}

		public void setKey(String key) {
			Key = key;
		}
	

	}
	

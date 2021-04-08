import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UniqueWords {

	private KeyElement first;
	private int size;

	public void Add(String key, int value) {
		//Data members
		KeyElement current = first;
		boolean keyFound = false; 
		
		while (current != null) //when the current is not null
		{
			if (current.getKey().equals(key)) // if current key is equal to the key
			{
				current.setValue(value); //the current value will be set to value
				keyFound = true; // keyfound will be true
			}
			current = current.getNext(); // current will go to next
		}
		//if keyfound is false
		if (keyFound == false) {
			KeyElement recent = new KeyElement(key, value); // recent will be added
			recent.setNext(first); // recent will be set to next
			first = recent; // making first the recent
			size++; // increasing size
		}
	}

	public String[] keyLookup() {
		
		//Data members
		String[] store = new String[size];
		KeyElement current = first;
		
		for (int i = 0; i < size; i++) {
			store[i] = current.getKey(); // storing the current key in the array
			current = current.getNext(); // setting current to next
		}
		return store; //returning the array
	}

	public int Find(String Key) {
		if (first == null) // if first is null
		{
			return 0; //return 0
		}
		KeyElement current = first; // initializing current to first
		while (true)
		{// starting while loop
			if (current.getKey().equals(Key)) // if current key is equal to key
			{
				return current.getValue(); // return the current value
			}
			if (current.getNext() == null) // if the next is null
			{
				break; // get out of the loop
			}
			current = current.getNext(); // make current equal the next one
		} //ending while loop
		return 0; //return 0
	}

	public static void main(String[] args) {

		String fileName = JOptionPane.showInputDialog("Input file name"); //file name
		try { //starting try block
			Scanner scan = new Scanner(new File(fileName)); // scanning in file
			UniqueWords UW = new UniqueWords(); //constructing the map of Unique words
			//Data Members
			int totalWords = 0;
			int value = 0;
			int counterOnce = 0;
			String once = "";
			int counterTwice = 0;
			String twice = "";
			int counterOdd = 0;
			String odd = "";
			int counterEven = 0;
			String even = "";

			while (scan.hasNext()) { // while start
				String word = scan.next().replaceAll("[^a-zA-Z ]", ""); //getting rid all the symbols
				value = UW.Find(word) + 1; //returning the current value and adding 1
				UW.Add(word, value); // adding the word and associating the value
				totalWords++; // incrementing totalWords
			} // while end
			String[] lookUps = UW.keyLookup(); //creating a string array for looking up the keys

			for (int i = 0; i < lookUps.length; i++) {
				String word = lookUps[i]; // putting current key in the the word
				int currentValue = UW.Find(word); //current value is the value associated with the key/word

				//if current value occured once, increment counter and concat the word
				if (currentValue == 1) {
					once += word + ", ";
					counterOnce++;
				}
				//if current value occured twice, increment the counter and concat the word
				if (currentValue == 2) {
					twice += word + ", ";
					counterTwice++;
				}
				//if current value occured odd number of times, increment the counter and concat the word
				if (currentValue % 2 == 1) {
					odd += word + ", ";
					counterOdd++;
				}
				//if current value occured even number of times, increment the counter and concat the word
				if (currentValue % 2 == 0) {
					even += word + ", ";
					counterEven++;
				}

			}

			//Print statements
			System.out.println("The total number of words are " + totalWords);
			System.out.println("(" + once.substring(0, once.length() - 2) + ") "
					+ "The number of times the word occured once was " + counterOnce);
			System.out.println("(" + twice.substring(0, twice.length() - 2) + ") "
					+ "The number of times the word occured twice was " + counterTwice);
			System.out.println("(" + odd.substring(0, odd.length() - 2) + ") "
					+ "Words that showed up odd number of times was " + counterOdd);
			System.out.println("(" + even.substring(0, even.length() - 2) + ") "
					+ "Words that showed up even number of times was " + counterEven);
			System.out.println("The number of unique words are " + UW.keyLookup().length);

		} //ending try block
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

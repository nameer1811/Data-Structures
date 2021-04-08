import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.w3c.dom.Node;

/**
 * Class: Main
 * 
 * @author Fahad
 *
 */
public class Main {

	public static void main(String[] args) {

		try {
			// scanner to scan the input file
			Scanner scan = new Scanner(new File("Data.dat"));
			// declaring and creating an array of size 4 which will store the parent sets
			Relations array[] = new Relations[4];
			int set = 0;
			// declaring and creating an array of size 32 which will store all the element
			Relations masterArray[] = new Relations[32];
			int masterSet = 0;

			// while file has next line
			while (scan.hasNext()) {
				// use string tokenizer to create tokens from the line
				StringTokenizer token = new StringTokenizer(scan.nextLine());
				// store the values in element 1 and element 2
				String element1 = token.nextToken();
				String element2 = token.nextToken();
				// declare a relations variable and set it to null
				Relations addElement1 = null;
				Relations addElement2 = null;

				// iterate through array
				for (int i = 0; i < masterArray.length; i++) {
					// if masterArray is not null
					if (masterArray[i] != null) {
						// if masterArray's value is equal to the element1's value
						if (masterArray[i].store.equals(element1)) {
							addElement1 = masterArray[i]; // add masterArray's value to addElement1
						}
						// if masterArray's value is equal to the element2's value
						if (masterArray[i].store.equals(element2)) {
							addElement2 = masterArray[i]; // add masterArray's value to addElement2
						}
					}
				}
				
				// if addElement2 is null
				if (addElement2 == null) {
					addElement2 = new Relations(element2); // create new relations and store element1's value in it
					masterArray[masterSet] = addElement2; // put the value in the index masterSet of the masterArray
					masterSet++; // increment masterSet
				}
				
				// if addElement1 is null
				if (addElement1 == null) {

					addElement1 = new Relations(element1); // create new relations and store element1's value in it
					masterArray[masterSet] = addElement1; // put the value in the index masterSet of the masterArray
					masterSet++; // increment masterSet

					array[set] = addElement1; // put addElement1's value in index set of array
					set++;
				}
				
				//iterate through array
				for(int i = 0; i < array.length; i++)
				{
					Relations rel = array[i]; //put the value of array in rel
					
					//if rel is not null and rel is equal element to addElement2
					if(rel != null && rel.equalElement(addElement2.store))
					{
						addElement2 = rel; //set element2 is rel
						break; //break out of loop
					}	
				}

				addElement1.setElement(addElement2); // set addElement1 to addElement2

			} // while ends

			// print out the elements in masterArray for the user to input
			System.out.print("The elements are: ");
			// iterate through masterArray
			for (int i = 0; i < masterArray.length; i++) {
				// if masterArray is null then break out of the loop
				if (masterArray[i] == null) {
					break;
				}

				// print all the elements from masterArray
				System.out.print(masterArray[i].store + " ");
			}

			scan.close(); // close scanner

			// going into infinite loop
			while (true) {
				// declaring and initializing a scanner object for user input
				Scanner input = new Scanner(System.in);

				// asking for user input
				System.out.print("\nPlease enter two elements: ");

				String userInput = input.nextLine();

				// using string tokenizer to read each input
				StringTokenizer token = new StringTokenizer(userInput);

				// checking if we have 2 inputs
				if (token.countTokens() == 2) {

					// declaring element 1 and element 2 for two inputs
					String element1 = token.nextToken();
					String element2 = token.nextToken();
					// declaring two variables as booleans to check for any element input that does
					// not exist
					boolean element1True = false;
					boolean element2True = false;

					// iterate through the array
					for (int i = 0; i < array.length; i++) {
						// if array is not null
						if (array[i] != null) {
							// if index i of array is equal to element1 and element1True is false after the first print
							if (array[i].equalElement(element1) && !element1True) {
								element1True = true; // set element1True to true
								// print out the output
								System.out
										.println("The element is \"" + element1 + "\"\nThe set is {" + array[i] + "}");
							}
							// if index i of array is equal to element2 and element2True is false after the first print
							if (array[i].equalElement(element2) && !element2True) {
								element2True = true; // set element2True to true
								// print out the output
								System.out
										.println("The element is \"" + element2 + "\"\nThe set is {" + array[i] + "}");
							}
						}
					}
					// if element1True is false
					if (element1True == false) {
						// print the element which does not exist
						System.out.println("The element \"" + element1 + "\" does not exist!");
					}
					// if element2True is false
					if (element2True == false) {
						// print the element which does not exist
						System.out.println("The element \"" + element2 + "\" does not exist!");
					}
					break; // break out of the loop

				} else // if 2 elements were not inputed
				{
					// print they did not enter 2 elements
					System.out.println("You did not enter 2 elements!");
					continue; // continue the loop
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class: Main
 * 
 * Description: Reads from the input file and does the graphs and depth first
 * traversal
 * 
 * @author Sheikh Fahad
 *
 */

public class Main {

	public static void main(String[] args) {

		// declaring and initializing a linked list of nodes
		LinkedList<Nodes> node = new LinkedList<Nodes>();
		try {
			// scanner to scan the input file
			Scanner scan = new Scanner(new File("Data.dat"));

			// while file has next line
			while (scan.hasNext()) {
				// use string tokenizer to create tokens from the line
				StringTokenizer token = new StringTokenizer(scan.nextLine());
				// store the values in element 1 and element 2
				String element1 = token.nextToken();
				String element2 = token.nextToken();
				int length = Integer.parseInt(token.nextToken());
				// declare a Nodes variable and set it to null
				Nodes addElement1 = null;
				Nodes addElement2 = null;

				// iterate through list
				for (int i = 0; i < node.size(); i++) {
					// if node's list value at i is not null
					if (node.get(i) != null) {
						// if node's list value i's ID is equal to the element1's value
						if (node.get(i).ID.equals(element1)) {
							addElement1 = node.get(i); // add node's list value of i to addElement1
						}
						// if node's list value i's ID is equal to the element2's value
						if (node.get(i).ID.equals(element2)) {
							addElement2 = node.get(i); // add node's list value of i to addElement2
						}
					}
				}

				// if addElement1 is null
				if (addElement1 == null) {

					addElement1 = new Nodes(element1); // create new Nodes and store element1's value in it
					node.add(addElement1); // add addElement1 to the node list
				}
				// if addElement2 is null
				if (addElement2 == null) {

					addElement2 = new Nodes(element2); // create new Nodes and store element2's value in it
					node.add(addElement2); // add addElement2 to the node list
				}

				addElement1.addConnections(addElement2, length); // make the connection between element1 and element 2
																	// and put the length

			} // while ends
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// using lambda function to sort the nodes from A to H
		node.sort((Nodes el1, Nodes el2) -> {
			return el1.ID.compareTo(el2.ID);
		});

		System.out.println("Printing nodes and its connection lengths with other nodes: \n");
		System.out.print("   ");

		// iterating through the nodes list
		for (int i = 0; i < node.size(); i++) {
			Nodes rows = node.get(i); // adding nodes values to rows
			System.out.print(rows.ID + " "); // printing rows out
		}

		System.out.println();

		System.out.print("  ----------------");

		System.out.println();

		// iterating through the nodes list
		for (int i = 0; i < node.size(); i++) {
			Nodes rows = node.get(i); // adding nodes values to rows

			System.out.print(rows.ID + "| "); // printing the rows out
			// iterating again for the columns
			for (int j = 0; j < node.size(); j++) {
				Nodes columns = node.get(j); // adding nodes values to columns

				System.out.print(rows.returnLength(columns) + " "); // printing the lengths of columns out for the table
			}
			System.out.println();
		}

		System.out.println("\nDepth First Traversal is: ");

		// printing depth first traversal
		System.out.println(node.get(0).DFT());

	}// main ends
}

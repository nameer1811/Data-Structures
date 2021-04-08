import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class: Main
 * 
 * Description: Has a minimum spanning tree function and performs it
 * 
 * @author Sheikh Fahad
 *
 */
public class Main {

	static LinkedList<ListNodes> list = new LinkedList<ListNodes>();
	// create a static linked list
	static LinkedList<Edges> connections = new LinkedList<Edges>();

	/**
	 * Method: minimumSpanningTree
	 * 
	 * Description: does a minimum spanning tree of a inputed graph\
	 * 
	 * @return ... returns the linked list of edges
	 */
	public static LinkedList<Edges> minimumSpanningTree() {
		// creating two linked list of edges and nodes to store the connections and
		// nodes
		LinkedList<Edges> connectionsMST = new LinkedList<Edges>();
		LinkedList<Nodes> nodesMST = new LinkedList<Nodes>();

		// using a lamda function to sort the edges
		connections.sort((Edges length1, Edges length2) -> {

			// if length1's length is greater than length2's length
			if (length1.length > length2.length) {
				return 1; // descending order
				// if length1's length is less than length2's length
			} else if (length1.length < length2.length) {
				return -1; // ascending order
			} else {
				return 0; // stay the same order
			}
		});

		// iterate through the list
		for (int i = 0; i < connections.size(); i++) {

			Edges edgeConnections = connections.get(i); // add the connections to a new variable inside the loop

			// create new boolean variable and set it to true
			boolean addToMST = true;

			// if node 1's and node 2's index is less than 0
			if (edgeConnections.node1.index < 0 && edgeConnections.node2.index < 0) {
				int count = list.size(); // make a variable with the size of the list
				ListNodes listNodes = new ListNodes(edgeConnections.node1, count); // create a variable of list nodes
																					// and construct node 1 and list
																					// size
				listNodes.addToRelations(edgeConnections.node2); // add node2 to the relations
				list.add(listNodes); // add listNodes to the the list
			}
			// else if node 1's and node 2's index is greater than -1
			else if (edgeConnections.node1.index > -1 && edgeConnections.node2.index > -1) {
				// if node 1's index equal to node 2's index
				if (edgeConnections.node1.index == edgeConnections.node2.index) {
					continue; // keep on continuing
				} else {
					// make a new variable of list nodes and get node 1's index
					ListNodes LN = list.get(edgeConnections.node1.index);
					LN.addToRelations(edgeConnections.node2); // add the relations of node 2 to list nodes
				}
			} else {
				// if node 1's index is greater than -1
				if (edgeConnections.node1.index > -1) {
					// make a new variable of list nodes and get node 1's index
					ListNodes LN = list.get(edgeConnections.node1.index);
					LN.addToRelations(edgeConnections.node2); // add the relations of node 2 to list nodes
				} else {
					// make a new variable of list nodes and get node 2's index
					ListNodes LN = list.get(edgeConnections.node2.index);
					LN.addToRelations(edgeConnections.node1); // add the relations of node 1 to list nodes
				}
			}

			// if the boolean is true
			if (addToMST == true) {
				connectionsMST.add(edgeConnections); // add the edgeConnections to connectionsMST
				// add both the nodes of those connections to the nodeMST list
				nodesMST.add(edgeConnections.node1);
				nodesMST.add(edgeConnections.node2);
			}

		} // first for loop ends

		return connectionsMST; // return the connectionMST list

	} // end method

	public static void main(String[] args) {
		LinkedList<Nodes> node = new LinkedList<Nodes>(); // creating a new linked list of nodes
		try {
			// scanner to scan the input file
			Scanner scan = new Scanner(new File("Graph.dat"));

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

				// iterate through array
				for (int i = 0; i < node.size(); i++) {
					// if masterArray is not null
					if (node.get(i) != null) {
						// if masterArray's value is equal to the element1's value
						if (node.get(i).ID.equals(element1)) {
							addElement1 = node.get(i); // add masterArray's value to addElement1
						}
						// if masterArray's value is equal to the element2's value
						if (node.get(i).ID.equals(element2)) {
							addElement2 = node.get(i); // add masterArray's value to addElement2
						}
					}
				}

				// if addElement1 is null
				if (addElement1 == null) {

					addElement1 = new Nodes(element1); // create new Nodes and store element1's value in it
					node.add(addElement1);
				}
				// if addElement1 is null
				if (addElement2 == null) {

					addElement2 = new Nodes(element2); // create new Nodes and store element1's value in it
					node.add(addElement2);
				}

				addElement1.addConnections(addElement2, length); // adding connections of addElement2 and length to
																	// addElement1

			} // while ends
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("The minimum spanning tree for the input graph is: ");

		LinkedList<Edges> MST = minimumSpanningTree(); // put the method in a list of edges

		// iterate through that list
		for (int i = 0; i < MST.size(); i++) {
			System.out.println(MST.get(i)); // print out the i value of the list
		}

	}

}
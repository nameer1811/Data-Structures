import java.util.LinkedList;

/**
 * Class: Nodes
 * 
 * Description: Performs all the functions of nodes
 * 
 * @author Sheikh Fahad
 *
 */

public class Nodes {

	String ID; // data member
	LinkedList<Edges> connections = new LinkedList<Edges>(); // creating a new LinkedList of edges

	// constructor
	public Nodes(String ID) {
		this.ID = ID;
	}

	/**
	 * Method: addConnections 
	 * Description: adds the connections between nodes and
	 * sets the length
	 * 
	 * @param node   ... the node that will be connected to
	 * @param length ... the length between the two nodes
	 */
	public void addConnections(Nodes node, int length) {
		// if node is not null
		if (node != null) {
			Edges line = new Edges(this, node, length); // create a edges variable line which will point to the seocnd
														// node and set length

			connections.add(line); // add the line to the connections linked list

			Edges line2 = new Edges(node, this, length); // create another edges varable line2 which will point to the
															// first node and set length

			node.connections.add(line2); // point both the nodes at each other to make undirected graph
		}
	}

	/**
	 * Method: toString 
	 * Description: print out the connections linked list to
	 * strings
	 */

	public String toString() {
		String result = ID + "\n";

		// iterate through the list
		for (int i = 0; i < connections.size(); i++) {
			result += "\t" + connections.get(i) + "\n"; // add list values to the results
		}

		return result; // return the result
	}

	/**
	 * Method: returnLength 
	 * Description: returns length between two nodes
	 * 
	 * @param node ... node to be put in to return the length of
	 * @return ... return the length between two nodes or return 0 if no connections
	 */
	public int returnLength(Nodes node) {
		// iterate through the list
		for (int i = 0; i < connections.size(); i++) {
			// if node equals to the value of connections node 2 then return the length
			if (node == connections.get(i).node2) {
				return connections.get(i).length;
			}
		}
		return 0; // else return zero
	}

	/**
	 * Method: addSearchRepeat 
	 * Description: returns list of node
	 * 
	 * @param node ... list of nodes added
	 * @return ... the node list
	 */
	private LinkedList<Nodes> addSearchRepeat(LinkedList<Nodes> node) {
		// add the current node
		node.add(this);

		// iterate through the connections list
		for (int i = 0; i < connections.size(); i++) {
			// if node does not contain connections of node2 with currently looked at node
			if (!node.contains(connections.get(i).node2)) {
				connections.get(i).node2.addSearchRepeat(node); // recursively add the nodes to the list
			}
		}

		return node; // return the list of node
	}

	/**
	 * Method: DFT 
	 * Description: performs depth first traversal
	 * 
	 * @return ... return the string after depth first traversal is done
	 */
	public String DFT() {
		String result = ""; // initializing and creating a variable of empty strings
		LinkedList<Nodes> depthFirst = new LinkedList<>(); // creating a new linked list of nodes

		addSearchRepeat(depthFirst); // use the list on method addSearchRepeat

		// iterate through the depthFirst list
		for (int i = 0; i < depthFirst.size(); i++) {
			result += depthFirst.get(i).ID + " > "; // add the values of depthFirst to result
		}

		return result.substring(0, result.length() - 2); // return the result except the '>' at the end
	}
}

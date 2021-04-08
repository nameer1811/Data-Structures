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
	// data member
	String ID;
	int index = -1;

	LinkedList<Edges> connections = new LinkedList<Edges>(); // creating a new linked list of edges

	// constructor
	public Nodes(String ID) {
		this.ID = ID;
	}

	/**
	 * Method: addConnections Description: adds the connections between nodes and
	 * sets the length
	 * 
	 * @param node   ... the node that will be connected to
	 * @param length ... the length between the two nodes
	 */
	public void addConnections(Nodes node, int length) {
		// if node is not null
		if (node != null) {
			Edges line = new Edges(this, node, length); // add a new edge in a variable called line with the node and
														// the node it's connected to and the length

			connections.add(line); //add the line to the list
			Main.connections.add(line); //add the line to the main's connections list
		}
	}

	/**
	 * Method: toString 
	 * 
	 * Description: print out the connections linked list to
	 * strings
	 */
	public String toString() {
		String result = ID + "\n";

		//iterate through the list
		for (int i = 0; i < connections.size(); i++) {
			
			result += "\t" + connections.get(i) + "\n"; //add the values of connections list to result
		}

		return result; //return result
	}

}

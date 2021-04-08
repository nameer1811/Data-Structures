/**
 * Class: Edges
 * 
 * Description: Stores the value of nodes and its lengths
 * 
 * @author Sheikh Fahad
 *
 */
public class Edges {
	// data members
	Nodes node1;
	Nodes node2;
	int length;

	// constructor
	public Edges(Nodes end1, Nodes end2, int length) {
		node1 = end1;
		node2 = end2;
		this.length = length;
	}

	/**
	 * Method: toString
	 * 
	 * Description: Prints out nodes in strings
	 */
	public String toString() {
		return node1.ID + " " + node2.ID + " " + length;
	}

}

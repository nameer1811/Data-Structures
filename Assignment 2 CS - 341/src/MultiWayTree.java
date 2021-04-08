/**
 * Class: MultiWayTree
 * 
 * @author Sheikh Fahad and Ashton Schultz
 *
 */
public class MultiWayTree {

	// data members
	Node root;
	int maxChild;

	// constructor
	public MultiWayTree(int m) {
		root = null;
		maxChild = m;
	}

	/**
	 * Method: isEmpty
	 * 
	 * @return ... true if root is null else returns false
	 */
	public boolean isEmpty() {
		// if root is null return true
		if (root == null) {
			return true;
		} else {
			return false; // else return false
		}
	}

	/**
	 * Method: insert
	 * 
	 * @param value ... the value inside the node
	 * @param child ... inserts child nodes at the node the value was found
	 */

	public void insert(int value, int child) {
		// if root is not null
		if (root != null) {
			if (root.find(value) != null) {
				root.find(value).insert(child); // find the value and insert a child
			}
		} else {
			root = new Node(child, maxChild); // if root is null, create a new node
		}
	}

	/**
	 * Method: find
	 * 
	 * @param value ... finds the value inside a node
	 * @return ... the node of the value
	 */

	public Node find(int value) {
		// if node is not null
		if (root != null) {
			return root.find(value); // return the node of the value
		} else {
			return null; // if it is null return null
		}
	}

	/**
	 * Method: height
	 * 
	 * @return ... height of the tree
	 */
	public int height() {
		// if root is not null
		if (root != null) {
			return root.height() - 1; // return the height of the node recursively
		} else
			return -1; // if it is null then return -1
	}

	/**
	 * Method: preOrder
	 * 
	 * @return ... the tree in pre order
	 */
	public String preOrder() {
		// if root is null return empty
		if (root == null) {
			return "Empty";
		} else {
			return root.toStringPreOrder(); // else return the pre order of the tree
		}
	}

	/**
	 * Method: postOrder
	 * 
	 * @return ... the tree in post order
	 */
	public String postOrder() {
		// if root is null, return empty
		if (root == null) {
			return "Empty";
		} else {
			return root.toStringPostOrder(); // else return the tree in post order
		}
	}

	/**
	 * Method: levelOrder
	 * 
	 * @return ... the tree in level order
	 */

	public String levelOrder() {
		// if the root is null, return the empty
		if (root == null) {
			return "Empty";
		} else {
			return root.toStringLevelOrder(); // else return the tree in level order
		}
	}

	/**
	 * Method: writeInFile
	 * 
	 * @return ... the string of the write
	 */
	public String writeInFile() {
		// if root is null then return empty string
		if (root == null) {
			return " ";
		} else {
			String returnString = maxChild + "\n" + root.data + "\n"; // store maxChild and root data in returnString
			returnString += root.write(); // recursively add the root in the returnString
			return returnString.substring(0, returnString.length() - 2); // return the returnString
		}
	}
}

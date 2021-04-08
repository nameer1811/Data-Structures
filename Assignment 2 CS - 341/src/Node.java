import java.util.LinkedList;

/**
 * Class: Node
 * 
 * @author Sheikh Fahad and Ashton Schultz
 *
 */
public class Node {

	//data members
	int data;
	boolean isLeaf = true;
	Node[] childNodes;

	//constructor
	public Node(int data, int m) {
		this.data = data;
		childNodes = new Node[m];
	}

	/**
	 * Method: find
	 * @param value ... find the node
	 * @return ... return the node
	 */
	public Node find(int value) {
		//if data is value
		if (data == value) {
			return this; //return the node
		}

		else {
			//making a null node
			Node at = null;
			//iterate through the child nodes
			for (int i = 0; i < childNodes.length; i++) {
				
				Node current = childNodes[i]; //setting current to child nodes

				//if current is null then continue
				if (current == null) {
					continue;
				} else {
					//if current node's data is value
					if (current.data == value) {
						return current; //return current
					// else if node at is null
					} else if (at == null) {
						at = current.find(value); //find the value in node and set that node to at 
					} else {
						break; //else break out of loop
					}
				}
			}
			return at; //return at

		}
	}

	/**
	 * Method: insert
	 * 
	 * @param value ... the input
	 * @return ... the value of the insert
	 */
	public int insert(int value) {

		//iterate through the child nodes
		for (int i = 0; i < childNodes.length; i++) {
			Node current = childNodes[i]; //set current to child nodes

			//if current is equal to null and child nodes is new node which should have value and the length of child nodes
			if (current == null) {
				childNodes[i] = new Node(value, childNodes.length);
				isLeaf = false; //set isLeaf to false
				return value; //return value
			} else {
				continue; //else continue
			}
		}
		return 0; //return 0
	}

	/**
	 * Method: height
	 * @return ... maxHeight + 1
	 */
	public int height() {
		int maxHeight = 0; //declare and initialize a variable
		//if true
		if (isLeaf) {
			return 1; //return 1
		} else {
			//else iterate through the child nodes
			for (int i = 0; i < childNodes.length; i++) {

				//child nodes is not i
				if (childNodes[i] != null) {
					int childHeight = childNodes[i].height(); //recursively go through and put height of child height
					//if child height is greater than max height
					if (childHeight > maxHeight) {
						maxHeight = childHeight; //set max height to child height
					}
				}
			}
			return maxHeight + 1; //return max height and add 1
		}

	}

	/**
	 * Method: toStringPreOrder
	 * @return ... the returnValue
	 */
	public String toStringPreOrder() {
		//declare and initialize a variable and put the data
		String returnValue = data + ", ";

		//iterate through the child nodes
		for (int i = 0; i < childNodes.length; i++) {
			//if child nodes is not null
			if (childNodes[i] != null) {
				returnValue += childNodes[i].toStringPreOrder(); //keep on adding childnodes recursively
			}
		}

		return returnValue; //return returnValue
	}

	/**
	 * Method: toStringPostOrder
	 * @return ... the returnValue
	 */
	public String toStringPostOrder() {
		//declare and initialize a variable and put the data
		String returnValue = "";
		//iterate through the child nodes
		for (int i = 0; i < childNodes.length; i++) {
			//if child nodes are not null
			if (childNodes[i] != null) {
				returnValue += childNodes[i].toStringPostOrder(); //keep on adding child nodes recursively
			}
		}

		returnValue += data + ", "; //keep on adding the data 

		return returnValue; //return returnValue
	}
	
	/**
	 * Method: toStringLevelOrder
	 * @return ... the returnValue
	 */
	public String toStringLevelOrder() {
		//create a linked list 
		LinkedList<Node> list = new LinkedList<Node>();
		//declare and initialize value
		String returnValue = "";

		list.add(this); //add the nodes in the list

		//while list is not empty
		while (!list.isEmpty()) {
			//poll the list in current
			Node current = list.poll();

			//iterate through the list
			for (int i = 0; i < current.childNodes.length; i++) {
				//if current childnodes is not null
				if (current.childNodes[i] != null) {
					
					list.add(current.childNodes[i]); //add the child nodes to the list
				}
			}

			returnValue += current.data + ", "; //keep on adding the current data in the returnValue
		}

		return returnValue; //return the returnValue
	}

	/**
	 * Method: write
	 * @return ... returnVal
	 */
	public String write() {
		//if isLeaf is false
		if (isLeaf == false) {
			//store the nodes data in returnVal
			String returnVal = this.data + " ";
			//iterate through the childnodes
			for (int i = 0; i < childNodes.length; i++) {
				//if child nodes is not null
				if (childNodes[i] != null) {
					//keep on adding child node's data
					returnVal += childNodes[i].data + " ";
				}
			}
			returnVal += "\n"; //go to next line after adding the line

			//iterate through the nodes again
			for (int j = 0; j < childNodes.length; j++) {
				//if child nodes is not equal to null
				if (childNodes[j] != null) {
					//recursively call function and keep on adding it to the returnVal
					returnVal += childNodes[j].write();
				}
			}
			return returnVal; //return returnVal
		} else {
			//else return empty
			return "";
		}

	}
}

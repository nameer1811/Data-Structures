import java.util.LinkedList;

/**
 * Class: ListNodes
 * 
 * Description: Merges relations and adds relations
 * 
 * @author Sheikh Fahad
 *
 */
public class ListNodes {

	// data member
	Nodes parentNode;
	LinkedList<Nodes> list = new LinkedList<Nodes>();

	// constructor
	ListNodes(Nodes node, int index) {
		this.parentNode = node;
		parentNode.index = index;
	}

	/**
	 * Method: addToRelations
	 * 
	 * Description: adds the nodes to the relations, and merges if the node which is
	 * being add is in a different relation
	 * 
	 * @param node ... the destination node
	 */
	public void addToRelations(Nodes node) {
		// if list does not contain node
		if (!list.contains(node)) {
			list.add(node); // add it to the list

			// if index of the node is greater than -1
			if (node.index > -1) {
				mergeRelations(Main.list.get(node.index).list); // merge the relations
			}
			node.index = parentNode.index; // make the nodes index equal to the parent node's index
		}

	}

	/**
	 * Method: mergeRelations
	 * 
	 * Description: Merges node 2's related nodes to node 1's related nodes
	 * 
	 * @param merge ... relation list of node 2
	 */
	public void mergeRelations(LinkedList<Nodes> merge) {
		LinkedList<Nodes> returnList = new LinkedList<Nodes>(); // create new list of nodes

		// add merge and list to the returnList
		returnList.addAll(merge);
		returnList.addAll(list);

		list = returnList; // make the list equal to the returnList

		// iterate through the merge list
		for (int i = 0; i < merge.size(); i++) {
			merge.get(i).index = parentNode.index; // make the indexes equal
		}
	}

}

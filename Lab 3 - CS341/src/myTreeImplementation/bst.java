package myTreeImplementation;

public class bst {

	node root;

	public bst() {
		root = null;
	}

	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	public void insert(item i) {
		node temp = new node(i);

		if (root == null) {
			root = temp; // insert if tree is null
		} else {
			node cursor = root;

			while (cursor != null) {
				if (cursor.data.value < i.value) {
					// insert on the right side

					if (cursor.right == null) {
						cursor.right = temp;
						break; // done with insert
					}
					{
						cursor = cursor.right; // go to next level
					}

				} else {
					if (cursor.data.value > i.value) {
						// insert on the left side

						if (cursor.left == null) {
							cursor.left = temp;
							break; // done with insert
						} else {
							cursor = cursor.left;
						}

					}
				}
			}

		}

	}

	public String toStringPreOrder() {
		if (root == null) {
			return "empty";
		} else {
			return root.toStringPre();
		}

	}

	public String toStringPostOrder() {
		if (root == null) {
			return "empty";
		} else {
			return root.toStringPost();
		}
	}

	// if the root is null then return empty or else return the root in order.
	public String toStringInOrder() {
		if (root == null) {
			return "empty";
		} else {
			return root.toStringIn();
		}

	}

	public int countLeaves() {
		if (root == null) {
			return 0;
		} else {
			return root.countLeaves();
		}
	}

	public int smallestValue() {
		if (root == null) {
			return 0;
		} else {
			return root.smallestValue(root.data.value);
		}
	}

	public int largestValue() {
		if (root == null) {
			return 0;
		} else {

			return root.largestValue(root.data.value);

		}
	}

	public int height() {
		if (root == null) {
			return -1;
		} else {
			return root.height() - 1;
		}
	}

	public int sumOfLeaves() {
		if (root == null) {
			return root.data.value;
		} else {
			return root.sumOfLeaves();
		}
	}

}

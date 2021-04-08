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

	public int getSmallest() {
		if (root == null) {
			return 0;
		} else {
			return root.getSmallest(root.data.value);
		}
	}

	public int getLargest() {
		if (root == null) {
			return 0;
		} else {
			return root.getLargest(root.data.value);
		}
	}
	
	public int sumLeaves()
	{
		if (root == null)
		{
			return 0;
		}
		else
		{
			return root.sumLeaves();
		}
	}

	public int countLeaves() {
		if (root == null) {
			return 0;
		} else {
			return root.countLeaves();
		}

	}

	public void update() {
		if (root != null) {
			root.update();
		}
	}

	public int height() {
		if (root != null) {
			return root.height();
		} else
			return -1;
	}
	
	public int getSum()
	{
		if (root == null)
		{
			return 0;
		}
		else
		{
			return root.getSum();
		}
	}
	public int getBalancingFacotr()
	{
		if (root == null)
		{
			return 0;
		}
		else
		{
			return root.BF;	}
	}

	public String toStringInOrder() {

		if (root == null) {
			return "empty";
		} else {
			return root.toStringIn();
		}
	}

	public String toStringPostOrder() {
		if (root == null) {
			return "empty";
		} else {
			return root.toStringPost();
		}
	}

	public String toStringPreOrder() {
		if (root == null) {
			return "empty";
		} else {
			return root.toStringPre();
		}
	}

	public void AVLinsert(item i) {
		node temp = new node(i);

		if (root == null) {
			root = temp; // insert if tree is null
		} else {
			root.avlInsert(temp);
		}
	}

	public void Insert(item i) {
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
		root.update();
	}
	
	public String returnLevel()
	{
		if (root == null) {
			return "No Level";
		} else {
			return root.returnLevel();
		}
	}
}

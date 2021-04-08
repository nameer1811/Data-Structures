package myTreeImplementation;

import java.util.LinkedList;

public class node {

	item data;

	node left, right;

	int BF;

	public node(item i) {
		data = i;
		BF = 0;
		left = null;
		right = null;
	}

	public String toStringIn() {
		String result = "";

		if (left != null)
			result = result + left.toStringIn();

		result = result + data.value + "/" + BF + ", ";

		if (right != null)
			result = result + right.toStringIn();

		return result;
	}

	public String toStringPre() {
		String result = "";

		result = result + data.value + "/" + BF + ", ";

		if (left != null)
			result = result + left.toStringPre();

		if (right != null)
			result = result + right.toStringPre();

		return result;
	}

	public String toStringPost() {

		String result = "";

		if (left != null)
			result = result + left.toStringPost();

		if (right != null)
			result = result + right.toStringPost();

		result = result + data.value + "/" + BF + ", ";

		return result;
	}

	public int countLeaves() {
		int result = 0;
		if (left == null && right == null) {
			return 1;
		} else {
			int leftcount = 0, rightcount = 0;

			if (left != null)
				leftcount = left.countLeaves();
			if (right != null)
				rightcount = right.countLeaves();

			result = leftcount + rightcount;

			return result;
		}

	}

	public int getSum() {
		int result = this.data.value;
		if (left != null) {
			result = result + left.getSum();
		}
		if (right != null) {
			result = result + right.getSum();
		}

		return result;
	}

	public int sumLeaves() {
		int result = 0;
		if (left == null && right == null) {
			return this.data.value;
		} else {
			int leftsum = 0, rightsum = 0;

			if (left != null)
				leftsum = left.sumLeaves();
			if (right != null)
				rightsum = right.sumLeaves();

			result = leftsum + rightsum;

			return result;
		}

	}

	public int getSmallest(int value) {
		int result = 0;

		if (value < this.data.value) {
			result = value;
		} else {
			result = this.data.value;
		}

		if (left != null) {
			result = left.getSmallest(result);
		}
		return result;

	}

	public int getLargest(int value) {
		int result = 0;

		if (value > this.data.value) {
			result = value;
		} else {
			result = this.data.value;
		}

		if (right != null) {
			result = right.getLargest(result);
		}
		return result;

	}

	public void update() {

		if (left != null) {
			left.update();
		}
		if (right != null) {
			right.update();
		}

		height();
	}

	public int height() {

		if (left == null && right == null) {
			BF = 0;
			return 0;
		} else {
			int leftht = -1, rightht = -1;

			if (left != null)
				leftht = left.height();

			if (right != null)
				rightht = right.height();

			BF = rightht - leftht;

			if (leftht > rightht)
				return leftht + 1;
			else
				return rightht + 1;
		}
	}

	public int avlInsert(node temp) {

		/*
		 * avlInsert will return a 0 if insert leaves the subtree balanced
		 * 
		 * will return -1 if the insert caused an increase in height in the left subtree
		 * 
		 * will return 1 if the insert caused an increase in the height in the right
		 * subtree
		 */
		if (this.data.value > temp.data.value) {
			if (this.left == null) {
				this.left = temp;
				BF = BF - 1;
				return -1;
			} else {
				int value = this.left.avlInsert(temp);

				if (value == 0)
					return 0;

				else {
					BF = BF - Math.abs(value);
					if (BF == 0)
						return 0;
					else if (BF == -1 || BF == 1)
						return BF;
					else {
						if (BF == -2 || BF == 2) {

							System.out.println("Unbalance at " + data.value);

							// fix the code
							// if BF == -2 && left.BF == -1 (single rotation)

							if (BF == -2 && left.BF == -1) {
								item currentData = this.data;
								item leftData = this.left.data;

								node currentLeft = this.left;
								node currentRight = this.right;
								node currentLeftRight = this.left.right;

								this.data = leftData;
								this.right = currentLeft;
								this.left = this.left.left;

								currentLeft.data = currentData;
								currentLeft.left = currentLeftRight;
								currentLeft.right = currentRight;

								this.BF = 0;
								this.right.BF = 0;
								currentLeft.update();

								return 0;
							}

							else if (BF == -2 && left.BF == 1) {

								node aRight = this.right;
								item aData = this.data;
								node b = this.left;
								node bLeft = this.left.left;
								node c = this.left.right;
								node cRight = c.right;
								node cLeft = c.left;

								// right side
								this.data = c.data;

								this.right = new node(aData);
								this.right.left = cRight;
								this.right.right = aRight;

								// left side
								this.left = new node(b.data);
								this.left.left = bLeft;
								this.left.right = cLeft;

								this.update();

								return 0;

							}

						}
					}
				}

			}
		}

		else if (this.data.value < temp.data.value) {

			if (this.right == null) {
				this.right = temp;
				BF = BF + 1;
				return 1;
			} else {
				int value = this.right.avlInsert(temp);

				if (value == 0)
					return 0;

				else {
					BF = BF + Math.abs(value);
					if (BF == 0)
						return 0;
					else if (BF == -1 || BF == 1)
						return BF;
					else if (BF == -2 || BF == 2) {
						System.out.println("Unbalance at " + data.value);

						// fix the code
						// if BF == -2 && left.BF == -1 (single rotation)

						if (BF == -2 && right.BF == -1) {
							item currentData = this.data;
							item rightData = this.right.data;

							node currentLeft = this.left;
							node currentRight = this.right;
							node currentRightLeft = this.right.left;

							this.data = rightData;
							this.left = currentRight;
							this.right = this.right.right;

							currentRight.data = currentData;
							currentRight.right = currentRightLeft;
							currentRight.left = currentLeft;

							this.BF = 0;
							this.left.BF = 0;
							this.update();

							return 0;
						}

						else if (BF == 2 && right.BF == -1) {
							node aLeft = this.left;
							item aData = this.data;
							node b = this.right;
							node bRight = this.right.right;
							node c = this.right.left;
							item cData = c.data;
							node cRight = c.right;
							node cLeft = c.left;

							// right side
							this.data = cData;

							this.left = new node(aData);

							this.left.right = cLeft;
							this.left.left = aLeft;

							// left side
							this.right = new node(b.data);
							this.right.right = bRight;
							this.right.left = cRight;

							this.update();

							return 0;

						}
					}

					return 0;
				}

			}

		}
		return 0;
	}
	
	public String returnLevel()
	{
		//declaring and initializing variables
		int height = height();
		
		int [] result = new int[height];
		
		int level = 0;
		
		//creating list
		LinkedList <node> list = new LinkedList<node>();
		
		//adding current and null to the list
		list.add(null);
		list.add(this);
		
		//while the list is not empty
		while(!list.isEmpty())
		{
			node Node = list.poll(); //poll from the list
			
			//if the node is null increase level
			if(Node == null)
			{
				level++;
			}
			else
			{
				//else result is result + 1 of that level
				result[level] = result[level] + 1;
				
				//if left node is not null add the left node to the list
				if(Node.left != null)
				{
					list.add(Node.left);
				}
				
				//if right node is not null add the right node to the list
				if(Node.right != null)
				{
					list.add(Node.right);
				}
				
				//if left node or right node is not null
				if (Node.left != null || Node.right != null)
				{
					list.add(null); //add null to the list
				}
			}
			
		}
		String returnLevel = "";
		
		//iterate through the array and result the result
		for (int i = 0; i < result.length; i++)
		{
			returnLevel = returnLevel + i + " " + result[i];
		}
		
		return returnLevel;
	}
}

package myTreeImplementation;

public class driver {

	public static void main(String[] args) {

		bst mytree = new bst();
		
		mytree.insert(new item(36));
		mytree.insert(new item(18));
		mytree.insert(new item(55));
		mytree.insert(new item(46));
		
//		System.out.println(mytree.toString());
		
	/*	
		mytree.insert(new item(5));
		mytree.insert(new item(25));
		mytree.insert(new item(1));
		
		//using for loop to insert random numbers into the tree
		for(int i = 0; i < 10; i++)
		{
			mytree.insert(new item((int)(Math.random()*100)));
		}
		*/
		System.out.println("Tree in PreOrder: " + mytree.toStringPreOrder());
		System.out.println("Tree in PostOrder: " + mytree.toStringPostOrder());
		System.out.println("Tree in InOrder: " + mytree.toStringInOrder()); //printing the numbers in order from the tree
		System.out.println("Counting the leaves: " + mytree.countLeaves());
		System.out.println("Height of the tree is: " + mytree.height());
		System.out.println("Sum of leaves: " + mytree.sumOfLeaves());
		System.out.println("The largest value in the tree is: " + mytree.largestValue());
		System.out.println("The smallest value in the tree is: " + mytree.smallestValue());
		
		
		
		/*
		 * Lab to be completed by Wednesday 10/14
		 * 
		 * Insert another 10 different values into the bst
		 * 
		 * Add a method to do an inorder traversal and a toString to get output by inorder
		 * 
		 * Output the result and note that it is values in ascending order
		 */
		
	}

}

package myTreeImplementation;

import javax.swing.JOptionPane;

public class driver {

	public static void main(String[] args) {

		bst mytree = new bst();
		
	//	System.out.println(mytree.toString());
		
		boolean done = false;
		
		int v;
		
		while (!done)
		{
			v = Integer.parseInt(JOptionPane.showInputDialog("Please input an integer value"));
			
			mytree.AVLinsert(new item(v));

			System.out.println("Printing in InOrder: " +mytree.toStringInOrder());
			System.out.println("Printing in PostOrder: " +mytree.toStringPostOrder());
			System.out.println("Printing in PreOrder: " + mytree.toStringPreOrder());
			System.out.println("Number of Leaves: " + mytree.countLeaves());
			System.out.println("Height of tree: " + mytree.height());
			System.out.println("Sum of tree: " + mytree.getSum());
			System.out.println("Sum of leaves in the tree: " + mytree.sumLeaves());
			System.out.println("Smallest value in tree: " + mytree.getSmallest());
			System.out.println("Largest value in tree: " + mytree.getLargest());
			System.out.println("Balancing Factor of the tree is: " + mytree.getBalancingFacotr());
		}
		
	}

}

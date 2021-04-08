package mywaytree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class driver {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		
		// open a file and read the first value for m 
		
		Scanner infile = new Scanner(new File("mwaytree.dat"));
		
		int m = infile.nextInt();
		
		System.out.println("we are doing a " + m + " way tree");
		
		
		int rootvalue = infile.nextInt();
		
		infile.nextLine();
		
		node root = new node(m, rootvalue);
		
		
		node foundnode = root.find(rootvalue);
		
		System.out.println("the found node has the value " + foundnode.data);
		
	
		
		// we now have to read line after line and create the tree
		
		while (infile.hasNextInt())  // read until file has been exhausted
		{
			
			String line = infile.nextLine();
			
			System.out.println(line);
			
			root.updatetree(line);
		
			
		}
			
			
		node foundnode2 = root.find(22);
		
		System.out.println("the found node has the value " + foundnode2.data);

		root.levelorder();
		
//		root.inorder();
		
//		System.out.println("the total of all values is " + root.returntotal());
//		System.out.println("the number of even values is " + root.returnevens());
		
		

	}

}

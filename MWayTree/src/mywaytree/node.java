package mywaytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class node {
	
	int data;
	
	node[] children;
	
	int m;
	
	public node(int m, int value)
	{
		data = value;  // value passed is the data in this node
		children = new node[m];  // there will be m children, all null
		this.m = m;
	}

	public void updatetree(String line) {
		// TODO Auto-generated method stub
		
		Scanner linedata = new Scanner(line);
		
		int nodeval = linedata.nextInt();
		
		node nodeforinsert = this.find(nodeval); // find the node that should have the rest of the items from the line
		
		System.out.println(nodeforinsert.data);
		nodeforinsert.addchildren(linedata);  // add children to the node that was found.
		
	}
	
	
	private void addchildren(Scanner linedata) {
		// TODO Auto-generated method stub
		
		int child = 0;
		
		while (linedata.hasNext())
		{
			this.children[child] = new node(this.children.length, linedata.nextInt());  // create a new node, and make it a child 
			child++;
		}
		
	}

	public node find(int nodeval)
	{
		
			if (data == nodeval)
			{
				return this;
			}
			else
			{
				for (int i = 0; i < children.length; i++)
				{
					if (children[i] != null)
					{
						
						node foundnode = children[i].find(nodeval);  // try find on children[i]
									
						if (foundnode != null)  // if found then return 
						{
							return foundnode;
						}
					}
				}
				
			}
			
			return null;
	}
	public void levelorder()
	{	
		Queue<node> myque = new LinkedList<node>();	
		myque.add(this);

		while (!myque.isEmpty())
		{
			node n = myque.remove();
			
			if (n.isLeaf())
			{
				continue;
			}
			
			String line = n.data + " ";
			
			int i = 0;
			
			while (i < m && n.children[i] != null)
			{
				myque.add(n.children[i]);
				line = line + n.children[i].data + " ";
	
				i++;
			}
			
			System.out.println(line);
		}
	}
	
	private boolean isLeaf() {
		// TODO Auto-generated method stub
		if (children[0] == null)
			return true;
		else
			return false;
	}

	public void inorder()
	{
				
			if (this != null)
			{
				if (children[0] != null) children[0].inorder();
				
			    System.out.println(this.data + " ");
				
				for (int i = 1; i < children.length; i++)
				{
					if (children[i] != null)
					{					
						children[i].inorder();  // try find on children[i]
					}
				}
				
			}
		
	}
	
	public int leafSums (String line, int total)
	{
		//if is leaf
		if (isLeaf())
		{
			//print out the total
			System.out.println(total + "(" + line + ")");
		}
		else
		{
			//create a node which is null 
			
			// iterate through each child
			for (node Children : children )
			{
				//if the node is not null
				if (Children != null)
				{
					//recursively keep on adding
					Children.leafSums(line+"+"+Children.data, total + Children.data);
				}
			}
		}
		return leafSums(line,total);
	}
	

}

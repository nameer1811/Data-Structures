package myTreeImplementation;

public class node {

	item data;
	
	node left, right;
	
	int balancingFactor;
	
	public node(item i)
	{
		data = i;
		
		left = null;
		right = null;
	}
	
	public String toStringPre()
	{
		String result = "";
		
		result = result + data.value + ", ";
		
		if (left != null) 
			result = result + left.toStringPre();
		
		if (right != null)
			result = result + right.toStringPre();
		
		return result;
	}

	public String toStringPost() {
			
		String result = "";
		
		
		if (left != null) 
			result = result + left.toStringPre();
		
		if (right != null)
			result = result + right.toStringPre();

		result = result + data.value + ", ";

		return result;
	}
	
	//InOrder method 
	
	public String toStringIn() {
		String result = ""; //setting result to empty string
		
		//if left is not null then add the result from the left node
		if (left != null)
		{
			result = result + left.toStringIn();
		}
		
		//add the result and the data value in the strings with comma separated
		result = result + data.value + "/" + balancingFactor() + ", ";
		
		//if right is not null then add the result from the right node
		if (right != null)
		{
			result = result + right.toStringIn();
		}
		return result; //return the result
	}
	
	//countLeaves method
	public int countLeaves() {
		int result = 0; //setting result to empty string
		
		//if left is null and right is null then return 1
		if (left == null && right == null)
		{
			return 1; 
		}
		else {
			int leftCount = 0, rightCount = 0; //declaring and initializing variables
			
			//if left is not null then left count is going to be recursive and put into leftCount
			if (left != null)
			{
				leftCount = left.countLeaves();
			}

			//if right is not null then left count is going to be recursive and put into righttCount
			if(right != null) {
				rightCount = right.countLeaves();
			}
			
			//result is sum of leftCount and rightCount
			result = leftCount + rightCount;
			
			return result; //return result
		}
	}
	
	public int smallestValue(int value)
	{
		int result = value;
		
		if (value > data.value)
		{
			result = data.value;
			
		}
		
		if(left != null)
		{
			result = left.smallestValue(result);
		}
		
		if(right != null)
		{
			result = right.smallestValue(result);
		}
		
		return result;
	}
	
	public int largestValue(int value) 
	{
	int result = value;
		
		if (value < data.value)
		{
			result = data.value;
			
		}
		
		if(left != null)
		{
			result = left.largestValue(result);
		}
		
		if(right != null)
		{
			result = right.largestValue(result);
		}
		
		return result;
	}
	
	public int height()
	{
		int result = 0;
		
		if (left == null && right == null)
		{
			return 1;
		}
		else 
		{
			int leftHeight = 0, rightHeight = 0;
			
			if (left != null)
			{
				leftHeight = left.height();
			}
			
			if (right != null)
			{
				rightHeight = right.height();
			}
			
			if (leftHeight > rightHeight)
			{
				result = leftHeight + 1;
			}
			else {
				result = rightHeight + 1;
			}
			
			return result;
		}
	}
	
	public int sumOfLeaves() {
		int result = 0;
		if(left == null && right == null)
		{
			return data.value;
		}
		else {
			int leftSum = 0, rightSum = 0;
			
			if (left != null)
			{
				leftSum = left.sumOfLeaves();
			}
			if(right != null)
			{
				rightSum = right.sumOfLeaves();
			}
			
			result = rightSum + leftSum;
			return result;
		}
	}
	
	public int balancingFactor()
	{
		int leftHeight = 0, rightHeight = 0;
		if (left != null)
		{
			leftHeight = left.height();
		}
		if (right != null)
		{
			rightHeight = right.height();
		}
		
		balancingFactor = rightHeight - leftHeight;
		
		return balancingFactor;
	}
	
	
}

package finalGraphProject;

import java.util.StringTokenizer;

public class graph {

	String[] vertexarray;
	int v;
	int[][] adjmatrix;

	public graph(String vertices) {

		System.out.println("the vertices in the graph are " + vertices);

		StringTokenizer stk = new StringTokenizer(vertices);

		v = stk.countTokens();

		vertexarray = new String[v];

		for (int i = 0; i < vertexarray.length; i++) {
			vertexarray[i] = stk.nextToken();
		}

		adjmatrix = new int[v][v];

	}

	public int getindex(String s) {
		int spot = -1;
		for (int i = 0; i < vertexarray.length; i++) {
			if (vertexarray[i].equals(s))
				spot = i;
		}
		return spot;
	}

	public String getvertexname(int i) {
		return vertexarray[i];
	}

	public void addedge(String s) {
		StringTokenizer stk = new StringTokenizer(s);

		int from = getindex(stk.nextToken());
		int to = getindex(stk.nextToken());

		int w = Integer.parseInt(stk.nextToken());
		;

		adjmatrix[from][to] = w;

	}

	public String toString() {
		String result = "The graph is\n      ";

		for (int i = 0; i < v; i++) {
			result += this.getvertexname(i) + "  ";
		}

		result += "\n";

		int i, j;

		for (i = 0; i < v; i++) {
			result += this.getvertexname(i) + "    ";

			for (j = 0; j < v; j++) {

				if (adjmatrix[i][j] > 0) {
					result += " " + adjmatrix[i][j] + " ";
				} else
					result += " 0 ";
			}
			result += "\n";
		}
		return result;
	}

	/**
	 * Method: make2edgegraph
	 *
	 * Description: makes 2 edges between graphs
	 */
	public void make2edgegraph() {
		//iterate through
		for (int i = 0; i < v; i++) {
			//create new array edgeConnect and make the size v 
			String[] edgeConnect = new String[v];
			int index = 0; //declare and initialize variable index
			
			//iterate v number of times
			for (int j = 0; j < v; j++) {
				//if adjmatrix greater than 0
				if (adjmatrix[i][j] > 0) {
					edgeConnect[index++] = getvertexname(j); //put getvertname's jth value in edgeConnect's index
				} 
				//else if index is greater than 0
				else if (index > 0) {
					//iterate index number of times
					for (int k = 0; k < index; k++) {
						//create string variable and put edgeConnect's i value
						String vertices = edgeConnect[i];
						//create int variable and put the index of the vertices
						int vertexNum = getindex(vertices);
						
						//if adjmatrix is greater than 0
						if (adjmatrix[vertexNum][j] > 0) {
							//adjmatrix will have adjmatric i and vertex num + adjmatric vertexNum and j
							adjmatrix[i][j] = adjmatrix[i][vertexNum] + adjmatrix[vertexNum][j];
						}
					}
				}
			}
		}
	}

}

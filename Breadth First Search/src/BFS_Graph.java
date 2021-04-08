import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

// This class represents a directed graph using adjacency list 
// representation 
public class BFS_Graph {
	// data members
	private int vertices; // No. of vertices
	private LinkedList<Integer> neighbours[]; // using adjacency list
	// constructor
	public BFS_Graph(int vertices) {
		this.vertices = vertices;
		neighbours = new LinkedList[vertices];
		for (int i = 0; i < vertices; ++i)
			neighbours[i] = new LinkedList();
	}
	// setting a starting vertex, and printing the vertices
	public void BFS(int startingVertex) {
		// creating a boolean array for the vertices
		boolean visited[] = new boolean[vertices];
		// creating a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// Mark the current object as visited by making it true and then queue it
		visited[startingVertex] = true;
		queue.add(startingVertex);
		while (queue.size() != 0) {
			// remove a vertex from queue and print it
			startingVertex = queue.poll();
			System.out.print(startingVertex + " ");
			// Get all adjacent vertices of the printed vertex
			// If an adjacent vertex has not been visited, then visit it and queue it
			Iterator<Integer> i = neighbours[startingVertex].listIterator();
			while (i.hasNext()) {
				int nextVertex = i.next();
				if (!visited[nextVertex]) {
					visited[nextVertex] = true;
					queue.add(nextVertex);
				}
			}
		}
	}
	// connecting edges from one vertex to another
	public void connectEdges(int vertice1, int vertice2) {
		neighbours[vertice1].add(vertice2);
	}

	public static void main(String args[]) {
		//making scanner object and scanning all the necessary values
		Scanner scan = new Scanner(System.in);
		System.out.println("Input the no. of vertex: ");
		int numberOfvertex = scan.nextInt();
		BFS_Graph graph = new BFS_Graph(numberOfvertex);
		System.out.println("Input the no. of edge: ");
		int numberOfedge = scan.nextInt();
		System.out.println("Input starting vertex: ");
		int startingVertex = scan.nextInt();
		//putting a for loop and connecting edges from one vertex to another
		System.out.println("Put the edges you want to connect");
		for (int i = 0; i < numberOfedge; i++) {
			System.out.print("From vertex: ");
			int vertex1 = scan.nextInt();
			System.out.print("to vertex: ");
			int vertex2 = scan.nextInt();
			System.out.println("Adding edge from " + vertex1 + " to " + vertex2);
			graph.connectEdges(vertex1, vertex2);
		}
		//searching the graph and printing vertices using the BFS method
		System.out.println("Printing out Breadth First Search");
		graph.BFS(startingVertex);
	}
}
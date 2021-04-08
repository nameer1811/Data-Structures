package finalGraphProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class driver {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner inp = new Scanner(new File("inputgraph"));
		
		String vertices = inp.nextLine();
		
		graph gr = new graph(vertices);
		
		while (inp.hasNext())
		{
			gr.addedge(inp.nextLine());
		}
		
		gr.make2edgegraph();
		
		System.out.println(gr.toString());
	}

}

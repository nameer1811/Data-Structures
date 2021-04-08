import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class: Main
 * 
 * @author Sheikh Fahad and Ashton Schultz
 *
 */
public class Main {

	public static void main(String[] args) {

		try {
			Scanner scan = new Scanner(new File("mwaytree.dat")); //scan the file 

			int numberOfWay = Integer.parseInt(scan.nextLine()); //get the first value from the file and store it as int
			int root = Integer.parseInt(scan.nextLine()); //get the second value from the file and store it as int
			String line = scan.nextLine(); //get the next line and store it as line 

			MultiWayTree mwt = new MultiWayTree(numberOfWay); //making a multiwaytree object

			mwt.insert(0, root); //insert the first value in the first node

			//while line is not null
			while (line != null) {
				String[] child = line.split(" "); //store value of the line in array by splitting them

				//iterate through
				for (int i = 1; i < child.length; i++) {
					int value = Integer.parseInt(child[i]); //store each value as an int in value
					mwt.insert(Integer.parseInt(child[0]), value); //set the parent node and insert the child
				}

				//if scanner has next line
				if (scan.hasNext()) {
					line = scan.nextLine(); //read next line 
				} else {
					break; //break out of loop
				}
			}
			
			//print out the tree
			System.out.println("Printing tree in PreOrder: " + mwt.preOrder());
			System.out.println("Printing tree in PostOrder: " + mwt.postOrder());
			System.out.println("Priniting tree in LevelOrder: " + mwt.levelOrder());
			System.out.println("Printing the height of the tree: " + mwt.height());

			PrintWriter pw = new PrintWriter("mwaytreeOutput.dat"); //output file name 

			pw.print(mwt.writeInFile()); //print the data of the tree in the file

			pw.flush(); //flush the data in the file
			pw.close(); //close printer

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
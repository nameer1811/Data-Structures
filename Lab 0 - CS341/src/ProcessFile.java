import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class ProcessFile {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName; // initiating variable

		fileName = JOptionPane.showInputDialog("Please type in the name of the input file"); // asking for file name

		Scanner inFile = new Scanner(new File(fileName)); // scanned data from file

		//initializing data
		int lineNumber = 1, largestValue = Integer.MIN_VALUE, smallestValue = Integer.MAX_VALUE, totalSum = 0;
		int minLine = 0, maxLine = 0, lineCounter = 0, lineMaxCounter = 0, lineMinCounter = 0;

		while (inFile.hasNext()) { //while loop starts
			String dataLine = inFile.nextLine(); // reading the data of the line of data

			StringTokenizer tokens = new StringTokenizer(dataLine); // separates data

			int numberOfValues = tokens.countTokens(); // counts each data and returns how many data is in there

			int sum = 0; // initializing variable

			// initializing max value and min value
			int Max = Integer.MIN_VALUE;
			int Min = Integer.MAX_VALUE;

			for (int i = 0; i < numberOfValues; i++) { //for loop starts

				int token = Integer.parseInt(tokens.nextToken()); // token is separated and made into an integer. Put it
																	// in token.
				sum += token; // adding all the tokens
				// if token is greater than max, set token to max
				if (token > Max) {
					Max = token;
				} 
				// if token is less than min, set token to min
				if (token < Min) {
					Min = token;
				} 
			} // for loop ends

			if (largestValue < Max) {
				largestValue = Max;
				maxLine = lineNumber;
			} // if largest is less than max, largest will be max and line number will be set
			if (smallestValue > Min) {
				smallestValue = Min;
				minLine = lineNumber;
			} // if smallest is greater than min, smallest will be min and line number will be
				// set

			// if statements for Loss, Profit, or Breakeven
			if (sum < 0) {
				System.out.println("The number of values in line " + lineNumber + " is " + numberOfValues
						+ " and the sum of the values are " + sum + ", which is a LOSS!");
			}
			if (sum > 0) {
				System.out.println("The number of values in line " + lineNumber + " is " + numberOfValues
						+ " and the sum of the values are " + sum + ", which is a PROFIT!");
			}
			if (sum == 0) {
				System.out.println("The number of values in line " + lineNumber + " is " + numberOfValues
						+ " and the sum of the values are " + sum + ", which is a BREAKEVEN!");
			}

			tokens = new StringTokenizer(dataLine); // separates data
			// initiating variables
			int minCount = 0;
			int maxCount = 0;
			for (int i = 0; i < numberOfValues; i++) { //for loop starts
				int token = Integer.parseInt(tokens.nextToken()); // token is separated and made into an integer. Put it
																	// in token.
				sum += token; // adding all the tokens
				if (token == Max) {
					maxCount++;
				} // if token is greater than max, set token to max
				if (token == Min) {
					minCount++;
				} // if token is less than min, set token to min
			} //for loop ends
			if (minCount > 1) {
				System.out.println("Data in this line has been repeated " + minCount + " times.");
			}
			if (maxCount > 1) {
				System.out.println("Data in this line has been repeated " + maxCount + " times.");
			}
			System.out.println(
					"The largest value in the line is " + Max + ". The smallest value in the line is " + Min + "\n");
			lineNumber++; // go to next line

			totalSum += sum;

		} // end of while loop
		System.out.println(
				"There are " + (lineNumber - 1) + " total lines in this file" + " and the largest value in the file is "
						+ largestValue + " and the smallest value is " + smallestValue + ".");
		System.out.println("The largest value in the file is at line " + maxLine
				+ " and the smallest value in the file is at line " + minLine + ".");

		// if statements for Loss, Profit, or Breakeven
		if (totalSum < 0) {
			System.out.println(
					"The total sum for the whole file is " + totalSum + ". The total sum of the file is a LOSS!");
		}
		if (totalSum > 0) {
			System.out.println(
					"The total sum for the whole file is " + totalSum + ". The total sum of the file is a PROFIT!");
		}
		if (totalSum == 0) {
			System.out.println(
					"The total sum for the whole file is " + totalSum + ". The total sum of the file is a BREAKEVEN!");
		}

		inFile.close(); // closing scanner
		inFile = new Scanner(new File(fileName)); // creating scanner to reiterate through the file again

		while (inFile.hasNext()) {
			String dataLine = inFile.nextLine(); // reading the data of the line of data

			StringTokenizer tokens = new StringTokenizer(dataLine); // separates data

			int numberOfValues = tokens.countTokens(); // counts each data and returns how many data is in there

			for (int i = 0; i < numberOfValues; i++) {

				int token = Integer.parseInt(tokens.nextToken()); // token is separated and made into an integer. Put it
																	// in token.

				// checks if token is equal to the largest/smallest values and the max/min line
				// is not equals line counter
				if (token == largestValue && maxLine != lineCounter) {
					lineMaxCounter++;
				}
				if (token == smallestValue && minLine != lineCounter) {
					lineMinCounter++;
				}
			}

			lineCounter++; // increment line counter

		}

		System.out.println("The largest occured " + lineMaxCounter
				+ " time(s) other than the intial line, while the smallest occured " + lineMinCounter
				+ " time(s) other than the inital line.");

	}
}

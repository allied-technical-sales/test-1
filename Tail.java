import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
*
* The Tail java program implements the linux tail command
* outputs the "tail"(last part) of file.
*
* @author Hao Chen
* @since 2017-08-03
*
*/

public class Tail {  
  /**
  * This is the main method which read command line arguments.
  * and validate inputs.
  * @param args Command line arguments.
  * @return Nothing.
  * @exception IllegalArgumentException on input error.
  * @see IllegalArgumentException
  */
  public static void main(String[] args) {
    final int DEFAULT_NUM_LINES = 10; // print last 10 lines by default
    final String NO_FILE_SPECIFIED = "-"; // dash represent no file is specified
    
    if (args.length > 3)
      throw new IllegalArgumentException("Maximum 3 command line arguments can be specified");
    else if (args.length == 0) {
      printTailLines(NO_FILE_SPECIFIED, DEFAULT_NUM_LINES);
    } else if (args.length == 1) {
      printTailLines(args[0], DEFAULT_NUM_LINES);
    } else if (args.length == 2) {
      printTailLines(args[1], DEFAULT_NUM_LINES);
    } else {
      // if the second argument is "-n", the third argument needs to be a number >= 0
      if (args[1].equals("-n") && checkNumberArgument(args[2])) {
        int lastNumLines = Integer.valueOf(args[2]); // convert the String argument to int
        printTailLines(args[0], lastNumLines);
      } else {
        throw new IllegalArgumentException("Invalid argument");
      }   
    }
  }
  
  /**
  * This is the method which reads a file and print the "tail" of the file 
  * @param fileName This is the file name.
  * @param lastNumLines This is the number of lines we want to print.
  * @return Nothing.
  * @exception FileNotFoundException.
  * @see FileNotFoundException.
  */
  
  public static void printTailLines(String fileName, int lastNumLines) {
    try {
      Scanner sc = null;
      if (fileName.equals("-")) {
        sc = new Scanner(System.in); // if the file name is "-"(unspecified), read from standard in.
      } else {
        sc = new Scanner(new FileReader(fileName)); // read from file if file is specified.
      }
      String[] output = new String[lastNumLines]; // String buffer which holds the output.
      int totalLines = 0;
      int index = 0;
      while (sc.hasNext()) {
        output[index] = sc.nextLine();
        index = (index + 1) % lastNumLines; // if n lines are read, we stars over again.
        totalLines++;
      }
      if (lastNumLines > totalLines) { // if file has less number lines than the number of lines to print
        for (int i = 0; i < totalLines; i++) {
          System.out.println(output[i]); // output the whole file
        }
      } else {
        for (int i = 0; i < lastNumLines; i++) {
          System.out.println(output[index]); // output last number of lines
          index = (index + 1) % lastNumLines;
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("could not find file");
    }
  }
  
  /**
  * This is the method which checks the string only containing numbers.
  * @param arg This is the String argument.
  * @return boolean, true if contains only numbers, otherwise false.
  */
  
  public static boolean checkNumberArgument(String arg) {
    if (arg.matches("[0-9]+")) 
      return true;
    else 
      return false;
  }
}
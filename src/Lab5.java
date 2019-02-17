import java.io.*;
import java.util.*;

/**
 * Computer Science Information Technology:
 *      Object Oriented Programming
 *      Lab5
 *
 * @author Brandon S. Ramos
 * date: 2/16/18
 * Resources: Java Book, Youbube Search "algorithms, recursion and iteration"
 *
 * Algorithm:
 * 1.) Create a method that reads the file and stores contents in array
 * 2.) Create a method to organize the array
 * 3.) Create iterative method to check for palindrome
 * 4.) Create recursive method to check for palindrome
 * 5.) Print out ot the console the palindromes
 */

public class Lab5 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("What file will i be reading from?:");
        String fileName = scan.nextLine();
        //String fileName = "palindrometest.txt";
        String[] array = loadWordsFromFile(fileName); //Array holds potential palindromes
        int numLines = array.length;
        String[] alphabetizedArray = alphabetize(array, numLines); //Alphabetized array
        int numOfPalindromes = numOfPalindromes(alphabetizedArray); //Get num of palindromes in array

        //Iteratively find the palindromes
        String[] palindromesByIteration = new String[numOfPalindromes];
        int indexForIterationArray = 0;
        for(int i = 0; i<alphabetizedArray.length; i++){
            if(palindromeIterative(alphabetizedArray[i])){
                palindromesByIteration[indexForIterationArray] = alphabetizedArray[i];
                indexForIterationArray++;
            }
        }

        String[] palindromesByRecursion = new String[numOfPalindromes];
        int indexForRecursionArray = 0;
        for(int i = 0; i<alphabetizedArray.length; i++){
            if(palindrome(alphabetizedArray[i])){
                palindromesByRecursion[indexForRecursionArray] = alphabetizedArray[i];
                indexForRecursionArray++;
            }
        }

        //Output to the console
        System.out.println("There are " + numOfPalindromes + " in " + fileName +"\n");

        System.out.println("Palindromes by Iteration are:");
        for(int i = 0; i < palindromesByIteration.length; i++){
            System.out.println(palindromesByIteration[i]);
        }
        System.out.println("\nPalindromes by Recursion are:");
        for(int i = 0; i < palindromesByRecursion.length; i++){
            System.out.println(palindromesByRecursion[i]);
        }
    }

    /**
     * Check recursively if string is a palindrome
     * @param string potential palindrome
     * @return true if string is palindrome
     */
    public static boolean palindrome(String string){
        //If the string has no length or is one letter long, it is a palindrome
        if(string.length()==1 || string.length()==0){
            return true;
        }
        if(string.charAt(0)==string.charAt(string.length()-1)){
            return palindrome(string.substring(1,string.length()-1));
        }
        return false;
    }

    /**
     * Check Iteratively if the string is a palindrome
     * @param string potential palindrome
     * @return true if string is palindrome
     */
    public static boolean palindromeIterative(String string){
        boolean palindrome = false;
        String reverseString = "";
        for(int i = string.length()-1; i>=0 ; i--){
            reverseString += string.charAt(i);
        }
        if(string.equals(reverseString)){
            palindrome = true;
        }
        return palindrome;
    }

    /**
     * Find how many palindromes are in the array
     * @param array array of words
     * @return number of palindromes in array
     */
    public static int numOfPalindromes(String[] array){
        int numOfPalindromes = 0;
        for(int i = 0; i < array.length; i++){
            if(palindromeIterative(array[i])){
                numOfPalindromes++;
            }
        }
        return numOfPalindromes;
    }

    /**
     * This will scan the file we provide and put the words in an array.
     * @param fileName text file we give the program
     * @return wordArray array of words
     * @throws Exception needs to be there
     */
    public static String[] loadWordsFromFile(String fileName) throws Exception{
        File file = new File(fileName);
        Scanner fileScanForNumLine = new Scanner(file);
        Scanner fileScan = new Scanner(file);

        //Lets first find out how many lines there are
        int numLines = 0;
        while(fileScanForNumLine.hasNext()){
            numLines++;
            fileScanForNumLine.nextLine();
        }

        //Make an array with the size of numLines
        String[] wordArray = new String[numLines];

        //Add the words to the array
        int row = 0;
        while(fileScan.hasNext()){
            String word = fileScan.nextLine();
            wordArray[row] = word;
            row++;
        }
        return wordArray;
    }

    /**
     * Will alphabetized the current array
     * @param array potential palindromes
     * @param sizeArray how many lines are in the .txt file
     * @return organized array
     */
    public static String[] alphabetize(String[] array, int sizeArray){
        for(int i = 0; i < sizeArray; i++){
            int index = 1;
            while(index < sizeArray){
                //Flip words around to alphabetize them
                if(array[index-1].compareTo(array[index]) > 0){
                    String words = array[index-1];
                    array[index-1] = array[index];
                    array[index] = words;
                }index++;
            }
        }
        return array;
    }
}

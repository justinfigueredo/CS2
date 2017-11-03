// Justin Figueredo
// Homework 7
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class HW7 {
	
	public int arrayLength = 9227;
	public double ratio = 0.75;
	public LinkedList<String>[] table;
	public Hashtable<String, String> hashTable;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HW7 hw7 = new HW7();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("File path to read from: "); // get file to read from
		String path = keyboard.nextLine();
		
		System.out.print("Where do you want the HW7.out file to go: "); // get file path to write the HW7.out file
		String outPath = keyboard.nextLine();
		
		System.out.print("Where do you want the HW7.hash file to go: "); // get file path to write the HW7.hash file
		String hashPath = keyboard.nextLine();
		
		try{
		System.out.print("How long would you like the array to be? (for default length of 9227 press any letter)"); // get length of array from user
		hw7.arrayLength = keyboard.nextInt();
		
		System.out.print("What load factor would you like to use for the hash table? (for default load factor of 0.75 press enter)"); // get load factor from user
		hw7.ratio = keyboard.nextDouble();
		}
		catch(Exception e){System.out.println("Not Valid input. The Default values of length: 9227 and load factor: 0.75 will be used.");} // use default values in case the user is dumb
		
		keyboard.close();
		
		hw7.table = new LinkedList[hw7.arrayLength]; // makes my hash table
		hw7.hashTable = new Hashtable<String, String>(hw7.arrayLength, (float)hw7.ratio); // makes java hash table
		
		ArrayList<String> words = hw7.getUniques(hw7.importData(path)); // imports the words and gets the unique ones
		hw7.populateHashTables(words); // populates both hash tables
		
		hw7.printMyTableCounts(); // prints biggest number of words in a linked list and number of empty indexes
		
		hw7.writeOutFile(outPath); // writes the HW7.out file
		hw7.writeHashFile(hashPath); // writes the HW7.hash file
		
		
	}
	
	public int hash(String word) // hashes the key
	{
		String number = "";
		String upperWord = word.toUpperCase(); // makes it so the ascii values will be less

			for(int i =0; i < word.length(); i++)
				number += (int)upperWord.charAt(i); // adds the ascii values of each character together
			
			if(number.length() > 17) // makes sure the returned number is not bigger than an int
				number = number.substring(0,17);
			
			return (int)(Long.parseLong(number) % arrayLength); // returns the hashed value
	}
	
	public void populateHashTables(ArrayList<String> words) //adds all the words to the hash tables
	{
		/////////////////////////////////// My HashTable
		for(String word : words)
		{
			int index = hash(word); // hashes key

			if(table[index] == null) // makes sure the linked list is initialized before adding
			{
				table[index] = new LinkedList<String>(); // initializes the linked list
			}

			table[index].add(word); // adds the word to the linked list
		}
		//////////////////////////////////
		
		////////////////////////////////// Java HashTable
		
		for(String word : words)
		{
			hashTable.put(word, word);
		}
		
		/////////////////////////////////
	}

	public ArrayList<String> importData(String filePath)
	{
		ArrayList<String> stringList = new ArrayList<String>();
		String data = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((data = br.readLine()) != null) { // makes data equal to the next line and checks if it is null
				
				String[] parts = data.split(" "); // splits the read in line into an array
				for(String part : parts) // goes through each word
				{
					if(part.contains("-") && !part.equals("-")) // if the part contains a dash and is not just a dash, we want to split it to two words
					{
					String first = part.substring(0, part.indexOf("-"));
					String second = part.substring(part.indexOf("-"), part.length());
					first = first.replaceAll("[,_`.!@#$%^&*()-/?'^\"|\"$\\[\\]\\:;<>]", "").toLowerCase();
					second = second.replaceAll("[,_`.!@#$%^&*()-/?'^\"|\"$\\[\\]\\:;<>]", "").toLowerCase();
					if(!first.equals("")) // makes sure we are not adding nothing
					stringList.add(first);
					
					if(!second.equals("")) // makes sure we are not adding nothing
					stringList.add(second);
					}
					else // adds the fixed part to the string list
					{	
					part = part.replaceAll("[,_`.!@#$%^&*()-/?'^\"|\"$\\[\\]\\:;<>]", "").toLowerCase();
					if(!part.equals("")) // makes sure we are not adding nothing
					stringList.add(part);
					}
				}
				}
			br.close();
			}
			catch(Exception e)
		{System.out.println("The file to read from does not exist. Check your file path.");}
		return stringList;
	}
	
	public void writeOutFile(String outPath)
	{
		try{

			BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));

			for(int i = 0; i < 6; i++) // make sure to only print the elements for indexes 0 to 5
			{
				if(table[i] == null) // avoids exception
				{
					bw.append(String.format("%-20s%-10s", i, "null") + " \n"); // puts null as the value to put in the file
				}
				else
				{
					bw.append(String.format("%-20s%-10s", i, table[i].toString()) + " \n"); // puts in the elements in the linked list
				}
			}

			bw.write(bw.toString()); // writes it all to the file
			bw.flush();
			bw.close();
			
		}
		catch(IOException e)
		{System.out.println("File path for HW7.out does not exist. Check file path.");}
	}
		
	public void writeHashFile(String hashPath)
	{
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(hashPath));
			
			int count = 0;
			String key = "";
			Enumeration<String> keys = hashTable.elements(); // gets hash table elements
			while(keys.hasMoreElements()) // loops through the elements
			{
				key = keys.nextElement(); // gets the next element
				if(count < 5) // make sure to only print the first 5 elements
				{
					bw.append(String.format("%-20s%-10s", key, key) + " \n"); //adds the bufferedWriter to print
				}
				count++;
			}

			bw.write(bw.toString()); // writes to file
			bw.flush();
			bw.close();
		}
		catch(IOException e)
		{System.out.println("File path for HW7.hash does not exist. Check file path.");}
	}

	public ArrayList<String> getUniques(ArrayList<String> list) // returns an ArrayList containing only the unique words from the file
	{
		ArrayList<String> wordList = new ArrayList<String>();
		
		for(String word : list)
		{
			if(!wordList.contains(word))
			{
				wordList.add(word);
			}
			
		}
		
		return wordList;
	}
	
	public void printMyTableCounts() // prints biggest number of words in a linked list and number of empty indexes
	{
		int nulls = 0;
		int big = 0;
		for(int i =0; i < table.length; i++)
		{
			if(table[i] != null) // if there is at least one word
			{
				if(table[i].size() > big) // if the size is bigger than any previous one
				{
					big = table[i].size(); // make big the new biggest linked list
				}
			}
			else // is an empty index
			{
				nulls++;
			}
		}
		System.out.println("Largest number of words in a linked list: " + big + "\nNumber of empty indexes: " + nulls); // print the values
	}
	
}

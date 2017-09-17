//HW2 Countries
//Justin Figueredo
//9/14/2017
//This program takes in Countries and Borders data and then makes and array and linked list of Countries objects.
//This program will display countries that border a given country you give it or display countries with a population over the number you give it.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HW2 {
	
public Countries[] countryArray = new Countries[9];
public CountriesLL countryLL;
public boolean done = false;

	public static void main(String[] args) throws IOException {
		try
		{
	HW2 thing = new HW2();	
	Borders border = new Borders();
	//CountriesLL countriesLL = new CountriesLL("","","",0,0,0.0,0);
	Scanner keyboard = new Scanner(System.in); // using scanner for user interface
	// gave each method an instance of border and/or thing because i'm not sure if we are allowed to use static
	System.out.println("HW2");
	thing.displayImportData(border, thing);
	
	while(!thing.done)
	{
	thing.displayBorderingCountries(border);
	
	thing.displayPopulationCountries(thing);
	
	thing.displayPopulationAndBorderingCountries(border, thing);
	
	thing.quit();
	}
	
	keyboard.close();
		}
		catch(Exception e){}
	}
	
	public void importCountriesData() throws IOException
	{
		String data = "";
		int count = 0;
		CountriesLL previous = new CountriesLL("","","",0,0,0.0,0);
			try {
				BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Justin\\workspace\\HW2\\Countries_Data.txt"));
				while ((data = br.readLine()) != null) { // makes data equal to the next line and checks if it is null
					
					String[] parts = data.split(","); // splits the read in line into an array
					Countries country = new Countries(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]));
					countryArray[count] = country;
					count++; // implements countries as an array
					
					CountriesLL current = new CountriesLL(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]));
					
					if(countryLL == null)
					{
						countryLL = current;
						previous = current;
					}
					else
					{
						previous.next = current;
						previous = current;
					} // implements countries as a linked list
				}
				br.close();
//				for(int i = 0;i<countryArray.length; i++)  I used this for testing
//				{
//				System.out.println(countryArray[i].countryName);
//				System.out.println(countryArray[i].latitude);
//				System.out.println(countryArray[i].longitude);
//				System.out.println(countryArray[i].countryArea);
//				System.out.println(countryArray[i].countryPopulation);
//				System.out.println(countryArray[i].countryGDP);
//				System.out.println(countryArray[i].countryYear);
//				}
				
//				System.out.println(countryLL.countryName);
//				System.out.println(countryLL.next.countryName);
//				System.out.println(countryLL.next.next.countryName);
//				System.out.println(countryLL.next.next.next.countryName);
//				System.out.println(countryLL.next.next.next.next.countryName);
//				System.out.println(countryLL.next.next.next.next.next.countryName);
//				System.out.println(countryLL.next.next.next.next.next.next.countryName);
//				System.out.println(countryLL.next.next.next.next.next.next.next.countryName);
//				System.out.println(countryLL.next.next.next.next.next.next.next.next.countryName);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public String[] getCountriesWithPopulation(int pop)
	{
		String countries = "";
		for(int i = 0; i < countryArray.length; i++) // loops through the country array
		{
			if(countryArray[i].countryPopulation > pop) // checks if the countries population is greater than the given number
				countries += countryArray[i].countryName + ","; // adds the countries name to the string
		}
		if(countries.equals("")) 
			return new String[0]; // returns a 0 length array if no countries were added
		return countries.split(","); // returns an array with the countries names 
	}
	
	public String[] getCountriesWithPopulationLL(int pop)
	{
		String countries = "";
		CountriesLL current = countryLL; // set current to the first item in the linked list
		
		while(current != null) // go until the linked list is done
		{
			if(current.countryPopulation > pop) // checks if the countries population is greater than the given number
				countries += current.countryName + ","; // adds the countries name to the string
			current = current.next; // makes the next country to check equal to the current one
		}
		
		if(countries.equals(""))
			return new String[0];// returns a 0 length array if no countries were added
		return countries.split(","); // returns an array with the countries names 
	}
	
	public String[] getCommonCountries(String[] one, String[] two)
	{
		String countries = "";

			for(int i = 0; i < one.length; i++)
			{
				for(int j = 0; j < two.length; j++)
				{
					if(one[i].equals(two[j]))
						countries += one[i] + ","; // goes through to see if the countries name is in both lists and adds the countries name to the string
				}
			}
			
		if(countries.equals(""))
			return new String[0]; // returns a 0 length array if no names were added to the string
		return countries.split(","); // returns an array of the countries names otherwise
	}
	
	public void displayImportData(Borders border, HW2 thing) throws IOException
	{
		System.out.println("Importing Data"); 
		border.importBordersData(); //import data
		thing.importCountriesData(); //import data
	}
	
	public void displayBorderingCountries(Borders border)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Would you like to see a list of countries that border a country? (y/n)"); // display bordering countries
		String input = keyboard.nextLine();
		if(input.equals("y") || input.equals("Y")) //check if the user wants to do this
		{
			System.out.print("What country would you like to see the bordering countries for? (first letter capitalized please) ");
			input = keyboard.nextLine();
			String[] countries = border.getCountriesThatBorder(input); // calls method that returns the bordering countries of the country given
			if(countries.length == 0) //makes sure there actually are countries in the list
				System.out.print("No countries border " + input + "\n");
			else
			{
				for(int j = 0; j< countries.length; j++)
					System.out.println(countries[j]); // prints the countries
			}
				
		}
	}
	
	public void displayPopulationCountries(HW2 thing)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Would you like to see a list of countries that have a population greater than a given number? (y/n)"); // display countries with a population greater than what they give
		String input = keyboard.nextLine();
		if(input.equals("y") || input.equals("Y")) //check if the user wants to do this
		{
			System.out.print("What number would you like to make the population? ");
			int pop = keyboard.nextInt();
			System.out.println("(Array implementation)"); // array implementation of getting the countries with a population greater than the given number
			String[] countries = thing.getCountriesWithPopulation(pop);
			if(countries.length == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + "\n");
			else
			{
				for(int j = 0; j< countries.length; j++)
					System.out.println(countries[j]); // prints the countries
			}
			System.out.println("(Linked List implementation)"); // linked list implementation of getting the countries with a population less than the given number
			String[] countriesLL = thing.getCountriesWithPopulationLL(pop);
			if(countriesLL.length == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + "\n");
			else
			{
				for(int j = 0; j< countriesLL.length; j++)
					System.out.println(countriesLL[j]); // prints the countries
			}
		}
	}
	
	public void displayPopulationAndBorderingCountries(Borders border, HW2 thing)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Would you like to see a list of countries that border a certain country and have a population greater than a given number? (y/n)"); // display bordering countries
		String input = keyboard.nextLine();
		if(input.equals("y") || input.equals("Y")) //check if the user wants to do this
		{
			System.out.print("What country would you like to see the bordering countries for? (first letter capitalized please) ");
			input = keyboard.nextLine();
			System.out.print("What number would you like to make the population? ");
			int pop = keyboard.nextInt();
			System.out.println("(Array implementation)"); // array implementation of getting the countries with a population greater than the given number
			String[] countries = thing.getCommonCountries(thing.getCountriesWithPopulation(pop),border.getCountriesThatBorder(input));
			if(countries.length == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + " and border " + input + "\n");
			else
			{
				for(int j = 0; j< countries.length; j++)
					System.out.println(countries[j]); // prints the countries
			}
			
			System.out.println("(Linked List implementation)"); // linked list implementation of getting the countries with a population less than the given number
			String[] countriesLL = thing.getCommonCountries(thing.getCountriesWithPopulationLL(pop),border.getCountriesThatBorder(input));
			if(countriesLL.length == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + " and border " + input + "\n");
			else
			{
				for(int j = 0; j< countriesLL.length; j++)
					System.out.println(countriesLL[j]); // prints the countries
			}
				
		}
	}
	
	public void quit()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Would you like to quit the program? (y/n)");
		String input = keyboard.nextLine();
		if(input.equals("y") || input.equals("Y")) // checks if the user wants to quit or not
			done = true; // changes the boolean so the loop ends
	}
	

}

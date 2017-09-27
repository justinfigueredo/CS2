//HW3 Countries
//Justin Figueredo
//9/14/2017
//This program takes in Countries and Borders data and then makes and ArrayList and LinkedList of Countries objects.
//This program will display countries that border a given country you give it or display countries with a population over the number you give it.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class HW3 {
	
public ArrayList<Countries> countryList = new ArrayList<Countries>();
public LinkedList<CountriesLL> countryLL;
public boolean done = false;
public String[][] borders = new String[16][2];

	public static void main(String[] args) throws IOException {
		try
		{
	HW3 thing = new HW3();	
	Borders border = new Borders();
	Scanner keyboard = new Scanner(System.in); // using scanner for user interface
	// gave each method an instance of border and/or thing because i'm not sure if we are allowed to use static
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
			try {
				BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Justin\\workspace\\HW3\\Countries_Data.txt"));
				while ((data = br.readLine()) != null) { // makes data equal to the next line and checks if it is null
					
					String[] parts = data.split(","); // splits the read in line into an array
					Countries country = new Countries(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]));
					countryList.add(country);// implements countries as an ArrayList
					
					CountriesLL current = new CountriesLL(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]));
					
					if(countryLL == null)
					{
						countryLL = new LinkedList<CountriesLL>();
						countryLL.add(current);
					}
					else
					{
						countryLL.add(current);
					} // implements countries as a linked list
				}
				br.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	public void importBordersData() throws IOException
	{
		String data = "";
		int count = 0;
			try {
				BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Justin\\workspace\\HW2\\Borders.txt"));
				while ((data = br.readLine()) != null) { // makes data equal to the next line and checks if it is null
					String[] parts = data.split(","); // splits the read in line into an array
					borders[count][0] = parts[0]; //
					borders[count][1] = parts[1]; //fills the 2d array
					count++;
				}
				br.close();
				
//				for(int i = 0; i< borders.length; i++)   I used this to test
//				{
//					System.out.println(borders[i][0] + " " + borders[i][1]);
//				}
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
	}
	
	public ArrayList<String> getCountriesWithPopulation(int pop)
	{
		ArrayList<String> countries = new ArrayList<String>();
		for(Countries country : countryList) // loops through the country ArrayList
		{
			if(country.countryPopulation > pop) // checks if the countries population is greater than the given number
				countries.add(country.countryName); // adds the countries name to the ArrayList
		}
		return countries; // returns an ArrayList with the countries names 
	}
	
	public ArrayList<String> getCountriesWithPopulationLL(int pop)
	{
		ArrayList<String> countries = new ArrayList<String>();		
		for(CountriesLL current : countryLL) // loops through the country ArrayList
		{
			if(current.countryPopulation > pop) // checks if the countries population is greater than the given number
				countries.add(current.countryName); // adds the countries name to the ArrayList
		}
		return countries; // returns an ArrayList with the countries names 
	}
	
	public ArrayList<String> getCommonCountries(ArrayList<String> one, ArrayList<String> two)
	{
		ArrayList<String> countries = new ArrayList<String>();

			for(String country1 : one)
			{
				for(String country2 : two)
				{
					if(country1.equals(country2))
						countries.add(country1); // goes through to see if the countries name is in both lists and adds the countries name to the string
				}
			}
		return countries; // returns an array of the countries names otherwise
	}
	
	public void displayImportData(Borders border, HW3 thing) throws IOException
	{
		System.out.println("Importing Data"); 
		thing.importBordersData(); //import data
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
			ArrayList<String> countries = border.getCountriesThatBorder(input, borders); // calls method that returns the bordering countries of the country given
			if(countries.size() == 0) //makes sure there actually are countries in the list
				System.out.print("No countries border " + input + "\n");
			else
			{
				for(String country : countries)
					System.out.println(country); // prints the countries
			}
				
		}
	}
	
	public void displayPopulationCountries(HW3 thing)
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Would you like to see a list of countries that have a population greater than a given number? (y/n)"); // display countries with a population greater than what they give
		String input = keyboard.nextLine();
		if(input.equals("y") || input.equals("Y")) //check if the user wants to do this
		{
			System.out.print("What number would you like to make the population? ");
			int pop = keyboard.nextInt();
			System.out.println("(ArrayList implementation)"); // array implementation of getting the countries with a population greater than the given number
			ArrayList<String> countries = thing.getCountriesWithPopulation(pop);
			if(countries.size() == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + "\n");
			else
			{
				for(String country : countries)
					System.out.println(country); // prints the countries
			}
			System.out.println("(Linked List implementation)"); // linked list implementation of getting the countries with a population less than the given number
			ArrayList<String> countriesLL = thing.getCountriesWithPopulationLL(pop);
			if(countriesLL.size() == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + "\n");
			else
			{
				for(String country : countriesLL)
					System.out.println(country); // prints the countries
			}
		}
	}
	
	public void displayPopulationAndBorderingCountries(Borders border, HW3 thing)
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
			System.out.println("(ArrayList implementation)"); // array implementation of getting the countries with a population greater than the given number
			ArrayList<String> countries = thing.getCommonCountries(thing.getCountriesWithPopulation(pop),border.getCountriesThatBorder(input, borders));
			if(countries.size() == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + " and border " + input + "\n");
			else
			{
				for(String country : countries)
					System.out.println(country); // prints the countries
			}
			
			System.out.println("(Linked List implementation)"); // linked list implementation of getting the countries with a population less than the given number
			ArrayList<String> countriesLL = thing.getCommonCountries(thing.getCountriesWithPopulationLL(pop),border.getCountriesThatBorder(input, borders));
			if(countriesLL.size() == 0) //makes sure there actually are countries in the list
				System.out.print("No countries have a population greater than " + pop + " and border " + input + "\n");
			else
			{
				for(String country : countries)
					System.out.println(country); // prints the countries
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

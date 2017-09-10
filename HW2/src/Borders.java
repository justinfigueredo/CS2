import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Borders {
	
public String[][] borders = new String[16][2];

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String[] getCountriesThatBorder(String country)
	{
		int count = 0;
		for(int i = 0; i< borders.length; i++) //gets the number of countries that border the given country
		{
			if(borders[i][0].equals(country) || borders[i][1].equals(country)) 
				count++;
		}
		//System.out.print(count);
		
		String[] borderingCountries = new String[count]; // makes an array that contains the right number of countries that border the given country
		int spot = 0;
		for(int i = 0; i< borders.length; i++) //puts the bordering country in the new array
		{
			if(borders[i][0].equals(country))
			{
				borderingCountries[spot] = borders[i][1]; // puts in the bordering country to the country given
				spot++; // increments it only if a country is put in
			}
			
			if(borders[i][1].equals(country))
			{
				borderingCountries[spot] = borders[i][0]; // puts in the bordering country to the country given
				spot++; // increments it only if a country is put in
			}
		}
		
		return borderingCountries;
	}
	
	
}

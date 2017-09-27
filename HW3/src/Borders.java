
import java.util.ArrayList;

public class Borders {
	
	public Borders()
	{
	
	}
	
	public ArrayList<String> getCountriesThatBorder(String country, String [][] borders)
	{

		ArrayList<String> borderingCountries = new ArrayList<String>();
		
		for(int i = 0; i< borders.length; i++) //puts the bordering country in the new array
		{
			if(borders[i][0].equals(country))
			{
				borderingCountries.add(borders[i][1]); // puts in the bordering country to the country given
			}
			
			if(borders[i][1].equals(country))
			{
				borderingCountries.add(borders[i][0]); // puts in the bordering country to the country given
			}
		}
		
		return borderingCountries;
	}
	
	
}

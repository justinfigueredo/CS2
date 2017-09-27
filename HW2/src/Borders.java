
public class Borders {
	
	public Borders()
	{
		
	}

	public String[] getCountriesThatBorder(String country, String [][] borders)
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

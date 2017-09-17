import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
				
				e.printStackTrace();
			}
	}
	
	public ArrayList<String> getCountriesThatBorder(String country)
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

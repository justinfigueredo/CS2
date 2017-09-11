
public class CountriesLL {
	
	String countryName = "";
	String latitude = "";
	String longitude = "";
	int countryArea = 0;
	int countryPopulation = 0;
	double countryGDP = 0.0; // in billions
	int countryYear = 0;
	CountriesLL next;

	public CountriesLL(String name, String lat, String lon, int area, int pop, double gdp, int year)
	{
		countryName = name;
		latitude = lat;
		longitude = lon;
		countryArea = area;
		countryPopulation = pop;
		countryGDP = gdp;
		countryYear = year;
	}
}

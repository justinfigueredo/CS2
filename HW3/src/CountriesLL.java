
public class CountriesLL extends Countries{
	
	CountriesLL next;
	
	public CountriesLL(String name, String lat, String lon, int area, int pop, double gdp, int year)
	{
		super.countryName = name;
		super.latitude = lat;
		super.longitude = lon;
		super.countryArea = area;
		super.countryPopulation = pop;
		super.countryGDP = gdp;
		super.countryYear = year;
	}
}

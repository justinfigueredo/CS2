
public class Countries {
	
String countryName = "";
String latitude = "";
String longitude = "";
int countryArea = 0;
int countryPopulation = 0;
double countryGDP = 0.0; // in billions
int countryYear = 0;

public Countries(){}

public Countries(String name, String lat, String lon, int area, int pop, double gdp, int year)
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

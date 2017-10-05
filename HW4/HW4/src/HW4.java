import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class HW4 {

	public static void main(String[] args)  {
		
		HW4 hw4 = new HW4();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("File path: ");
		String path = keyboard.nextLine();
		keyboard.close();
		
		ArrayList<String> list = hw4.importData(path);
		ArrayList<String> list2 = hw4.importData(path);
		//list2.addAll(list);
		ArrayList<String> list3 = hw4.importData(path);
		//list2.addAll(list);
		ArrayList<String> list4 = hw4.importData(path);
		//list2.addAll(list);
		System.out.println("Number of Words: " + list.size());
//		for(int i = 0; i<150; i++)
//		{
//			System.out.println(list.get(i));
//		}
		Sorter sorter = new Sorter();
		
		LocalDateTime javaSortStart = LocalDateTime.now();
				sorter.javaSort(list);
		LocalDateTime javaSortEnd = LocalDateTime.now();
		Duration javaElapsed = Duration.between(javaSortStart.toLocalTime(), javaSortEnd.toLocalTime());
		
		LocalDateTime bubbleSortStart = LocalDateTime.now();
				sorter.bubbleSort(list2);
				LocalDateTime bubbleSortEnd = LocalDateTime.now();
				Duration bubbleElapsed = Duration.between(bubbleSortStart, bubbleSortEnd);
				
				System.out.println("Wall Clock:");
				System.out.println("	Internal Sort: " + javaElapsed.toMinutes() + " Minutes " + javaElapsed.getSeconds() % 60 + " Seconds " + javaElapsed.toMillis() % 1000 + " Milliseconds");
				System.out.println("	Bubble Sort: " + bubbleElapsed.toMinutes() + " Minutes " + bubbleElapsed.getSeconds() %60 + " Seconds " + bubbleElapsed.toMillis() % 1000 + " Milliseconds");
				
				long javaStartTime = System.nanoTime();
				sorter.javaSort(list3);
				 long javaEstimatedTime = System.nanoTime() - javaStartTime;
				 
				 long bubbleStartTime = System.nanoTime();
					sorter.bubbleSort(list4);
					 long bubbleEstimatedTime = System.nanoTime() - bubbleStartTime;
					 
				System.out.println("");
				System.out.println("CPU Time:");
				System.out.println("	Internal Sort: " + javaEstimatedTime);
				System.out.println("	Bubble Sort: " + bubbleEstimatedTime);
				
	}
	
	public ArrayList<String> importData(String filePath)
	{
		ArrayList<String> stringList = new ArrayList<String>();
		String data = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((data = br.readLine()) != null) { // makes data equal to the next line and checks if it is null
				
				String[] parts = data.split(" "); // splits the read in line into an array
				for(String part : parts)
				{
					if(part.contains("-") && !part.equals("-"))
					{
					String first = part.substring(0, part.indexOf("-"));
					String second = part.substring(part.indexOf("-"), part.length());
					first = first.replaceAll("[,_`.!@#$%^&*()-/?'^\"|\"$\\[\\]\\:;<>]", "").toLowerCase();
					second = second.replaceAll("[,_`.!@#$%^&*()-/?'^\"|\"$\\[\\]\\:;<>]", "").toLowerCase();
					if(!first.equals(""))
					stringList.add(first);
					
					if(!second.equals(""))
					stringList.add(second);
					}
					else
					{	
					part = part.replaceAll("[,_`.!@#$%^&*()-/?'^\"|\"$\\[\\]\\:;<>]", "").toLowerCase();
					if(!part.equals(""))
					stringList.add(part);
					}
				}
				}
			br.close();
			}
			catch(Exception e){}
		return stringList;
	}
	
	

}

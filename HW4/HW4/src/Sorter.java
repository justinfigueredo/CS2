import java.util.ArrayList;
import java.util.Collections;

public class Sorter {

	public static int compare(String one, String two)
	{
		int before = -1;
		int equal = 0;
		int after = 1;
		int iterations = Math.max(one.length(), two.length());

		if(one.equals(two))
			return equal;
		for(int i = 0; i < iterations; i++)
		{
			if(i >= one.length())
				return before;
			if(i >= two.length())
				return after;
			
			Character oneChar = one.charAt(i);
			Character twoChar = two.charAt(i);
			if((int)oneChar < (int)twoChar)
				return before;
			
			if((int)twoChar < (int)oneChar)
				return after;
			
		}
		return equal;
	}
	
	public ArrayList<String> bubbleSort(ArrayList<String> list)
	{
        for (int i = 0; i < list.size()-1; i++)
            for (int j = 0; j < list.size()-i-1; j++)
                if (Sorter.compare(list.get(j), list.get(j+1)) > 0)
                {
                    String temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
		return list;
	}
	
	public ArrayList<String> javaSort(ArrayList<String> list)
	{
		Collections.sort(list.subList(0, list.size()));
		return list;
	}
	
}

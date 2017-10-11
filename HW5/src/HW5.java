import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HW5 {

	public static void main(String[] args)  {
		
		HW5 hw5 = new HW5();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("File path: ");
		String path = keyboard.nextLine();
		keyboard.close();
		
		ArrayList<String> list = hw5.importData(path);
		LinkedList<UniqueWord> linkedList = hw5.importLinkedListData(path, hw5);

		Map<String, Integer> wordCount = hw5.getCounts(list);
		System.out.println("Number of Words: " + wordCount.size());
		
		ArrayList<String> keys = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : wordCount.entrySet())
		{
			keys.add(entry.getKey());
		}
		
		Sorter sorter = new Sorter();
		sorter.javaSort(list);
		sorter.javaSort(keys);
		
		hw5.writeFile(keys, wordCount);
		hw5.writeFile(linkedList);
//		for(int i = 0; i<150; i++)
//		{
//			System.out.println(list.get(i));
//		}
		
				
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
	
	public LinkedList<UniqueWord> importLinkedListData(String filePath, HW5 hw5)
	{
		LinkedList<UniqueWord> stringList = new LinkedList<UniqueWord>();
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
							if(!hw5.doesContain(stringList, first))
								hw5.addInOrder(stringList, first);
							else
								stringList.get(hw5.indexOfWord(stringList, first)).count++;


						if(!second.equals(""))
							if(!hw5.doesContain(stringList, second))
								hw5.addInOrder(stringList, second);
							else
								stringList.get(hw5.indexOfWord(stringList, second)).count++;

					}
					else
					{	
						part = part.replaceAll("[,_`.!@#$%^&*()-/?'^\"|\"$\\[\\]\\:;<>]", "").toLowerCase();
						if(!part.equals(""))
							if(!hw5.doesContain(stringList, part))
								hw5.addInOrder(stringList, part);
							else
								stringList.get(hw5.indexOfWord(stringList, part)).count++;
					}
				}
			}
			br.close();
		}
		catch(Exception e){System.out.println(e.getMessage());}
		return stringList;
	}
	
	public int indexOfWord(LinkedList<UniqueWord> list, String word)
	{
		for(UniqueWord uniqueWord : list)
			if(uniqueWord.word.equals(word))
				return list.indexOf(uniqueWord);
		return -1;
	}
	
	public boolean doesContain(LinkedList<UniqueWord> list, String word)
	{
		for(UniqueWord uniqueWord : list)
			if(uniqueWord.word.equals(word))
				return true;
		return false;
	}
	
	public void addInOrder(LinkedList<UniqueWord> list, String word)
	{
		int count = 0;
		boolean done = false;
		if(list.size() == 0)
			list.add(count, new UniqueWord(word));

		for(UniqueWord uniqueWord : list)
		{
		String one = word;
		String two = uniqueWord.word;
		int iterations = Math.max(one.length(), two.length());

		for(int i = 0; i < iterations; i++)
		{
			if(i >= one.length())
			{
				done = true; break;
			}
			if(i >= two.length())
			{
				done = true; count += 1; break;
			}
			
			Character oneChar = one.charAt(i);
			Character twoChar = two.charAt(i);
			if((int)oneChar < (int)twoChar)
			{
				done = true; break;
			}
			
			if((int)twoChar < (int)oneChar)
			{
				done = true; count += 1; break;
			}
			
		}
		if(done)
			break;
		count++;
		}
		list.add(count, new UniqueWord(word));
	}
	
	public void writeFile(ArrayList<String> keys, Map<String, Integer> wordCount)
	{
		try{
			//String output = "";
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Justin\\Desktop\\CS2 Projects\\HW5\\arrayList.txt"));
		
		for(String key : keys)
		bw.append(String.format("%-20s%-10s", key.toString(), wordCount.get(key).toString()) + " \n");
		
		bw.write(bw.toString());
		bw.flush();
		bw.close();
		}
		catch(IOException e){}
	}
	
	public void writeFile(LinkedList<UniqueWord> keys)
	{
		try{
			//String output = "";
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Justin\\Desktop\\CS2 Projects\\HW5\\linkedList.txt"));
		
		for(int i = 0; i<keys.size(); i++)
		bw.append(String.format("%-20s%-10s", keys.get(i).word.toString(), keys.get(i).count) + " \n");
		
		bw.write(bw.toString());
		bw.flush();
		bw.close();
		}
		catch(IOException e){}
	}
	
	public Map<String, Integer> getCounts(ArrayList<String> list)
	{
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		
		for(String word : list)
		{
			if(wordMap.containsKey(word))
			{
				wordMap.put(word, wordMap.get(word) + 1);
			}
			else
			{
				wordMap.put(word, 1);
			}
		}
		
		return wordMap;
	}
	
	

}

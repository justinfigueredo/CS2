// Justin Figueredo
// Homework 6
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HW6 {

	public long javaStartTime;
	public long javaSortTime;
	public long mergeStartTime;
	public long mergeSortTime;
	public StringBuffer output = new StringBuffer();

	
	public static void main(String[] args) {
		
		HW6 hw6 = new HW6();
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("File path to print tests to: ");
		String path = keyboard.nextLine();
		hw6.integerTest();
		hw6.doubleTest();
		hw6.stringTest();
		hw6.printToFile(path);
		
		System.out.print("Do you want to sort a list of Integers?(y/n) ");
		String answer = keyboard.nextLine();
		if(answer.equals("y") || answer.equals("Y"))
		{
		hw6.doIntSort(keyboard); 
		System.out.println("");
		}
		
		//System.out.println("");
	
		System.out.print("Do you want to sort a list of Reals?(y/n) ");
		keyboard.nextLine();
		answer = keyboard.nextLine();
		if(answer.equals("y") || answer.equals("Y"))
		{
		hw6.doDoubleSort(keyboard);
		System.out.println("");
		}
	
		System.out.print("Do you want to sort a list of words?(y/n) ");
		keyboard.nextLine();
		answer = keyboard.nextLine();
		if(answer.equals("y") || answer.equals("Y"))
		{
		hw6.doStringSort(keyboard);
		}
		
	}
	
	public void doIntSort(Scanner keyboard)
	{
		System.out.print("How many random numbers do you want? ");
		int size = keyboard.nextInt();
		System.out.print("What is the biggest number you might want? ");
		int big = keyboard.nextInt();
		Integer[] mixedArray = this.makeArray(size, big);
		
		for(int num : mixedArray)
			System.out.print(num + " ");
		
		System.out.print("\n");
		
		Mergesort.sort(mixedArray);
		
		for(int num : mixedArray)
			System.out.print(num + " ");
	}
	
	public void doDoubleSort(Scanner keyboard)
	{
		System.out.print("How many random numbers do you want? ");
		double size = keyboard.nextDouble();
		System.out.print("What is the biggest number you might want? ");
		double big = keyboard.nextDouble();
		Double[] mixedArray = this.makeArray(size, big);
		
		for(double num : mixedArray)
		{
			System.out.printf("%.2f", num); 
			System.out.print(" ");
		}
		
		System.out.println("");
		
		Mergesort.sort(mixedArray);
		
		for(double num : mixedArray)
		{
			System.out.printf("%.2f", num); 
			System.out.print(" ");
		}
	}
	
	public void doStringSort(Scanner keyboard)
	{
		System.out.print("How many random words do you want? ");
		int size = keyboard.nextInt();
		
		String[] mixedArray = this.makeArray(size);
		
		for(String word : mixedArray)
		{
			System.out.print(word + " "); 
		}
		
		System.out.println("");
		
		Mergesort.sort(mixedArray);
	
		for(String word : mixedArray)
		{
			System.out.print(word + " "); 
		}
	}
	
	public void printToFile(String path)
	{
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(path));
				bw.write(output.toString());
				
				//flush the stream
				bw.flush();
		        
		        //close the stream
		        bw.close();
			}
			catch(IOException e){}
	 }
	
	public void integerTest()
	{	
		this.output.append("Integer Number Tests:\n\n10 numbers:\n");
		Integer[] mixedArray = this.makeArray(10, 100);
		ArrayList<Integer> mixedArrayCopy = new ArrayList<Integer>();
		
		for(Integer num : mixedArray)
		{
			mixedArrayCopy.add(num);
		}
		
		for(Integer num : mixedArray)
		{
			this.output.append(num + " "); 
		}
		
		this.output.append("\n");

		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
	
		for(Integer num : mixedArray)
		{
			this.output.append(num + " "); 
		}
		
		this.appendTimes();
		this.output.append("\n");

		
		Integer[] mixedArray2 = this.makeArray(50, 500);
		ArrayList<Integer> mixedArrayCopy2 = new ArrayList<Integer>();
		
		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray2);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy2);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
		
		this.output.append("50 numbers:");
		this.appendTimes();
		this.output.append("\n");

		
		Integer[] mixedArray3 = this.makeArray(200, 2000);
		ArrayList<Integer> mixedArrayCopy3 = new ArrayList<Integer>();
		
		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray3);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy3);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
		
		this.output.append("200 numbers:");
		this.appendTimes();
		this.output.append("\n");

		
	}
	
	public void doubleTest()
	{	
		this.output.append("Real Number Tests:\n\n10 numbers:\n");
		Double[] mixedArray = this.makeArray(10.0, 100.0);
		ArrayList<Double> mixedArrayCopy = new ArrayList<Double>();
		
		for(Double num : mixedArray)
		{
			mixedArrayCopy.add(num);
		}
		
		for(Double num : mixedArray)
		{
			this.output.append(String.format("%.2f", num) + " "); 
		}
		
		this.output.append("\n");

		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
	
		for(Double num : mixedArray)
		{
			this.output.append(String.format("%.2f", num) + " "); 
		}
		
		this.appendTimes();
		this.output.append("\n");

		
		Double[] mixedArray2 = this.makeArray(50.0, 500.0);
		ArrayList<Double> mixedArrayCopy2 = new ArrayList<Double>();
		
		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray2);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy2);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
		
		this.output.append("50 numbers:");
		this.appendTimes();
		this.output.append("\n");

		
		Double[] mixedArray3 = this.makeArray(200.0, 2000.0);
		ArrayList<Double> mixedArrayCopy3 = new ArrayList<Double>();
		
		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray3);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy3);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
		
		this.output.append("200 numbers:");
		this.appendTimes();
		this.output.append("\n");

		
	}
	
	public void stringTest()
	{	
		this.output.append("String Tests:\n\n10 strings:\n");
		String[] mixedArray = this.makeArray(10);
		ArrayList<String> mixedArrayCopy = new ArrayList<String>();
		
		for(String word : mixedArray)
		{
			mixedArrayCopy.add(word);
		}
		
		for(String word : mixedArray)
		{
			this.output.append(word + " "); 
		}
		
		this.output.append("\n");

		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
	
		for(String word : mixedArray)
		{
			this.output.append(word + " "); 
		}
		
		this.appendTimes();
		this.output.append("\n");
		
		String[] mixedArray2 = this.makeArray(50);
		ArrayList<String> mixedArrayCopy2 = new ArrayList<String>();
		
		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray2);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy2);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
		
		this.output.append("50 strings:");
		this.appendTimes();
		this.output.append("\n");
		
		String[] mixedArray3 = this.makeArray(200);
		ArrayList<String> mixedArrayCopy3 = new ArrayList<String>();
		
		this.mergeStartTime = System.nanoTime();
		Mergesort.sort(mixedArray3);
		this.mergeSortTime = System.nanoTime() - this.javaStartTime;
		
		this.javaStartTime = System.nanoTime();
		Collections.sort(mixedArrayCopy3);
		this.javaSortTime = System.nanoTime() - this.javaStartTime;
		
		this.output.append("200 strings:");
		this.appendTimes();
		this.output.append("\n");
		
	}
	
	public void appendTimes()
	{
		this.output.append("\nMergesort took: " + this.mergeSortTime);
		this.output.append("\n");
		this.output.append("Java sort took: " + this.javaSortTime);
		this.output.append("\n");

	}
	
	public void printTimes()
	{
		System.out.println("Mergesort took: " + this.mergeSortTime);
		System.out.println("Java sort took: " + this.javaSortTime);
	}
	
	public Integer[] makeArray(int size, int big)
	{
		Integer[] array = new Integer[size];
		for(int i = 0; i < size; i++)
		array[i] = (int )(Math.random() * big + 1);
		return array;
	}
	
	public Double[] makeArray(double size, double big)
	{
		Double[] array = new Double[(int)size];
		for(int i = 0; i < size; i++)
		array[i] = (Math.random() * big + 1);
		return array;
	}
	
	public String[] makeArray(int size)
	{
		String[] array = new String[size];
		for(int i = 0; i < size; i++)
		{
			String word = "";
			int count = 0;
			int ascii = 0;
			while(count < 6)
			{
				ascii = (int )(Math.random() * 123 + 1);
				if(ascii > 47 && ascii < 58)
				{
					word += (char)ascii;
					count++;
				}
				if(ascii > 64 && ascii < 91)
				{
					word += (char)ascii;
					count++;
				}
				if(ascii > 96 && ascii < 123)
				{
					word += (char)ascii;
					count++;
				}
			}
			array[i] = word;
		}
		return array;
	}

}

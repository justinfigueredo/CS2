//User Login
//Justin Figueredo
//9/5/2017
//This program takes in a user login and determines whether it meets some requirements or not.
//The user login will be printed out in the console and to a separate file with its validity and the requirements not met if applicable. 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class UserLogin {
	
	public String loginFromUser = "";
	public String userLoginReport = "";
	public boolean hasUpperCase = false;
	public boolean hasLowerCase = false;
	public boolean hasSymbol = false;
	public boolean isLongEnough = false;
	public boolean hasDigit = false;
	public boolean hasInvalidCharacter = false;
	public boolean valid = false;
	public boolean done = false;
	public StringBuffer buffer = new StringBuffer(200);
	
	public static void main(String[] args){
		
		try
		{
			UserLogin userLogin = new UserLogin();
			userLogin.buffer.append("Project #1 - User Login \nJustin Figueredo\n\n"); //starts the stringBuffer with the header
			userLogin.greetUser();
			
			while(!userLogin.done) //loops until there are no more user logins
			{
			userLogin.readUser();
			userLogin.checkCase();
			userLogin.checkSymbol();
			userLogin.checkDigit();
			userLogin.checkInvalidCharacters();
			userLogin.checkLength();
			userLogin.checkValidty();
			userLogin.printUser();
			userLogin.addToReport();
			userLogin.goAgain();
			userLogin.hasUpperCase = false;//reset booleans after each user login
			userLogin.hasLowerCase = false;
			userLogin.hasSymbol = false;
			userLogin.isLongEnough = false;
			userLogin.hasDigit = false;
			userLogin.hasInvalidCharacter = false;
			userLogin.valid = false;
			}
			userLogin.printReport();
		}
		catch(IOException e){}
	}
	
	// determines whether the loop should end or not based on user input
	public void goAgain()
	{
		try
		{
			String input = "";
			System.out.println("");
			System.out.print("Do you have another login? (y/n)");
			InputStreamReader isr = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(isr);
	        input = br.readLine().toString();
	        if(!(input.equals("y")))
	        	done = true;
		}
		catch(IOException e){}
	}

	// tells the user what this program does at the start
	public void greetUser()
	{
		System.out.print("This program takes in a user login and determines whether it meets some requirements or not. \nThe user login will be printed out in the console and to a separate file with its validity and the requirements not met if applicable.");
	}
	
	// reads in the users login from the console
	public void readUser()
	{
		try
		{
			System.out.println("");
			System.out.print("Login: ");
			InputStreamReader isr = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(isr);
	        loginFromUser = br.readLine().toString();
		}
		catch(IOException e){}
	}
	
	// determines whether or not the user login contains at least one upper case letter and one lower case letter 
	public void checkCase()
	{
	 for(int i = 0; i < loginFromUser.length(); i++)
	 {
		char c = loginFromUser.charAt(i);
		if(Character.isUpperCase(c))
		{
			hasUpperCase = true;
		}
		
		if(Character.isLowerCase(c))
		{
			hasLowerCase = true;
		}
	  }
	}
	
	// determines whether or not the user login contains one of the following symbols: !,@,#, or $ 
	public void checkSymbol()
	{
		if(loginFromUser.contains("!") || loginFromUser.contains("@") || loginFromUser.contains("#") || loginFromUser.contains("$"))
			hasSymbol = true;
	}
	
	// determines whether or not the user login contains at least one digit
	public void checkDigit()
	{
		for(int i =0; i < loginFromUser.length(); i++)
		{
			char c = loginFromUser.charAt(i);
			if(Character.isDigit(c))
			{
				hasDigit = true;
			}
		}
	}
	
	// determines whether or not the user login contains a space or tab
	public void checkInvalidCharacters()
	{
		if(loginFromUser.contains(" ") || loginFromUser.contains("	"))
			hasInvalidCharacter = true;
	}
	
	// determines whether or not the user login is 5 or more characters long
	public void checkLength()
	{
		if(loginFromUser.length() >= 5)
			isLongEnough = true;
	}
	
	// determines whether or not the user login is valid based on if all the requirements are met
	public void checkValidty()
	{
		if(hasUpperCase && hasLowerCase && hasSymbol && isLongEnough && hasDigit && !hasInvalidCharacter)
			valid = true;
	}
	
	// prints the feedback to the console for each user login
	public void printUser()
	{
		String temp = String.format("%-12s", loginFromUser);
		userLoginReport = "Login: " + temp;
		
		if(valid)
			userLoginReport += " (valid)";
		else
			userLoginReport += " (invalid)";
		
		if(!hasUpperCase)
			userLoginReport += "\n   -- no uppercase letter";
		
		if(!hasLowerCase)
			userLoginReport += "\n   -- no lowercase letter";
		
		if(!hasDigit)
			userLoginReport += "\n   -- no digit";
		
		if(hasInvalidCharacter)
			userLoginReport += "\n   -- invalid character";
		
		if(!hasSymbol)
			userLoginReport += "\n   -- no special character";
		
		if(!isLongEnough)
			userLoginReport += "\n   -- too short (minimum of 5 characters)";
	
		System.out.println(userLoginReport);
	}
	
	// adds the feedback for each user login to the buffer
	public void addToReport()
	{
		buffer.append(userLoginReport + "\n");
	}
	
	// prints the final report to a file with all the user login feedbacks
	public void printReport() throws IOException
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Justin\\workspace\\User Login\\report.txt"));
			bw.write(buffer.toString());
			
			//flush the stream
			bw.flush();
	        
	        //close the stream
	        bw.close();
		}
		catch(IOException e){}
	}
	
}



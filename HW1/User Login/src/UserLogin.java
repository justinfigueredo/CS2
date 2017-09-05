//User Login
//Justin Figueredo
//9/4/2017
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
	
	public static void main(String[] args)throws IOException {
		
		UserLogin userLogin = new UserLogin();
		userLogin.buffer.append("Project #1 - User Login \nJustin Figueredo\n\n");
		userLogin.greetUser();
		
		
		while(!userLogin.done)
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
		userLogin.hasUpperCase = false;
		userLogin.hasLowerCase = false;
		userLogin.hasSymbol = false;
		userLogin.isLongEnough = false;
		userLogin.hasDigit = false;
		userLogin.hasInvalidCharacter = false;
		userLogin.valid = false;
		}
		userLogin.printReport();
		
	}
	
	public void goAgain() throws IOException
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
	
	public void greetUser()
	{
		System.out.print("This program takes in a user login and determines whether it meets some requirements or not. \nThe user login will be printed out in the console and to a separate file with its validity and the requirements not met if applicable.");
	}
	
	public void readUser() throws IOException
	{
		System.out.println("");
		System.out.print("Login: ");
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        loginFromUser = br.readLine().toString();
	}
	
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
	
	public void checkSymbol()
	{
		if(loginFromUser.contains("!") || loginFromUser.contains("@") || loginFromUser.contains("#") || loginFromUser.contains("$"))
			hasSymbol = true;
	}
	
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
	
	public void checkInvalidCharacters()
	{
		if(loginFromUser.contains(" ") || loginFromUser.contains("	"))
			hasInvalidCharacter = true;
	}
	
	public void checkLength()
	{
		if(loginFromUser.length() >= 5)
			isLongEnough = true;
	}
	
	public void checkValidty()
	{
		if(hasUpperCase && hasLowerCase && hasSymbol && isLongEnough && hasDigit && !hasInvalidCharacter)
			valid = true;
	}
	
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
	
	public void addToReport()
	{
		buffer.append(userLoginReport + "\n");
	}
	
	public void printReport() throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Justin\\workspace\\User Login\\report.txt"));
		bw.write(buffer.toString());
		
		//flush the stream
		bw.flush();
        
        //close the stream
        bw.close();
	}
	
}



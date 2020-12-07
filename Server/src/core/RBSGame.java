package core;

import java.util.Random;
import java.util.Scanner;

public class RBSGame {
	private static String[] RBS = {"rock", "paper", "scizzors"};
	
    public static Boolean Choice(String c) {
    	if (c.equalsIgnoreCase("rock"))
    	{
    		return true;
    	}
    	else if (c.equalsIgnoreCase("paper"))
    	{
    		return true;
    	}
    	else if (c.equalsIgnoreCase("scizzors"))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public static void Decision(String c, String k)
    {
    	switch(c)
    	{
    		case "rock":
    			if (k.equalsIgnoreCase("paper"))
    			{
    				System.out.println("You lose");
    			}
    			else if(k.equalsIgnoreCase("scizzors"))
    			{
    				System.out.println("You Win");
    			}
    			else
    			{
    				System.out.println("You tied");
    			}
    			break;
    		case "paper":
    			if (k.equalsIgnoreCase("scizzors"))
    			{
    				System.out.println("You lose");
    			}
    			else if(k.equalsIgnoreCase("rock"))
    			{
    				System.out.println("You Win");
    			}
    			else
    			{
    				System.out.println("You tied");
    			}
    			break;
    		case "scizzors":
    			if (k.equalsIgnoreCase("rock"))
    			{
    				System.out.println("You lose");
    			}
    			else if(k.equalsIgnoreCase("paper"))
    			{
    				System.out.println("You Win");
    			}
    			else
    			{
    				System.out.println("You tied");
    			}
    			break;
    	}
    	
    }
    public static void main (String[] args) {
    	do {
    		Scanner scan = new Scanner(System.in);
    		System.out.println("Rock, Paper, Scizzors Go(Pick one): ");
    		String player = scan.next().toLowerCase();
    		if (Choice(player))
    		{
    			System.out.println("You choice " + player);
    		}
    		else
    		{
    			System.out.println(player + " is an Invalid choice");
    			break;
    		}
    		Random random = new Random();
    		int select = random.nextInt(RBS.length);
    		String bot = RBS[select];
    		System.out.println("Bot choice: " + bot);
    		Decision(player, bot);
    		System.out.println("Would you like to play again? (Y/N)");
    		String end = scan.next();
    		if (end.equalsIgnoreCase("Y"))
    		{
    			System.out.println("Restarting...");
    		}
    		else 
    		{
    			System.out.println("Ending game.");
    			break;
    		}

    		
    		
    	} while(true);
    	
    	
    	
    }

}

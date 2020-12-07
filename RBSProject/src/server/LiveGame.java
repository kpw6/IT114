package server;

public class LiveGame {
	
	//rock = 0
	//paper = 1
	//scizzors = 2
	//if return 1 player wins, if 0 other wins, 2 is a tie.
	
	public static int gameDecision(int one, int two)
	{
		if(one == 1 && two == 0)
		{
			return 1;
		}
		else if(one == 1 && two == 2)
		{
			return 0;
		}
		else if(one == 2 && two == 0)
		{
			return 0;
		}
		else if(one == 2 && two == 1)
		{
			return 1;
		}
		else if(one == 0 && two == 1)
		{
			return 0;
		}
		else if(one == 0 && two == 2)
		{
			return 1;
		}
		return 2;
		
	}
	public static String processResults(int decision) {
		switch (decision) {
		case 0:
			return "You have Lost!";
		case 1:
			return "You have Won!";
		case 2:
			return "You have tied!";
			
		}
		return "no decision was made";
	
		
	}
	

}

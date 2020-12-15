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
		else if(one == 0 && two== 2)
		{
			return 1;
		}
		else if(one == 3)
		{
			return 0;
		}
		else if(two == 3)
		{
			return 1;
		}
		return 2;
		
	}
	public static String processResults(int decision, String otherPlayer) {
		switch (decision) {
		case 0:
			return "has have Lost against " + otherPlayer + "!"  ;
		case 1:
			return "has have Won against " + otherPlayer + "!";
		case 2:
			return "has tied against " + otherPlayer + "!" ;
			
		}
		return "no decision was made";
	
		
	}
	

}

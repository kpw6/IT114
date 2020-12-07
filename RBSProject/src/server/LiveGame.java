package server;

public class LiveGame {
	
	int timeLimit = 20; //Player has countdown to decide
	//rock = 0
	//paper = 1
	//scizzors = 2
	//if return 1 player wins, if 0 other wins, 2 is a tie.
	
	public int gameLoop(int one, int two)
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

}

public class Recursion {
  
	public static int sum(int num) {
		if (num > 0) {
			return num + sum(num - 1);
		}
		return 0;
	}
	
	public static int loopSum(int num)
	{
		int sum = 0;
		for (int x = num; x > 0; x--)
		{
			sum += x;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(loopSum(55));
		System.out.println(sum(55));
	}
		
}
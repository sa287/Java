

public class Tail {

	public static int tailFactorial(int n,int res){
		if (n == 0)
			return res;
		else 
			return tailFactorial(n-1,n*res);
	}
	
	// optional part
	public static int tailSum(int n,int sum){
		return -1; // dummy value to be removed.
	}
		
	// optional part
	public static int tailMultiply(int m,int n,int sum){
		return -1; // dummy value to be removed.
	}

}

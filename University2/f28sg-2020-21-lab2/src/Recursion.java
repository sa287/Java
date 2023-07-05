public class Recursion {

	// Part 1: complete
	/**
	 * @param n number given to sum
	 * @return sum of all numbers up to n
	 */
	public static int sum(int n){ 
		if  (n==0) return 0;
		else {
			return n + sum(n-1);
		}
		
	
	}

	// Part 1 complete
	
	/**
	 * @param m number to multiply with n
	 * @param n number to multiply with m
	 * @return the product of m and n
	 */
	public static int multiply(int m, int n){
		
		if ( n == 0 ){
			return 0;
		}
		
		else if (n < 0) {
			return -m + multiply(m, n+1);
		}
			else {
				return m + multiply(m , n-1);
		}
	
		
	}
	
	// Part 1: complete
	
	/**
	 * @param n number of fibonacci terms to output
	 * @return number of terms of fibonacci up to the nth term
	 */
	public static int Fibonacci(int n){
		if (n == 0) {
			return 0;
		}
		else if (n == 1) {
			return 1;
			
		}
		else {
			return Fibonacci(n-1) + Fibonacci(n-2);
		}
		
		 // dummy value to be removed.
	}


}
// exception used for Q5
class CalculateException extends RuntimeException {
	public CalculateException(String err) {
		super(err);
	}
}

public class Calculator {

	/*
	 * Methods for Part 3
	 */
	/**
	 * @param cmds
	 * @return calculated answer of the given array
	 */
	public static int calculate(String[] cmds) {
		Stack pstack = new Stack(100);
		int answer = 0;	

// REVERSE ARRAY FUNCTION CODE USED FROM EARLIER TASK. 	
		for (int i = 0; i < cmds.length; i++) {
			pstack.push(cmds[i]);

		}

		for (int i = 0; i < cmds.length; i++) {
			cmds[i] = pstack.pop().toString();
		}
	
		
// GO THROUGH REVERSED ARRAY, FIND NUMBERS AND THEN PUT THOSE NUMBERS IN STACK.	
		for (int i = 0; i < cmds.length; i++) {
			if (isNumber(cmds[i])) {
				pstack.push(cmds[i]);
				
			} 
// IF ITS NOT A NUMBER, POP TWO ITEMS FROM STACK AND DO THE APPLYOP METHOD WITH THE OPERATOR IT FOUND.	
			else {
				
				answer = applyOp(pstack.pop().toString(), cmds[i],pstack.pop().toString());
				pstack.push(Integer.toString(answer));
			}
		}
		
// RETURN THE FINAL ANSWER OUTSIDE OF THE METHOD FOR USE ELSEWHERE.
		return answer; 
	}

	
	
	/**
	 * @param s
	 * @return return converted String s to integer, and into a variable called x, which is then returned.
	 * @throws NumberFormatException
	 */
	public static int convert(String s) throws NumberFormatException {

		int x = 0;
		x = Integer.parseInt(s);
		return x; 
	}

	
	
	/**
	 * @param s
	 * @return boolean values, true or false, depending on succession of try-catch statement. If it Integer.parseInt succeeds, return true, otherwise
	 * if an NumberFormatException is thrown, return false.
	 */
	public static boolean isNumber(String s) {
		try {
			 Integer.parseInt(s);
			 return true;
		}	
			catch (NumberFormatException operator) {
				
				return false;
		}
	}

	
	/**
	 * @param fst
	 * @param op
	 * @param snd
	 * @return z, after converting fst and snd strings to int, and doing the calculations by matching the op string to the if statement conditions and progressing through.
	 */
	public static int applyOp(String fst, String op, String snd) {
		int x = convert(fst);
		int y = convert(snd);
		int z = 0;
		if (op == "*") {
			z = x * y;
		} else if (op == "+") {
			z = x + y;
		} else if (op == "-") {
			z = x - y;
		} else if (op == "/") {
			z = x / y;

		}

		return z; 
	}

	
	
	
	
	
	
	
	
	// main operation to calculate using Polish notation directly
	public static int calculatePolish(String[] cmds) {
		return -1; // dummy value
	}
}

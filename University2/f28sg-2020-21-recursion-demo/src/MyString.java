
public class MyString {
	
	public static int length(String str) {
		//TODO base case
		// isEmpty for "" -> true
		// isEmpty for "Java" -> false
		// TODO recursive case
		if (str.isEmpty()) {
			return 0;
		}
		else {
			return 1 + length(str.substring(1));
		}
		 // dummy return value
	}
	
}
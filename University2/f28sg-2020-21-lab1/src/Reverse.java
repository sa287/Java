import java.util.Arrays;

public class Reverse {

	// Part 2
	public static void reverse(String[] arr){ 
		Stack rStack = new Stack(3);
		
		for (int i = 0; i < arr.length;i++) {
			 	rStack.push(arr[i]);
			 	
	}
		
		
		
		
		
		for (int i = 0; i < arr.length;i++) {
			arr[i] = rStack.pop().toString();		
		}
		System.out.println(Arrays.toString(arr));
		
}
}
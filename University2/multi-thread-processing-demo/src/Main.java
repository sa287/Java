
public class Main {

	public static void main(String[] args) {
		CompareMultiAndSingleThreading acr1 = new CompareMultiAndSingleThreading();
		acr1.performCalculations(false);
		
		CompareMultiAndSingleThreading acr2 = new CompareMultiAndSingleThreading();
		acr2.performCalculations(true);
	}

}


public class Collection {
	private Person[] collection;
	private static int MAX_SIZE = 200;
	private int size;

	public Collection(int max) {
		collection = new Person[max];
		size = 0;
	}

	public Collection() {
		this(MAX_SIZE);
	}

	public Person[] getCollection() {
		return collection;
	}

	public int getSize() {
		return size;
	}

	public void addPerson(String fname, String lname, int age) {
		collection[size++] = new Person(fname, lname, age);
	}

	public int maxAgeLinear() {
		// TODO use a loop
		int maximumAge = -1;
		
		for (int idx = 0; idx < getSize(); idx++) {
			if (collection[idx].getAge() > maximumAge) {
				maximumAge = collection[idx].getAge();
			}
		}
		return maximumAge;
		
	}
	
	public int maxAgeRecursive() {
		// TODO use maxAgeRecursive method
		return maxAgeRecursive(-1 ,0);
	}

	private int maxAgeRecursive(int currentAge, int idx) {
		// Base case
		if (idx == getSize()) return currentAge;
		

		// Recursive case
		else {
			int newAge = collection[idx].getAge();
			if (newAge > currentAge) {
				return maxAgeRecursive(newAge,idx + 1);
			}
			else {
				return maxAgeRecursive(currentAge, idx+1 );
			}
			
		}
		// dummy output
		
	}

}

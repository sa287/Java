public class ASearch {


	private Entry[] catalogue;
	private int current;
	
	/*
	 * Assume 10 entries
	 */
	public ASearch(){
		catalogue = new Entry[10];
		current = 0;
	}
	
	/*
	 * Ignores adding if full (should really be handled by exception...)
	 */
	public void addEntry(Entry e){
		if(current < 10){
			catalogue[current++] = e;
		}
	}
	
	/*
	 * Part 2: complete implementation
	 */
	/**
	 * @param name, the name that is required in string form
	 * @return, the corresponding number to the name variable that is given. Does this by iterating through the catalogue entry array
	 * checking each indexs' name using the loop variable i and the methods of the entry data type .getName. We use .getName to get a comparable
	 * string that can allow us to use the .equals method to see if it matches with the given parameter name. if it does we return the number of that
	 * name at the catalogue index point i. If the name is not found, it will return -1 as stated in the spec beforehand.
	 */
	public int linearSearch(String name){
		// your code
		for (int i = 0; i<current;i++) {
			if(catalogue[i].getName().equals(name)){
				return catalogue[i].getNumber();
			}
		}
		return -1;
	}

	/*
	 * Part 4: complete implementation
	 */
	/**
	 * @param first, the starting point of the search
	 * @param last, the end point of the search
	 * @param name, the name that is being searched for
	 * @return the corresponding number like before, but this time using variables to create a middle variable which is the
	 * middle of the catalogue array. So the middle Entry variable is set to the middle index (pog represents the numerical middle) of the 
	 * catalogue list.
	 * It will first check if the starting point of the search is greater than the list, because of this method being recursive
	 * if the starting point is greater than the end point, this means that the item cannot be found in the list.
	 * Like the linear search method, it will then compare the middle entry variables' name to the parameter and will return it if they match,
	 * otherwise it will recursively call the function and edit the parameters of the starting/ending points accordingly, depending if the middle
	 * variables name is alphabetically more/less than the parameter name. It also makes sure to pass in the name that is needed to be found.
	 */
	private int binarySearch(int first,int last,String name){
		// your code
		int pog = (first+last)/2;
		Entry middle = catalogue[pog];
		if(first>last) {
			return -1;
		}
		if(middle.getName().equals(name)) {
			return middle.getNumber();
		}
			else if (middle.getName().compareTo(name) > 0)
				return binarySearch(first,pog-1,name);
			else {
				return binarySearch(pog+1,last,name);
			}
			
				
		
	}

	// helper method exposed to the programmer
	public int binarySearch(String name){
		return binarySearch(0,current-1,name);
	}
	
	
}

package F28DA_CW1;

import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Math;

public class HTWordsSet implements WordsSet, Hashing, Monitor {
	public int tempSHIT = 0;
	private float maxLF = (float) 0.5;
	private int prime;
	private int max;
	private String[] words;
	private int collisions;
	private int numberOfProbes;
	private int numberOfOperations;
	private int size;
	private int[] previousSizes;
	private int sizeChanged;
	
	public HTWordsSet(float maxLoadFactor) {
		// TODO Auto-generated constructor stub
	this.maxLF = maxLoadFactor;
	words = new String[7];
	prime = 2;
	max = (int)(1e9+9);
	collisions = 1;
	numberOfProbes =0;
	numberOfOperations =0;
	size = 7;
	previousSizes = new int[100];
	sizeChanged = 0;
	}

	@Override
	public float getMaxLoadFactor() {
		// TODO Auto-generated method stub
	
		return this.maxLF;
	}

	@Override
	public float getLoadFactor() {
		// TODO Auto-generated method stub
		int temp = this.getWordsCount();
		
		int loadFactor = temp/size;
		
		return loadFactor;
	}

	@Override
	public float getAverageProbes() {
		// TODO Auto-generated method stub
		return (float) numberOfProbes/numberOfOperations;
	}
	
	public int primeGen(int doubledSize) {

	    boolean isPrime;
	   doubledSize++;
	    while (true) {
	        int sqrt = (int) Math.sqrt(doubledSize);
	        isPrime = true;
	        for (int i = 2; i <= sqrt; i++) {
	            if (doubledSize % i == 0) isPrime = false;
	        }
	        if (isPrime) {
	        	 
	        	  return doubledSize;
	        }
	            
	        else {
	           doubledSize++;
	        }
	    }
	
	  
		
		
		
	}
	
	public void adjustSize() {
		
		int temp = size;
		
		previousSizes[sizeChanged] = temp;
		sizeChanged++;
		size = primeGen(2*size);
		
		
		String[] newSize = new String[size];
		for(int i = 0; i<temp;i++) {
			newSize[i] = words[i];
		}
		
		words = newSize;
		
	}

	@Override
	public int giveHashCode(String s) {
		// TODO Auto-generated method stub
		int hash = 0;
		int powerIter = 1;
		
		for(int i = 0; i<s.length(); i++) {
			hash = (hash + (s.charAt(i) - 'a' +1) * powerIter) % max;
			powerIter = (powerIter * prime) % max;
		}
		
		
		return hash;
	}
	
	public int giveHashCode2(String s) {
		int hash = 0;
		int powerIter = 1;
		int tempPrime = 2;
		
		for(int i = 0; i<s.length(); i++) {
			hash = (hash + (s.charAt(i) - 'a' +1) * powerIter) % max;
			powerIter = (powerIter * tempPrime) % max;
		}
		
		return hash;
	}

	
	@Override
	public void insWord(String word) throws SpellCheckException {
		// TODO Auto-generated method stub
		
		
		int hash = giveHashCode(word);
		if(getLoadFactor()>this.maxLF) {
			adjustSize();
		}
		
		
			if(words[hash%size]!=null) {
				if(words[hash%size].equals(word)) {
					numberOfProbes++;
					throw new SpellCheckException("Word already exists");
				}		
				int hash2 = giveHashCode2(word);
				
				if(words[(hash+collisions*hash2)%size]!=null){
					if(words[(hash+collisions*hash2)%size].equals(word)) {
						numberOfProbes++;
						throw new SpellCheckException("Word already exists");
					}	
				}
				else {
					words[(hash+collisions*hash2)%size] = word;
					tempSHIT++;
					numberOfOperations++;
					numberOfProbes++;
				}
				
				collisions++;
				
				}
				else {
					words[hash%size] = word;
					numberOfOperations++;
					numberOfProbes++;
					tempSHIT++;
				}
		
		
	}

	@Override
	public void rmWord(String word) throws SpellCheckException {
		// TODO Auto-generated method stub
		int hash = giveHashCode(word);
		int hash2 = giveHashCode2(word);
		if(words[hash%size].equals(word)) {
			words[hash%size] = null;
			numberOfProbes++;
			numberOfOperations++;
		}
		else if(words[(hash+collisions*hash2)%size].equals(word)) {
			words[(hash+collisions*hash2)] = null;
			numberOfProbes++;
			numberOfOperations++;
		}
		else {
			throw new SpellCheckException("Word doesn't exist");
		}

	}

	
	public boolean previousSizeCheck(int hash3, int hash4, String word1) {
		int counter = 0;
		
		for(int x = 0; x<100; x++) {
			if(previousSizes[x]!=0) {
				counter++;
			}
			
			
		}
		
		int hash = hash3;
		int hash2 = hash4;
		
		
		for(int i =0; i<counter;i++) {
		//	System.out.println(words[hash%previousSizes[i]]);
		//	System.out.println(words[(hash+collisions*hash2)%previousSizes[i]]);
			if(words[hash%previousSizes[i]]==null) {
				
				return false;
			}
			else if((words[(hash+collisions*hash2)%previousSizes[i]]) == null){
				return false;
			}
			else if(words[hash%previousSizes[i]].equals(word1)) {
				numberOfOperations++;
				numberOfProbes++;
				return true;
				
			}
			else if(words[(hash+collisions*hash2)%previousSizes[i]].equals(word1)) {
			
				numberOfOperations++;
				numberOfProbes++;
				return true;
			}
			
			
		
		
		
		
		
		
		}
		
		numberOfOperations++;
		numberOfProbes++;
		return false;
		
	}
	
	
	
	
	@Override
	
	
	public boolean wordExists(String word) {
		// TODO Auto-generated method stub
		int hash = giveHashCode(word);
		int hash2 = giveHashCode2(word);
		boolean check = previousSizeCheck(hash,hash2,word);
		if(check) return true;
		if(words[hash%size]==null) {
			//System.out.println()
			return false;
		}
		else if((words[(hash+collisions*hash2)%size]) == null){
			return false;
		}
		else if(words[hash%size].equals(word)) {
			numberOfOperations++;
			numberOfProbes++;
			return true;
			
		}
		else if(words[(hash+collisions*hash2)%size].equals(word)) {
			numberOfOperations++;
			numberOfProbes++;
			return true;
		}
		
		numberOfOperations++;
		numberOfProbes++;
		return false;
	}

	@Override
	public int getWordsCount() {
		// TODO Auto-generated method stub
		int number = 0;
		
		for(String x:words)
		{
			if(x!=null) {
				number++;
			}
			numberOfProbes++;
		}
		return number;
	}

	@Override
	public Iterator<String> getWordsIterator() {
		// TODO Auto-generated method stub
		LinkedList<String> tempList = new LinkedList<String>();
		for(String x:words) {
			if(x!=null) {
				
				tempList.add(x);
			}
			numberOfProbes++;
		}
		
		return tempList.iterator();
	}

}

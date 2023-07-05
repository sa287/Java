package F28DA_CW1;

import java.util.Iterator;
import java.util.LinkedList;
import java.lang.Math;

public class HTWordsSet implements WordsSet, Hashing, Monitor {
	
	private float maxLF = (float) 0.5;

	private String[] words;
	private int collisions;
	private int numberOfProbes;
	private int numberOfOperations;
	private int size;
	
	
	public HTWordsSet(float maxLoadFactor) {
		// TODO Auto-generated constructor stub
	maxLF = maxLoadFactor;
	words = new String[7];
	collisions = 0;
	numberOfProbes =0;
	numberOfOperations =0;
	size = 7;
	
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
		
		float loadFactor = (float) temp/size;
		//System.out.println(size);
		//System.out.println(temp);
		//System.out.println(loadFactor);
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
		
		size = primeGen(2*size);
		
		
		String[] newWords = words;
		
		words = new String[size];
		for(int i = 0; i<temp;i++) {
			if(newWords[i]!=null) {
				try {
					
					insWord(newWords[i]);
				} catch (SpellCheckException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}


	@Override
	public int giveHashCode(String s) {
		// TODO Auto-generated method stub
		int hash=0;
		for(int i=0;i<s.length();i++) {
			 hash = 31*hash + s.charAt(i);
			 hash = hash%size;
		}
		hash = hash%size;  
		
		
		numberOfOperations++;
		//System.out.println(hash);
		return hash;
	}
	
	public int giveHashCode2(String s) {
		int hash = 0;
		int tempPrime = 5;
		
		for(int i=0;i<s.length();i++) {
		  hash = 31*hash + s.charAt(i); 
		 
		}
		hash = tempPrime -hash%tempPrime;
		numberOfOperations++;
		//System.out.println(hash);
		return hash ;
	}

	
	@Override
	public void insWord(String word) throws SpellCheckException {
		// TODO Auto-generated method stub
		
		if(getLoadFactor()>=getMaxLoadFactor()) {
			
			adjustSize();
		}
		int hash = giveHashCode(word);
		
		int hash2 = giveHashCode2(word);
		
			if(wordExists(word)) {
			numberOfProbes++;
			throw new SpellCheckException("Word already exists");
			}
			else {
				for(int i = 0; i < size; i++) {
				if(words[(hash+i*hash2)%size] == null) {
					words[(hash+i*hash2)%size] = word;
					numberOfOperations++;
					numberOfProbes++;
					break;
				}
			
				}
				
				}
		
		
				
	}

	@Override
	public void rmWord(String word) throws SpellCheckException {
		// TODO Auto-generated method stub
		int hash = giveHashCode(word);
		int hash2 = giveHashCode2(word);
		if(wordExists(word)) {
			
			for(int i = 0; i < size; i++) {
			
			if(words[(hash+i*hash2)%size].equals(word)) {
				words[(hash+i*hash2)%size] = null;
				break;
			}
			
			
			
			numberOfProbes++;
			numberOfOperations++;
			}
		}
		else {
			
			throw new SpellCheckException("Word doesn't exist");
		}

	
	}

	
	
	
	
	
	@Override
	
	
	public boolean wordExists(String word) {
		// TODO Auto-generated method stub
		int hash = giveHashCode(word);
		int hash2 = giveHashCode2(word);
		for(int i=0; i<size;i++) {
		if(words[(hash+i*hash2)%size] == null) {
			// System.out.println(size);
			return false;
		}
		if(words[(hash+i*hash2)%size].equals(word)) {
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
	public int getWordsCount() {
		// TODO Auto-generated method stub
		int number = 0;
		
		for(String x:words)
		{
			//System.out.println(x);
			if(x!=null) {
				number++;
			}
			
		}
	//	System.out.println("hello"+number);
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
			
		}
		
		return tempList.iterator();
	}

}

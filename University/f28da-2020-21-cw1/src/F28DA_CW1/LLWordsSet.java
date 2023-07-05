package F28DA_CW1;

import java.util.LinkedList;
import java.util.Iterator;

public class LLWordsSet implements WordsSet {

	LinkedList<String> set;

	public LLWordsSet() {
		// TODO Auto-generated constructor stub
		set = new LinkedList<String>();

	}

	@Override
	public void insWord(String word) throws SpellCheckException {
		// TODO Auto-generated method stub
		if(set.contains(word)) {
			throw new SpellCheckException("Word already exists");
		}
		else {
			set.add(word);
		}
		

	}

	@Override
	public void rmWord(String word) throws SpellCheckException {
		// TODO Auto-generated method stub
		if(set.contains(word)){
			set.remove(word);
		}
		else {
			throw new SpellCheckException("Word doesn't exist");
		}
		
	}

	@Override
	public boolean wordExists(String word) {
		// TODO Auto-generated method stub
		
		for (String words : set) {
			
			if (words.equals(word)) {
				
				return true;
			}
		}
		return false;
	}

	@Override
	public int getWordsCount() {
		// TODO Auto-generated method stub

		return set.size();
	}

	@Override
	public Iterator<String> getWordsIterator() {
		return set.iterator();
		// TODO Auto-generated method stub
		//return new Iterator<String>() {
		//	
		//	String current = set.getFirst();
		//	@Override
		//	public boolean hasNext() {
		//		// TODO Auto-generated method stub
		//		return current!=null;
		//	}

		//	@Override
		//	public String next() {
		//	//	// TODO Auto-generated method stub
			//	if(hasNext()) {
			//		int tempIndex = set.indexOf(current);
			///		tempIndex++;
			//		current = set.get(tempIndex);
			//		return current;
			//	}
			//	return null;
			//}
			
		//};
	}

}
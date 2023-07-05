package F28DA_CW1;

import java.util.LinkedList;

public class SpellingImpl implements Spelling {

	public HTWordsSet suggestions(String word, WordsSet dict) {
		// TO IMPLEMENT
		HTWordsSet changes = new HTWordsSet((float) 0.5);
		
		String variant = null;
		
	
		for(int i = 0; i <word.length(); i++) {
			
			//change characters
			for(char charC ='a'; charC<='z'; charC++) {
				variant = word.substring(0,i) + charC + word.substring(i+1);
				
				
				if (dict.wordExists(variant)){
					try {
						
						
						changes.insWord(variant);
					} catch (SpellCheckException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			

			//add characters
			for (char charA ='a';charA<='z';charA++) {
				variant = word.substring(0,i) + charA + word.substring(i);
				
				if (dict.wordExists(variant)){
					try {
						
						changes.insWord(variant);
					} catch (SpellCheckException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} 
			
			//space characters
			char space = ' ';
			variant = word.substring(0,i) + space + word.substring(i);
			if (dict.wordExists(word.substring(0,i))&& dict.wordExists(word.substring(i))) {
				try {
					
					changes.insWord(variant);
				} catch (SpellCheckException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//delete characters
			variant = word.substring(0,i) + word.substring(i+1);
			if (dict.wordExists(variant)){
				try {
					
					changes.insWord(variant);
				} catch (SpellCheckException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		//swap characters
		for(int i = 1; i<word.length(); i++) {
			char charS1 = word.charAt(i-1);
			char charS2 = word.charAt(i);
			
			variant = word.substring(0,i-1) + charS2 + charS1 + word.substring(i+1);
			if(dict.wordExists(variant)) {
				try {
					changes.insWord(variant);
					
				} catch (SpellCheckException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		
		
		return changes;
	}

}

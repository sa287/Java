package F28DA_CW1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Main class for the program
 */
public class SpellCheck {

	/**
	 * Main method for the program. The program takes two input filenames in the
	 * command line: the word dictionary file and the file containing the words to
	 * spell-check.
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: SpellCheck dictionaryFile.txt inputFile.txt ");
		System.exit(1);
		}

		try {
			
			BufferedInputStream dict, file;
			SpellingImpl speller;
			HTWordsSet linked = new HTWordsSet((float) 0.5);
			HTWordsSet spelled = new HTWordsSet((float) 0.5);
			HTWordsSet temp = new HTWordsSet((float) 0.5);
		
		
			dict = new BufferedInputStream(new FileInputStream(args[0]));
			
			FileWordRead dictWords = new FileWordRead(dict);
			while (dictWords.hasNextWord()) {
				
				
				try{
					linked.insWord(dictWords.nextWord());
					//System.out.println(linked.getWordsIterator().next());
				
					
				}
				catch(SpellCheckException linkedRead) {
					System.err.println("Error has occurred whilst reading the dictionary.");
				}
			}
			// TO IMPLEMENT
			//System.out.println(linked.tempSHIT);
			dict.close();
		
			speller = new SpellingImpl();
			
			file = new BufferedInputStream(new FileInputStream(args[1]));
						
			FileWordRead spellCheckFile = new FileWordRead(file);
			
			
			while (spellCheckFile.hasNextWord()) {
				
				String tempWord = spellCheckFile.nextWord();
				if(!linked.wordExists(tempWord)) {
					
					
					temp = speller.suggestions(tempWord, linked);
					
					if(temp.getWordsCount()>0) {
					try {
						spelled.insWord(temp.getWordsIterator().next());
					} catch (SpellCheckException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
			}
			
			
			
			// TO IMPLEMENT

			file.close();
			
			//ERROR HERE
			Iterator<String> x = spelled.getWordsIterator();
			//System.out.println(x.hasNext());
			//System.out.println(linked.getWordsIterator().next());
			//while(linked.getWordsIterator().hasNext()) {
			//	System.out.println(linked.getWordsIterator().next());
			//}
			
			System.out.println(linked.getWordsCount());
			System.out.println(linked.tempSHIT);
			
			while(x.hasNext()) {
				System.out.println(x.next());
			}
			
		} catch (IOException e) { // catch exceptions caused by file input/output errors
			System.err.println("Missing input file, check your filenames");
			System.exit(1);
		}
	}

}

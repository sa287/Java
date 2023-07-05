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
		long x1 = System.currentTimeMillis();
		int balls = 0;
		// check if the correct number of arguments is given in with the execution of the SpellCheck class, otherwise output a message to the user and exit the program.
		if (args.length != 2) {
			System.err.println("Usage: SpellCheck dictionaryFile.txt inputFile.txt ");
		System.exit(1);
		}

		// try catch statement to ensure any SpellCheckExceptions that are thrown by the try block, are caught by the catch block and handled.
		try {
			
			// Initialise the variables and data structures
			// BufferedInputStreams dict and file, used in the I/O of the program to help read in the dictionary and text-to-check file.
			BufferedInputStream dict, file;
			// Instantiate our SpellingImpl class that holds the function to create word suggestions, i.e. our actual spell checker class.
			SpellingImpl speller;
			// Create our wordset of choice (in this case, the hash table implementation) and choose it to hold our information of the first dictionary, the words that are
			// suggested by our spellchecker and a temporary wordset to hold temporarily hold data. If its a hash table, we give in the load factor if we need to. Otherwise it will default
			// at 0.5.
			HTWordsSet linked = new HTWordsSet((float) 0.5);
			HTWordsSet spelled = new HTWordsSet((float) 0.5);
			WordsSet temp = new HTWordsSet((float) 0.5);
		
			// Take in the first argument that is given in, and use that as the dictionary to be read.
			dict = new BufferedInputStream(new FileInputStream(args[0]));
			
			// FileWordRead class helps in our file I/O and we use it to read the data in the file.
			FileWordRead dictWords = new FileWordRead(dict);
			// This while block  reads in each word in the dictWords variable and will stop once its ran
			// out of words to read in in the file. For every word that is read in, we try to insert it into the
			// linked variable using its method insWord. If an error occurs whilst trying to insert the word in, we handle it in the catch block.
			while (dictWords.hasNextWord()) {
				
				try{	
					linked.insWord(dictWords.nextWord());
				}
				catch(SpellCheckException linkedRead) {
					// An error message is given out if an error occurs during the insertion of the word.
					System.err.println("Error has occurred whilst reading the dictionary.");
				}
			}
			
			balls = linked.getWordsCount();
			dict.close();
		
			
			
			// We move on to the file that is getting spell checked.
			speller = new SpellingImpl();
			
			file = new BufferedInputStream(new FileInputStream(args[1]));
						
			FileWordRead spellCheckFile = new FileWordRead(file);
			
			// We iterate through the words in the file
			while (spellCheckFile.hasNextWord()) {
				// We create temporary string variable that holds each word in the file during the duration of the loop.
				String tempWord = spellCheckFile.nextWord();
				// if the word does not exist in the linked word set, aka if it wasnt in the dictionary we spell check it
				// and return the correctly spelled word and insert it into the spelled word set.
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
			
			
			

			file.close();
		
			// create an iterator of type string to hold the values in the spelled word set
		Iterator<String> x = spelled.getWordsIterator();
		
			
			// then use said iterator, to print out each of the values in the spellle
		while(x.hasNext()) {
			System.out.println(x.next());
			
	   }
			
		} catch (IOException e) { // catch exceptions caused by file input/output errors
			System.err.println("Missing input file, check your filenames");
			System.exit(1);
		}
	
		long x2 = System.currentTimeMillis();
		long x3 = x2 - x1;
		System.out.println("Time:"+x3);
		System.out.println("Words:"+balls);
		
	
	
	}

}

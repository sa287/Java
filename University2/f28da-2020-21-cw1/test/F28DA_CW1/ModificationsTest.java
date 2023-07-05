package F28DA_CW1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModificationsTest {

	@Test
	public void test1() {
		WordsSet dict = new LLWordsSet(); // or new HTWordsSet();
		// ...
		try {
			dict.insWord("hello");
			dict.insWord("troll");
			dict.insWord("ogre");
			dict.rmWord("hello");
			dict.insWord("wolf");
			dict.insWord("demon");
			dict.insWord("tree");
		}
		catch(SpellCheckException e) {
			fail("Error with linked list implementation");
		}
		
		
		
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("demn", dict);
		assertTrue(sugg.wordExists("demon"));
	}

	@Test
	public void test2() {
		WordsSet dict = new LLWordsSet();
		
		
		try {
			dict.insWord("dog");
			dict.insWord("troll");
			dict.insWord("ogre");
			dict.insWord("wolf");
			dict.insWord("demon");
			dict.insWord("tree");
		}
		catch(SpellCheckException e) {
			fail("Error with linked list implementation");
		}
		
		
		
		Spelling speller = new SpellingImpl();
		WordsSet sugg = speller.suggestions("dag", dict);
		assertTrue(sugg.wordExists("dog"));
	
	
	
	
	
	}

	

}

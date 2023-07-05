package F28DA_CW1;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class HTableWordsTest {

	
	@Test
	public void test1() {
		// tests for getWordsCount and insWord functions and correct values.
		try {
			float maxLF = (float) 0.5;
			HTWordsSet testSet = new HTWordsSet(maxLF);
			String word1 = "hello";
			String word2 = "hello2";
			testSet.insWord(word1);
			testSet.insWord(word2);
			
			assertTrue(testSet.getWordsCount() == 2);
		} catch (SpellCheckException e) {
			fail();
		}
	}
		
	@Test
	public void test2() {
		try	{
			// tests if a large amount of words inserted result in the correct number of words given.
			float maxLF = (float) 0.5;
			HTWordsSet testSet = new HTWordsSet(maxLF);
			String word1;
			for (int i = 0; i < 100; i++) {
				word1 = "hi" + i;
				testSet.insWord(word1);
			}
			assertEquals(testSet.getWordsCount(), 100);
			
		} catch (SpellCheckException e) {
			fail();
		}
	}
	
	@Test
	public void test3() {
		// check if the word1 variable that is inserted and removed into the word set, is recognized as not existing in the word set afterward.
		float maxLF = (float) 0.5;
		HTWordsSet testSet = new HTWordsSet(maxLF);
		String word1 = "hello";
		try {
			testSet.insWord(word1);
			testSet.rmWord(word1);
		} catch (SpellCheckException e) {
			fail();
		}
		assertFalse(testSet.wordExists("hello"));
	}















}

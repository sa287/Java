import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CollectionTest {

	Collection c;

	@Before
	public void setup() {
		c = new Collection();
	}

	@Test
	public void testEmptyMaxAgeLinear() {
		assertEquals(-1, c.maxAgeLinear());
	}

	@Test
	public void testOneElementMaxAgeLinear() {
		c.addPerson("Rob", "Stewart", 33);
		assertEquals(33, c.maxAgeLinear());
	}

	@Test
	public void testThreeElementsMaxAgeLinear() {
		c.addPerson("Rob", "Stewart", 33);
		c.addPerson("Sarah", "Smith", 37);
		c.addPerson("Steven", "Johnson", 16);
		assertEquals(37, c.maxAgeLinear());
	}

	@Test
	public void testEmptyMaxAgeRecursive() {
		assertEquals(-1, c.maxAgeRecursive());
	}

	@Test
	public void testOneElementMaxAgeRecursive() {
		c.addPerson("Rob", "Stewart", 33);
		assertEquals(33, c.maxAgeRecursive());
	}

	@Test
	public void testThreeElementsMaxAgeRecursive() {
		c.addPerson("Rob", "Stewart", 33);
		c.addPerson("Sarah", "Smith", 37);
		c.addPerson("Steven", "Johnson", 16);
		assertEquals(37, c.maxAgeRecursive());
	}
}

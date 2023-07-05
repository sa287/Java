import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyStringTest {
	
	@Test
	public void lengthTest1() {
		assertEquals(MyString.length("foo"), 3);
	}
	
	@Test
	public void lengthTest2() {
		assertEquals(MyString.length(""), 0);
	}
	
	@Test
	public void lengthTest3() {
		assertEquals(MyString.length("software"), 8);
	}
	
}
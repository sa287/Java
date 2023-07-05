import static org.junit.Assert.*;

import org.junit.Test;


public class ReverseStackTest {

	@Test
	public void reverseStackTest() {
		Stack<String> st = new Stack<>(5);
		st.push("A");
		st.push("B");
		st.push("C");
		ReverseStack.reverseStack(st);
		assertEquals("A", st.pop());
		assertEquals("B", st.pop());
		assertEquals("C", st.pop());
	}


}

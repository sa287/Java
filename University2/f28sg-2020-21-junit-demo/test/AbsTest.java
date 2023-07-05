import static org.junit.Assert.*;
import org.junit.Test;

public class AbsTest {
	@Test
	// abs(1)
	public void testAbsPos() {
		assertEquals(1, Abs.abs(1));
	}

	@Test
	// abs(-1)
	public void testAbsNeg() {
		
	
		assertEquals( 3, Abs.abs(-3));
	}
}

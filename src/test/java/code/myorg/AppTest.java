package code.myorg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTruetest()
    {
        assertTrue( true );
    }
    
    @Test
	public void test1() {
    	App ob = new App();
		assertEquals(8, ob.add(4, 4));
	}
    
    @Test
	public void test2() {
    	App ob = new App();
		assertEquals(2, ob.divide(10, 5));
	}
    
    @Test
	public void test3() {
    	App ob = new App();
		assertEquals(40, ob.multiply(10, 4));
	}
}

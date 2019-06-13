package code.myorg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
	
	@BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }
 
    @Before
    public void before() {
        System.out.println("Before Test Case");
    }
 
     
    @After
    public void after() {
        System.out.println("After Test Case");
    }
 
    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }
	
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

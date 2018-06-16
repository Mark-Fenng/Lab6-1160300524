import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * Monkey Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>June 16, 2018</pre>
 */
public class MonkeyTest {
    private Monkey m1 = new Monkey(1, "L->R", 4);
    private Monkey m2 = new Monkey(2, "R->L", 1);


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getDirection()
     */
    @Test
    public void testGetDirection() throws Exception {
        assertEquals("L->R", m1.getDirection());
        assertEquals("R->L", m2.getDirection());
    }
} 

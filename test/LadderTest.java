import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Ladder Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>June 15, 2018</pre>
 */
public class LadderTest {

    private Ladder ladder1 = new Ladder(10, 1);
    private Ladder ladder2 = new Ladder(11, 2);
    private Monkey m1 = new Monkey(1, "L->R", 5);
    private Monkey m2 = new Monkey(2, "R->L", 6);

    @Before
    public void before() throws Exception {
        ladder1.addMonkey(0, m1);
        ladder2.addMonkey(0, m2);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getID()
     */
    @Test
    public void testGetID() throws Exception {
        assertEquals(1, ladder1.getID());
        assertEquals(2, ladder2.getID());
    }

    /**
     * Method: getSize()
     * 测试逻辑：在初始时已经添加了一些数量的猴子，比较getSize()返回值和实际梯子上的猴子数量可验证正确性
     */
    @Test
    public void testGetSize() throws Exception {
        assertEquals(1, ladder1.getSize());
        assertEquals(1, ladder2.getSize());
    }

    /**
     * Method: getDirection()
     */
    @Test
    public void testGetDirection() throws Exception {
        assertEquals("L->R", ladder1.getDirection());
        assertEquals("R->L", ladder2.getDirection());
    }

    /**
     * Method: removeMonkey(int index)
     */
    @Test
    public void testRemoveMonkey() throws Exception {
        ladder1.removeMonkey(0);
        // 如果移除猴子后，梯子1上的猴子数量应该为0
        assertEquals(0, ladder1.getSize());
    }

    /**
     * Method: addMonkey(int index, Monkey monkey)
     */
    @Test
    public void testAddMonkey() throws Exception {
        // 原本1号台阶上没有猴子，可以添加成功,会返回true
        assertEquals(true, ladder2.addMonkey(1, m1));
        // 1号台阶上已经有了猴子，添加失败，会返回false
        assertEquals(false, ladder2.addMonkey(1, m1));
        // 0号台阶上已经有了猴子，添加失败，会返回false
        assertEquals(false, ladder2.addMonkey(0, m2));
    }

    /**
     * Method: getMonkeys()
     */
    @Test
    public void testGetMonkeys() throws Exception {
        assertEquals(Arrays.asList(m1, null, null, null, null, null, null, null, null, null), ladder1.getMonkeys());
        assertEquals(Arrays.asList(m2, null, null, null, null, null, null, null, null, null, null), ladder2.getMonkeys());
        ladder2.addMonkey(3, m2);
        assertEquals(Arrays.asList(m2, null, null, m2, null, null, null, null, null, null, null), ladder2.getMonkeys());
    }
} 

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Rung Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>June 15, 2018</pre>
 */
public class RungTest {
    private List<Rung> rungs = new ArrayList<>();
    private List<Monkey> monkeys = new ArrayList<>();

    @Before
    public void before() throws Exception {
        for (int i = 0; i < 10; i++) {
            rungs.add(new Rung());
            monkeys.add(new Monkey(i, "L->R", i + 1));
        }
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getMonkey()
     */
    @Test
    public void testGetMonkey() throws Exception {
        // 在所有的台阶都是空的情况的测试
        for (Rung rung : rungs)
            assertEquals(null, rung.getMonkey());
        // 给所有的梯子添加一个不同的猴子
        for (int i = 0; i < rungs.size(); i++)
            rungs.get(i).setMonkey(monkeys.get(i));
        // 测试所有的有猴子的梯子
        for (int i = 0; i < rungs.size(); i++)
            assertEquals(monkeys.get(i), rungs.get(i).getMonkey());
    }

    /**
     * Method: setMonkey(Monkey newMonkey)
     */
    @Test
    public void testSetMonkey() throws Exception {
        // 给所有的梯子添加一个不同的猴子
        for (int i = 0; i < rungs.size(); i++)
            rungs.get(i).setMonkey(monkeys.get(i));
        // 测试所有的有猴子的梯子
        for (int i = 0; i < rungs.size(); i++)
            assertEquals(monkeys.get(i), rungs.get(i).getMonkey());
    }
} 

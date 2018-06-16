import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * LadderGenerator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>June 16, 2018</pre>
 */
public class LadderGeneratorTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: generateLadders(int ladderNumber, int pedalNumber)
     */
    @Test
    public void testGenerateLadders() throws Exception {
        LadderGenerator.generateLadders(2, 5);
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ladders.add(new Ladder(5, i + 1));
        }
        assertEquals(ladders, LadderGenerator.getLadders());
    }

    /**
     * Method: getLadders()
     */
    @Test
    public void testGetLadders() throws Exception {
        // 如果在调用getLadders()之前没有调用过generateLadders()函数，就会返回一个空的List
        assertEquals(new ArrayList<Ladder>(), LadderGenerator.getLadders());
        LadderGenerator.generateLadders(2, 5);
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ladders.add(new Ladder(5, i + 1));
        }
        assertEquals(ladders, LadderGenerator.getLadders());
    }


} 

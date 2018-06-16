import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * MonkeyGenerator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>June 16, 2018</pre>
 */
public class MonkeyGeneratorTest {

    private MonkeyGenerator monkeyGenerator = new MonkeyGenerator(1, 30, 2, 5);

    @Before
    public void before() throws Exception {
        LadderGenerator.generateLadders(4, 10);
        for (Ladder ladder : LadderGenerator.getLadders()) {
            System.out.println(ladder);
        }
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: generate()
     */
    @Test
    public void testGenerate() throws Exception {
        monkeyGenerator.generate();
    }

    /**
     * Method: getMonkeys()
     */
    @Test
    public void testGetMonkeys() throws Exception {
        monkeyGenerator.getMonkeys();
    }
} 

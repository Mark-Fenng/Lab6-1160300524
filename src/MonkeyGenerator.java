import java.io.*;
import java.util.*;

public class MonkeyGenerator {
    private final List<Monkey> monkeys = Collections.synchronizedList(new ArrayList<>());
    private final int t, N, k, MV;

    MonkeyGenerator() throws IOException {
        String filePath = "./test.conf";
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String content;
        content = bufferedReader.readLine();
        String[] arguments = content.split(" ");
        t = Integer.parseInt(arguments[2]);
        N = Integer.parseInt(arguments[3]);
        k = Integer.parseInt(arguments[4]);
        MV = Integer.parseInt(arguments[5]);
        bufferedReader.close();
    }

    void generate() {
        Timer timer = new Timer();
        long delay = 0;
        long period = t * 1000;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < k; i++) {
                    if (monkeys.size() >= N - 1) {
                        timer.cancel();
                        break;
                    }
                    monkeys.add(new Monkey(monkeys.size(), Math.random() < 0.5 ? "L->R" : "R->L", (int) Math.random() * MV));
                }
            }
        };
        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, period);
    }

    List<Monkey> getMonkeys() {
        return monkeys;
    }
}

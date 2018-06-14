import LoggerFactory.MyLogger;

import java.util.*;

class MonkeyGenerator {
    private final List<Monkey> monkeys = Collections.synchronizedList(new ArrayList<>());
    private final int t, N, k, MV;

    MonkeyGenerator(int t, int N, int k, int MV) {
        this.t = t;
        this.N = N;
        this.k = k;
        this.MV = MV;
    }

    void generate() {
        Timer timer = new Timer();
        long delay = 0;
        long period = t * 1000;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < k; i++) {
                    if (monkeys.size() >= N) {
                        timer.cancel();
                        break;
                    }
                    Monkey newMonkey = new Monkey(monkeys.size() + 1, Math.random() < 0.5 ? "L->R" : "R->L", (int) (Math.random() * MV) + 1);
                    monkeys.add(newMonkey);
//                    MyLogger.info(newMonkey.getID() + "is generated");
                    new Thread(newMonkey).start();
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

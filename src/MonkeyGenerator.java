import LoggerFactory.MyLogger;

import java.util.*;

/**
 * 这个类负责每秒生成指定数量的猴子
 */
class MonkeyGenerator {
    private final List<Monkey> monkeys = Collections.synchronizedList(new ArrayList<>());
    private final List<Thread> monkeysThread = new ArrayList<>();
    private final int t, N, k, MV;
    private long startTime, endTime;

    /**
     * @param t  间隔t秒生成一拨猴子
     * @param N  最终生成的猴子数量
     * @param k  每一拨生成的猴子数量
     * @param MV 生成猴子的最快速度
     */
    MonkeyGenerator(int t, int N, int k, int MV) {
        this.t = t;
        this.N = N;
        this.k = k;
        this.MV = MV;
    }

    /**
     * 调用这个函数之后，开始按照要求产生猴子，并启动猴子的过河线程
     */
    void generate() {
        startTime = System.currentTimeMillis();
        Timer timer = new Timer();
        long delay = 0;
        long period = t * 1000;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < k; i++) {
                    if (monkeys.size() >= N) {
                        timeCallBack();
                        timer.cancel();
                        break;
                    }
                    Monkey newMonkey = new Monkey(monkeys.size() + 1, Math.random() < 0.5 ? "L->R" : "R->L", (int) (Math.random() * MV) + 1);
                    monkeys.add(newMonkey);
//                    MyLogger.info(newMonkey.getID() + "is generated");
                    Thread monkeyThread = new Thread(newMonkey);
                    monkeyThread.start();
                    monkeysThread.add(monkeyThread);
                }
            }
        };
        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, period);
    }

    /**
     * 获得当前时间已经产生的猴子的列表
     *
     * @return 猴子的所有列表
     */
    List<Monkey> getMonkeys() {
        return monkeys;
    }

    private void timeCallBack() {
        monkeysThread.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        endTime = System.currentTimeMillis();
        double throughputRate = N / ((endTime - startTime) / 1000.0);
        MyLogger.info("Throughput Rate is " + throughputRate);
        int fairness = 0;
        for (int i = 0; i < monkeys.size() - 1; i++)
            for (int j = i + 1; j < monkeys.size(); j++) {
                if (((monkeys.get(i).getBornTime() - monkeys.get(j).getBornTime()) ^ (monkeys.get(i).getArrivedTime() - monkeys.get(j).getArrivedTime())) >= 0)
                    fairness++;
                else
                    fairness--;
            }
        MyLogger.info("Fairness is " + fairness / (N * (N - 1) / 2.0));
        MyLogger.closeLogger();
    }
}

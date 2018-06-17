import LoggerFactory.MyLogger;

import java.util.Arrays;
import java.util.List;

/**
 * spec:
 * AF:ID-&gt;猴子的唯一标识
 * direction-&gt;true="L-&gt;R",false="R-&gt;L"
 * speed -&gt; 猴子在梯子上的爬行速度 ，正整数
 * RI: speed&gt;=1，且为整数
 * safe from exposure:
 * 这个类中所有的成员变量都是private和不可变的，不会有表示泄漏的问题
 * thread safe:
 * 这个类没有多线程公共的数据，不会有线程安全问题
 */
public class Monkey implements Runnable {
    private final int ID;
    private final boolean direction;
    private final int speed;
    private long bornTime = 0, arrivedTime = 0;

    Monkey(int ID, String direction, int v) {
        this.ID = ID;
        this.direction = direction.equals("L->R");
        this.speed = v;
        bornTime = System.currentTimeMillis();
    }

    /**
     * @return 猴子的ID
     */
    private int getID() {
        return ID;
    }

    /**
     * @return 猴子的方向 ”L->R" 表示从左到右 "R->L"表示从右到左
     */
    String getDirection() {
        return direction ? "L->R" : "R->L";
    }

    /**
     * @return 猴子的移动速度
     */
    private int getSpeed() {
        return speed;
    }

    /**
     * 得到这只猴子的出生时间
     *
     * @return 猴子的出生时间，单位 ms
     */
    long getBornTime() {
        return bornTime;
    }

    /**
     * 得到这只猴子到达河对岸的时间
     *
     * @return 猴子到达对岸的时间，单位 ms
     */
    long getArrivedTime() {
        return arrivedTime;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Monkey && ((Monkey) obj).getID() == this.ID;
    }

    @Override
    public int hashCode() {
        int[] IDs = new int[1];
        IDs[0] = this.ID;
        return Arrays.hashCode(IDs);
    }

    @Override
    public String toString() {
        return "Monkey" + this.getID();
    }

    @Override
    public void run() {
        this.bornTime = System.currentTimeMillis();
        boolean onLadder = false; // 猴子是否已经上了梯子的一个标志
        int position = 0; // 猴子当前的位置
        ladderChoice choice = ladderChoice.getChoice();
        Ladder ladder = choice.getLadder(this, LadderGenerator.getLadders()); // 猴子选择爬上的梯子对象
        // 如果猴子没有爬上梯子，就每个1s做一次决策，直到爬上梯子为止
        while (!onLadder) {
            ladder = choice.getLadder(this, LadderGenerator.getLadders()); // 猴子选择爬上的梯子对象
            while (ladder != null && !onLadder) {
                onLadder = ladder.addMonkey(0, this);
                if (onLadder)
                    MyLogger.info(this.getID() + " Get on the Ladder " + ladder.getID());
                else
                    ladder = choice.getLadder(this, LadderGenerator.getLadders()); // 猴子选择爬上的梯子对象
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int h = LadderGenerator.getLadders().get(0).getMonkeys().size(); // 梯子的台阶数

        // 如果猴子还在梯子上没有过河的操作
        while (position < h - 1) {
            List<Monkey> monkeyList = ladder.getMonkeys();
            int tryIndex = position + 1;
            // 寻找当前位置之后的适合位置的台阶
            while (tryIndex <= Math.min(position + this.getSpeed(), h - 1)) {
                if (monkeyList.get(tryIndex) == null)
                    tryIndex++;
                else
                    break;
            }
            tryIndex--;
            if (tryIndex != position) {
                if (tryIndex > h - 1)
                    tryIndex = h - 1;
                if (ladder.addMonkey(tryIndex, this)) {
                    ladder.removeMonkey(position);
                    if (ladder.getDirection().equals("L->R"))
                        MyLogger.info(this.getID() + " on the Ladder " + ladder.getID() + " jump to the " + (tryIndex + 1) + "th rung . Direction L->R");
                    else
                        MyLogger.info(this.getID() + " on the Ladder " + ladder.getID() + " jump to the " + (h - tryIndex) + "th rung . Direction R->L");
                    position = tryIndex;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MyLogger.info(this.getID() + " on the Ladder " + ladder.getID() + " passed the ladder ");
        ladder.removeMonkey(position);
        arrivedTime = System.currentTimeMillis();
    }
}

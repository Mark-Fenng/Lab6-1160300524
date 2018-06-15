import LoggerFactory.MyLogger;

import java.util.Arrays;
import java.util.List;

/**
 * spec:
 * AF:ID->猴子的唯一标识
 * direction->true="L->R",false="R->L"
 * speed -> 猴子在梯子上的爬行速度 ，正整数
 * RI: speed>=1，且为整数
 * safe from exposure:
 * thread safe:
 */
public class Monkey implements Runnable {
    private final int ID;
    private final boolean direction;
    private final int speed;

    Monkey(int ID, String direction, int v) {
        this.ID = ID;
        this.direction = direction.equals("L->R");
        this.speed = v;
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
        boolean onLadder = false; // 猴子是否已经上了梯子的一个标志
        int position = 0; // 猴子当前的位置
        ladderChoice choice = new firstStrategy();
        Ladder ladder = choice.getLadder(this, LadderGenerator.getLadders()); // 猴子选择爬上的梯子对象
        // 如果猴子没有爬上梯子，就每个1s做一次决策，直到爬上梯子为止
        while (!onLadder) {
            ladder = choice.getLadder(this, LadderGenerator.getLadders());
            if (ladder != null)
                onLadder = ladder.addMonkey(0, this);
            if (onLadder)
                MyLogger.info(this.getID() + " Get on the Ladder " + ladder.getID());
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
                    ladder.remove(position);
                    MyLogger.info(this.getID() + " on the Ladder " + ladder.getID() + " jump to the " + (tryIndex + 1) + "th rung");
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
        ladder.remove(position);
    }
}

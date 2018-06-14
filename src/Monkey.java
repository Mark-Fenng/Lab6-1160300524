import LoggerFactory.MyLogger;

import java.util.Arrays;
import java.util.List;

/**
 * spec:
 * AF:
 * RI:
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
    int getSpeed() {
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
        boolean onLadder = false;
        int position = 0;
        ladderChoice choice = new firstStrategy();
        Ladder ladder = choice.getLadder(this, LadderGenerator.getLadders());
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
        int h = LadderGenerator.getLadders().get(0).getMonkeys().size();
        while (position < h - 1) {
            List<Pedal> monkeyList = ladder.getMonkeys();
            int tryIndex = position + 1;
            while (tryIndex <= Math.min(position + this.speed, h - 1)) {
                if (monkeyList.get(tryIndex).getMonkey() == null)
                    tryIndex++;
                else
                    break;
            }
            tryIndex--;
            if (tryIndex != position) {
                if (tryIndex > h - 1)
                    tryIndex = h - 1;
                synchronized (ladder) {
                    ladder.remove(position);
                    ladder.addMonkey(tryIndex, this);
                }
                MyLogger.info(this.getID() + " on the Ladder " + ladder.getID() + " jump to the " + (tryIndex + 1) + "th pedal");
                position = tryIndex;
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

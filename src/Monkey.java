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
    private int ladderID = -1;
    private int pedalID = -1;

    Monkey(int ID, String direction, int v) {
        this.ID = ID;
        this.direction = direction.equals("L->R");
        this.speed = v;
    }

    /**
     * @return 猴子的ID
     */
    int getID() {
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

    /**
     * @return
     */
    public int getLadderID() {
        return ladderID;
    }

    /**
     * @return
     */
    public int getPedalID() {
        return pedalID;
    }

    /**
     * @param pedalID
     */
    public void setPedalID(int pedalID) {
        this.pedalID = pedalID;
    }

    /**
     * @param ladderID
     */
    public void setLadderID(int ladderID) {
        this.ladderID = ladderID;
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
            onLadder = ladder.addMonkey(0, this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int h = LadderGenerator.getLadders().get(0).getSize();
        while (position <= h) {
            List<Monkey> monkeyList = ladder.getMonkeys();

        }
    }
}

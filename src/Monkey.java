import java.util.Arrays;

/**
 * spec:
 * AF:
 * RI:
 * safe from exposure:
 * thread safe:
 */
public class Monkey {
    private final int ID;
    private final boolean direction;
    private final int speed;
    private int ladderID = -1;
    private int pedalID = -1;

    private Monkey(int ID, String direction, int v) {
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
}

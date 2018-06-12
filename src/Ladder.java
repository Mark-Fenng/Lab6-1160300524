import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spec
 * AF:
 * RI:
 * safe from exposure:
 * Thread safe:
 */
public class Ladder {
    private int ID;
    private int size = 0;
    private String direction = null;
    private final List<Pedal> pedals = Collections.synchronizedList(new ArrayList<>());

    Ladder(int pedalNumber) {
        synchronized (pedals) {
            for (int i = 0; i < pedalNumber; i++)
                pedals.add(new Pedal());
        }
    }

    /**
     * @return 返回梯子的id值
     */
    private int getID() {
        return ID;
    }

    public int getSize() {
        return size;
    }

    public String getDirection() {
        return direction;
    }

    /**
     * 删除指定踏板上的某个猴子
     *
     * @param index 踏板的下标
     * @return boolean值，表示是否删除成功
     */
    boolean remove(int index) {
        if (index > pedals.size() - 1 || index < 0)
            return false;
        pedals.get(index).setMonkey(null);
        size--;
        if (size == 0)
            this.direction = null;
        return true;
    }

    boolean addMonkey(int index, Monkey monkey) {
        if (index > pedals.size() - 1 || index < 0)
            return false;
        Pedal pedal = pedals.get(0);
        synchronized (pedal) {
            if (pedal.getMonkey() != null) {
                pedals.get(index).setMonkey(monkey);
                this.size++;
                this.direction = monkey.getDirection();
                return true;
            }
        }
        return false;
    }

    public List<Monkey> getMonkeys() {
        return this.pedals.stream().map(Pedal::getMonkey).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Ladder && ((Ladder) obj).getID() == this.ID;
    }

    @Override
    public int hashCode() {
        int[] IDs = new int[1];
        IDs[0] = this.ID;
        return Arrays.hashCode(IDs);
    }

    @Override
    public String toString() {
        return "Ladder" + this.getID();
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * spec
 * AF:
 * RI:
 * safe from exposure:
 * Thread safe:
 */
public class Ladder {
    int ID;
    private final List<Monkey> pedals = Collections.synchronizedList(new ArrayList<>());

    Ladder(int h) {
        synchronized (pedals) {
            for (int i = 0; i < h; i++)
                pedals.add(null);
        }
    }

    /**
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * 删除指定踏板上的某个猴子
     *
     * @param index 踏板的下标
     * @return boolean值，表示是否删除成功
     */
    boolean remove(int index) {
        if (index > pedals.size() - 1 || pedals.get(index) == null)
            return false;
        pedals.add(index, null);
        return true;
    }

    boolean addMonkey(int index, Monkey monkey) {
        if (index > pedals.size() - 1 || pedals.get(index) != null)
            return false;
        pedals.add(index, monkey);
        return true;
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

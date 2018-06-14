import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spec
 * AF: ID->梯子的唯一标识
 * size-> 当前梯子上爬着的猴子数量（便于在选择过河策略中的实现）
 * direction->当前梯子上所有猴子的过河方向（null->梯子上没有猴子 "R->L"所有猴子的方向都是从右到左 "L->R"所有的猴子方向都是从左到右）
 * pedals-> 一个列表，存储的台阶对象
 * RI: pedals的数量始终等于传入的pedalNumber数量 direction的值只能是null,"L->R" ,"R->L"三个值 size>=0
 * safe from exposure:
 * Thread safe:
 */
public class Ladder {
    private final int ID;
    private int size = 0;
    private String direction = null;
    private final List<Pedal> pedals = Collections.synchronizedList(new ArrayList<>());

    Ladder(int pedalNumber, int ID) {
        this.ID = ID;
        synchronized (pedals) {
            for (int i = 0; i < pedalNumber; i++)
                pedals.add(new Pedal());
        }
    }

    /**
     * @return 返回梯子的id值
     */
    int getID() {
        return ID;
    }

    int getSize() {
        return size;
    }

    String getDirection() {
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
            if (pedal.getMonkey() == null) {
                pedals.get(index).setMonkey(monkey);
                this.size++;
                this.direction = monkey.getDirection();
                return true;
            }
        }
        return false;
    }

    List<Pedal> getMonkeys() {
        return this.pedals;
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

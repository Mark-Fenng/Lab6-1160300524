import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * spec
 * AF: ID->梯子的唯一标识
 * size-> 当前梯子上爬着的猴子数量（便于在选择过河策略中的实现）
 * direction->当前梯子上所有猴子的过河方向（null->梯子上没有猴子 "R->L"所有猴子的方向都是从右到左 "L->R"所有的猴子方向都是从左到右）
 * rungs-> 一个列表，存储的台阶对象
 * RI: rungs的数量始终等于传入的rungNumber数量 direction的值只能是null,"L->R" ,"R->L"三个值 size>=0 梯子上所有猴子的方向必须是一致的
 * safe from exposure:
 * 所有的成员变量的访问权限都是private，类的外部不可能直接访问这些变量
 * 为外部提供了修改某个台阶上猴子的封装方法，但是没有暴露出梯子内台阶的存储和实现，因为台阶对象rung是内部类
 * Thread safe:
 * ID是private和不可变的，是线程安全的
 * getSize(),getID(),getDirection()方法内部都是原子操作，不会有线程安全问题
 * getMonkeys()对访问公共数据的部分，加了线程锁，保证了线程安全
 * setMonkey()方法在修改线程公共数据rung时，使用了线程安全的方法get()和setMonkey()，同时对size和direction的修改不会因为多线程而改变结果
 * removeMonkey()方法 使用了线程安全的方法get()和getMonkey()，同时对size和direction的修改加了线程锁
 * 这个类是线程安全类
 */
public class Ladder {
    private final int ID;
    private int size = 0;
    private String direction = null;
    private final List<Rung> rungs = Collections.synchronizedList(new ArrayList<>());

    Ladder(int rungNumber, int ID) {
        this.ID = ID;
        synchronized (rungs) {
            for (int i = 0; i < rungNumber; i++)
                rungs.add(new Rung());
        }
    }

    /**
     * @return 返回梯子的id值
     */
    int getID() {
        return ID;
    }

    /**
     * @return 当前梯子上的猴子的数量
     */
    int getSize() {
        return size;
    }

    /**
     * @return 当前梯子上猴子的行进方向
     */
    String getDirection() {
        return direction;
    }

    /**
     * 删除指定踏板上的某个猴子
     *
     * @param index 踏板的下标
     * @return boolean值，表示是否删除成功
     */
    boolean removeMonkey(int index) {
        if (index > rungs.size() - 1 || index < 0)
            return false;
        rungs.get(index).setMonkey(null);
        byte[] lock = new byte[0]; // 用了对代码块进行加锁
        synchronized (lock) {
            size--;
            if (size == 0)
                this.direction = null;
        }
        return true;
    }

    /**
     * 在梯子的指定台阶上放置猴子
     * 需要设置猴子的台阶必须原来是空的，否则无法添加成功，会返回false
     *
     * @param index  指定梯子上的台阶位置
     * @param monkey 需要设置的猴子
     * @return 是否设置成功的boolean值
     */
    boolean addMonkey(int index, Monkey monkey) {
        if (index > rungs.size() - 1 || index < 0)
            return false;
        if (rungs.get(index).setMonkey(monkey)) {
            this.size++;
            this.direction = monkey.getDirection();
            return true;
        }
        return false;
    }

    /**
     * 如果某个位置是空的，则表示这个台阶上没有猴子
     *
     * @return 这个梯子对应台阶上的猴子，是一个列表
     */
    List<Monkey> getMonkeys() {
        List<Monkey> monkeys = new ArrayList<>();
        synchronized (rungs) {
            for (Rung item : rungs)
                monkeys.add(item.getMonkey());
        }
        return monkeys;
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

/**
 * spec
 * AF:monkey->存储当前台阶上的猴子对象
 * RI: 无
 * safe from exposure:
 * 内部的monkey对象是private属性，外部无法直接访问和修改
 * monkey的修改方法经过了封装，外部无法利用类的内部实现
 * Thread safe:
 * getMonkey()方法自身就是原子操作，不会出现线程危险
 * setMonkey()方法加了锁，它只能被一个线程同时调用，是线程安全的
 * 这个类是线程安全的类
 */
class Rung {
    private Monkey monkey = null;

    Rung() {
    }

    /**
     * 得到当前台阶上的猴子对象
     *
     * @return 返回当前台阶上的猴子对象
     */
    Monkey getMonkey() {
        return monkey;
    }

    /**
     * 给当前阶梯设置一个新的猴子对象
     * 添加成功的条件：
     * 规定如果阶梯是空的才可以放新的猴子
     * 或者放进的猴子对象为空（将台阶置空）
     * 其他情况都会添加失败
     * 这个方法是线程安全的方法
     *
     * @param newMonkey 添加进来的新的猴子对象
     * @return 表示这个台阶的猴子是否添加成功
     */
    synchronized boolean setMonkey(Monkey newMonkey) {
        // 给空的台阶放置新的猴子
        if (this.monkey == null) {
            this.monkey = newMonkey;
            return true;
        } else
            return false;
    }
}

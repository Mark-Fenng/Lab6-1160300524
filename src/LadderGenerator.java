import java.util.ArrayList;
import java.util.List;

/**
 * spec：
 * 这个类是个静态类，所有的成员变量都是静态的
 * 初始时需要传入梯子的数量和每个梯子的台阶数，一次生成后就不能再更改
 * 在调用getLadders()函数之前必须在调用之前调用过一次generateLadders()，否则无法得到梯子
 */
class LadderGenerator {
    private static List<Ladder> ladders = null;

    /**
     * 根据给出的梯子的数量和梯子上的台阶的数量，生成参数要求的梯子
     *
     * @param ladderNumber 要求生成的梯子的数量 spec:大于0
     * @param pedalNumber  要求每个梯子上拥有的台阶的数量 spec:大于0
     */
    static void generateLadders(int ladderNumber, int pedalNumber) {
        if (ladders == null) {
            ladders = new ArrayList<>();
            for (int i = 0; i < ladderNumber; i++)
                ladders.add(new Ladder(pedalNumber, i + 1));
        }
    }

    /**
     * 返回生成的梯子对象列表
     * 返回一个新建的列表是为了防止外部改变梯子在列表中的顺序
     * 即外部改变梯子在列表中的顺序不会对此静态对象列表中的梯子对象的顺序产生影响
     *
     * @return 返回所有已经生成的梯子, 存储在一个列表中
     */
    static List<Ladder> getLadders() {
        if (ladders != null)
            return new ArrayList<>(ladders);
        return new ArrayList<>();
    }
}

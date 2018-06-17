import java.util.List;

public abstract class ladderChoice {
    /**
     * 传入所有的梯子和当前需要决策的一只猴子，返回决策后猴子应该上的梯子对象，如果猴子不应该上梯子,则返回null
     *
     * @param monkey     需要做上哪个梯子决策的猴子
     * @param ladderList 所有可供选择的梯子
     * @return 做出的决策的梯子，如果猴子不上梯子，就返回null
     */
    public abstract Ladder getLadder(Monkey monkey, List<Ladder> ladderList);

    /**
     * 用于产生随机的猴子过河策略
     *
     * @return 一种随机的过河策略对象，策略是已经实现的策略之一
     */
    static ladderChoice getChoice() {
        double choice = Math.random() * 3;
//        if (choice > 0 && choice <= 1)
//        return new firstStrategy();
//        else if (choice > 1 && choice <= 2)
//        return new secondStrategy();
//        else
        return new thirdStrategy();
    }
}

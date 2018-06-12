import java.util.List;

public interface ladderChoice {
    /**
     * 传入所有的梯子和当前需要决策的一只猴子，返回决策后猴子应该上的梯子对象，如果猴子不应该上梯子,则返回null
     *
     * @param monkey     需要做上哪个梯子决策的猴子
     * @param ladderList 所有可供选择的梯子
     * @return 做出的决策的梯子，如果猴子不上梯子，就返回null
     */
    public Ladder getLadder(Monkey monkey, List<Ladder> ladderList);
}

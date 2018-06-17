import java.util.List;

/**
 * 猴子挑选梯子的策略：猴子每次挑选空的梯子或者方向一致的梯子
 */
public class firstStrategy extends ladderChoice {
    @Override
    public Ladder getLadder(Monkey monkey, List<Ladder> ladderList) {
        Ladder emptyLadder = ladderList.stream().filter(item -> item.getSize() == 0).findFirst().orElse(null);
        if (emptyLadder == null) {
            for (Ladder item : ladderList) {
                if ((item.getDirection() == null || item.getDirection().equals(monkey.getDirection())) && item.getMonkeys().get(0) == null)
                    return item;
            }
        }
        return emptyLadder;
    }
}

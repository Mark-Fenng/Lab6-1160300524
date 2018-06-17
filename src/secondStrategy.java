import java.util.List;

/**
 * 猴子挑选梯子的策略：猴子每次挑选空的梯子
 */
public class secondStrategy implements ladderChoice {
    @Override
    public Ladder getLadder(Monkey monkey, List<Ladder> ladderList) {
        return ladderList
                .stream()
                .filter(item -> item.getSize() == 0)
                .findFirst()
                .orElse(null);
    }
}

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 猴子挑选梯子的策略：在梯子上所有猴子的方向和需要做决策的猴子一致的梯子中，挑选猴子数量最少的梯子
 */
public class thirdStrategy extends ladderChoice {

    @Override
    public Ladder getLadder(Monkey monkey, List<Ladder> ladderList) {
        return ladderList
                .stream()
                .filter(item -> (item.getDirection() == null || item.getDirection().equals(monkey.getDirection())) && item.getMonkeys().get(0) == null) // 寻找梯子上猴子数量最少的梯子
                .min(Comparator.comparingInt(Ladder::getSize))
                .orElse(null);
    }
}

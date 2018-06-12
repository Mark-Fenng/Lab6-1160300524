import java.util.List;

public class firstStrategy implements ladderChoice {
    @Override
    public Ladder getLadder(Monkey monkey, List<Ladder> ladderList) {
        Ladder emptyLadder = ladderList.stream().filter(item -> item.getSize() == 0).findFirst().orElse(null);
        if (emptyLadder == null) {
            for (Ladder item : ladderList) {
                if (item.getDirection().equals(monkey.getDirection()) && item.getMonkeys().get(0) == null)
                    return item;
            }
        }
        return emptyLadder;
    }
}

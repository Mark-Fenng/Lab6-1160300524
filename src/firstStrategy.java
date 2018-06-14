import java.util.List;

public class firstStrategy implements ladderChoice {
    @Override
    public Ladder getLadder(Monkey monkey, List<Ladder> ladderList) {
        Ladder emptyLadder = ladderList.stream().filter(item -> item.getSize() == 0).findFirst().orElse(null);
        if (emptyLadder == null) {
            for (Ladder item : ladderList) {
                if ((item.getDirection() == null || item.getDirection().equals(monkey.getDirection())) && item.getPedals().get(0).getMonkey() == null)
                    return item;
            }
        }
        return emptyLadder;
    }
}

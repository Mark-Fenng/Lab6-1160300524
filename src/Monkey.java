/**
 * spec:
 * AF:
 * RI:
 * safe from exposure:
 * thread safe:
 */
public class Monkey {
    private final int ID;
    private final boolean direction;
    private final int speed;

    Monkey(int ID, String direction, int v) {
        this.ID = ID;
        this.direction = direction.equals("L->R");
        this.speed = v;
    }


}

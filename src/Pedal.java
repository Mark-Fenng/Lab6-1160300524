/**
 * spec
 * AF:
 * RI:
 * safe from exposure:
 * Thread safe:
 */
public class Pedal {
    private Monkey monkey = null;

    Pedal() {
    }

    public Pedal(Monkey monkey) {
        this.monkey = monkey;
    }

    Monkey getMonkey() {
        return monkey;
    }

    void setMonkey(Monkey newMonkey) {
        this.monkey = newMonkey;
    }
}

/**
 * spec
 * AF:monkey->存储当前台阶上的猴子对象
 * RI: 无
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

    /**
     * 得到当前台阶上的猴子对象
     *
     * @return 返回当前台阶上的猴子对象
     */
    Monkey getMonkey() {
        return monkey;
    }

    /**
     * 给当前阶梯设置一个新的猴子对象
     *
     * @param newMonkey 添加进来的新的猴子对象
     */
    void setMonkey(Monkey newMonkey) {
        this.monkey = newMonkey;
    }
}

/**
 * spec
 * AF:monkey->存储当前台阶上的猴子对象
 * RI: 无
 * safe from exposure:
 * Thread safe:
 */
public class Rung {
    private Monkey monkey = null;

    Rung() {
    }

    public Rung(Monkey monkey) {
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
     * 添加成功的条件：
     * 规定如果阶梯是空的才可以放新的猴子
     * 或者放进的猴子对象为空（将台阶置空）
     * 其他情况都会添加失败
     *
     * @param newMonkey 添加进来的新的猴子对象
     * @return 表示这个台阶的猴子是否添加成功
     */
    synchronized boolean setMonkey(Monkey newMonkey) {
        // 给空的台阶放置新的猴子
        if (newMonkey == null) {
            this.monkey = null;
            return true;
        } else {
            // 将此台阶置空
            if (this.monkey == null) {
                this.monkey = newMonkey;
                return true;
            } else
                return false;
        }
    }
}

import com.ktw.duan.utils.ThreadDemo;
import org.junit.Test;

public class ThreadTest {
    @Test
    public void test() {
        ThreadDemo t1 = new ThreadDemo(true);
        ThreadDemo t2 = new ThreadDemo(false);
        new Thread(t1).start();
        new Thread(t2).start();
    }
}

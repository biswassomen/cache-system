import org.junit.jupiter.api.Test;
import prac.JunitPractice;

import static org.junit.jupiter.api.Assertions.*;

class JunitPracticeTest {

    @Test
    public void check() {
        var cache = new JunitPractice();
        assertEquals(4, cache.add(1, 3));
    }

    @Test
    public void checkException() {
        var cache = new JunitPractice();
        assertThrows(IllegalArgumentException.class, () -> {
           cache.add(Integer.MAX_VALUE, 1);
        });
    }
}
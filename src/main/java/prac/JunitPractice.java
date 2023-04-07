package prac;

public class JunitPractice {

    public int add(int a, int b) {
        if(a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Give proper number");
        }
        return a + b;
    }
}

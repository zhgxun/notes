package github.banana.demo;

public class ExTest {

    public static void main(String[] args) {
        int tryBlock;
        int catchBlock;
        int finallyBlock;
        int exitBlock;

        try {
            tryBlock = 1;
        } catch (Exception e) {
            catchBlock = 2;
        } finally {
            finallyBlock = 3;
        }
        exitBlock = 4;
    }
}

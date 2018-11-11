package github.banana.demo;

public class FooTest {

    public static void main(String[] args) {
        boolean flag = true;

        if (flag) {
            System.out.println("flag是一个布尔真");
        } else {
            System.out.println("flag不是一个布尔真");
        }

        if (flag == true) {
            System.out.println("flag实际是一个布尔真");
        } else {
            System.out.println("flag实际不是一个布尔真");
        }
    }
}

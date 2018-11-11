package github.banana.demo;

import org.springframework.stereotype.Service;

/**
 * 描述类的用途
 *
 * @version v1.0
 */
@Service
public class Swap {

    public static void main(String[] args) {
        String s = "This is a word";
        int length = s.length();
        for (int i = 0; i < length; i++) {
            System.out.println(s.charAt(i));
        }
    }
}

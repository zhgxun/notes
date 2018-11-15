package github.banana.sort;

import java.util.Random;

/**
 * 产生一个指定数量的随机数组
 */
public class RandList {

    private static Random seed = new Random();

    public static int[] getIntList(int length) {
        if (length <= 0) {
            throw new RuntimeException("Error");
        }

        int[] list = new int[length];
        for (int i = 0; i < length; i++) {
            list[i] = seed.nextInt(length);
        }

        return list;
    }
}

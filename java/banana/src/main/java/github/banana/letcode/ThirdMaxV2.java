package github.banana.letcode;

import java.util.TreeSet;

public class ThirdMaxV2 {

    public static void main(String[] args) {
        System.out.println(thirdMaxV2(new int[]{1, 2}));
    }

    public int thirdMax(int[] nums) {
        int length = nums.length;
        // 散列维护三个最大值, 最后来帅选
        // 默认升序排序, 故移除第一个散列值
        // 维护的第一个始终是第三大的元素
        // set比较直接好理解, 但是要维护有序集合, 效率不高
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < length; i++) {
            set.add(nums[i]);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }

        return set.size() < 3 ? set.last() : set.first();
    }

    public static int thirdMaxV2(int[] nums) {
        int length = nums.length;
        // 这点需要注意, 必须要用long类型, 否则有最小值边界
        // 第一大的数
        long one = nums[0];
        // 第二大的数
        long two = Long.MIN_VALUE;
        // 第三大的数
        long three = Long.MIN_VALUE;

        for (int i = 1; i < length; i++) {
            // 相同的值跳过
            if (nums[i] == one || nums[i] == two || nums[i] == three) {
                continue;
            }

            // 大小比较必须从最大到最小, 就不连带包含了
            // 比第一个大
            if (nums[i] > one) {
                three = two;
                two = one;
                one = nums[i];
            } else if (nums[i] > two) {
                three = two;
                two = nums[i];
            } else if (nums[i] > three) {
                three = nums[i];
            }
        }

        // 如果没有第三大元素存在则返回最大, 否则要返回第三大元素
        return three == Long.MIN_VALUE ? (int) one : (int) three;
    }
}

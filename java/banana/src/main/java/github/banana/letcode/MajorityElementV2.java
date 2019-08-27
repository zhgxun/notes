package github.banana.letcode;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementV2 {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        // 初始化: 定义两个候选人及其对应的票数
        int candidateA = nums[0];
        int candidateB = nums[0];
        int countA = 0;
        int countB = 0;
        // 遍历数组
        for (int num : nums) {
            // 投A
            if (num == candidateA) {
                countA++;
                continue;
            }

            //投B
            if (num == candidateB) {
                countB++;
                continue;
            }

            // 此时当前值和AB都不等, 检查是否有票数减为0的情况, 如果为0, 则更新候选人
            if (countA == 0) {
                candidateA = num;
                countA++;
                continue;
            }
            if (countB == 0) {
                candidateB = num;
                countB++;
                continue;
            }
            // 若此时两个候选人的票数都不为0, 且当前元素不投AB, 那么A, B对应的票数都要--
            countA--;
            countB--;
        }

        // 上一轮遍历找出了两个候选人, 但是这两个候选人是否均满足票数大于 N/3 仍然没法确定, 需要重新遍历, 确定票数
        countA = 0;
        countB = 0;
        for (int num : nums) {
            if (num == candidateA) {
                countA++;
            } else if (num == candidateB) {
                countB++;
            }
        }

        if (countA > nums.length / 3) {
            res.add(candidateA);
        }
        if (countB > nums.length / 3) {
            res.add(candidateB);
        }

        return res;
    }

    // 多次投票升级版
    public List<Integer> majorityElementV2(int[] nums) {
        // 首先由于众数个数必须多余数组元素的 n/3, 由于数组元素最多就3份, 故众数最多有2个, 最少有0个
        // 想到了投票的玩法, 假设存在两个众数A, B, 则对他们进行投票, 选出可能存在的众数A, B
        // 但是A, B 是否真的满足数量超过 n/3 个, 需要一次遍历累加和才能确定

        List<Integer> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }

        int a = nums[0], b = nums[0], aCount = 0, bCount = 0;
        int length = nums.length;

        for (int i = 0; i < length; i++) {

            // 如果a是众数, 则投1票
            if (nums[i] == a) {
                aCount++;
                continue;
            }

            // 如果b是众数, 则投1票
            if (nums[i] == b) {
                bCount++;
                continue;
            }

            // 此时都不是 a, b 的众数, 看票数是否为0
            // a的票数是否为0, 为0则重新选择众数
            if (aCount == 0) {
                a = nums[i];
                aCount++;
                continue;
            }
            // b的票数是否为0, 为0则重新选择众数
            if (bCount == 0) {
                b = nums[i];
                bCount++;
                continue;
            }

            // 否则此时两个众数都有效, 未能恢复为0, 但由于当前元素无法成为众数, 故投票次数降低1
            aCount--;
            bCount--;
        }

        // 经过前面的帅选, 有两个候选的众数a,b, 此时一次遍历来验证这两个众数是否有效
        aCount = bCount = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] == a) {
                aCount++;
                continue;
            }
            if (nums[i] == b) {
                bCount++;
            }
        }
        if (aCount > length / 3) {
            res.add(a);
        }
        if (bCount > length / 3) {
            res.add(b);
        }

        return res;
    }
}

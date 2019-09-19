package github.banana.letcode;

import github.banana.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class NextLargerNodes {

    public static void main(String[] args) {
        ListNode node = new ListNode(2);
        node.next = new ListNode(7);
        node.next.next = new ListNode(4);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(5);
//        node.next.next.next.next.next = new ListNode(2);
//        node.next.next.next.next.next.next = new ListNode(5);
//        node.next.next.next.next.next.next.next = new ListNode(1);
        System.out.println(Arrays.toString(nextLargerNodes(node)));
        System.out.println(Arrays.toString(nextLargerNodesV2(node)));
    }

    // 比较难以理解, 算是对暴力解法的一些完善
    // 1. 将链表转化为数组
    // 2. 对数组从后往前遍历找目标值
    // 3. 先比较当前值跟下一个值的关系, 如果当前当前值比下一个值小则下一个更大的值就是下一个值本身
    // 4. 然后看当前值跟目标值比较, 如果小于目标值则下一个更大的值就是下一个目标值本身
    // 5. 如果大于或者等于下一个值和目标值, 则看该值是否为最大, 最大值的下一个值为0, 否则在已遍历记录中重新找一个符合当前值小于下一个值的值作为目标值, 这个不好理解
    // 76个单元测试耗时307ms, 效率偏低下, 显然该种做法不是非常好的做法
    public static int[] nextLargerNodes(ListNode head) {
        // 将链表转化为数组, 数组可以随机访问, 链表比较难操作
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }

        int size = nums.size();

        // 保存下一个最大值数组
        int[] result = new int[size];

        // 最后一个元素的下一个最大值为0
        result[size - 1] = 0;
        int max = nums.get(size - 1);

        for (int i = size - 2; i >= 0; i--) {
            // 当前值比下一个值要小
            if (nums.get(i) < nums.get(i + 1)) {
                result[i] = nums.get(i + 1);
            } else if (nums.get(i) < result[i + 1]) {
                // 当前值比目标值要小
                result[i] = result[i + 1];
            } else {
                // 当前值是最大值不存在比它小的值
                if (nums.get(i) > max) {
                    result[i] = 0;
                } else {
                    for (int j = i + 1; j < size; j++) {
                        if (nums.get(i) < nums.get(j)) {
                            result[i] = nums.get(j);
                            break;
                        }
                    }
                }
            }

            // 记录历史最大值
            if (max < nums.get(i)) {
                max = nums.get(i);
            }
        }

        return result;
    }

    // 显然上一种解法虽然直接但是效率太差, 肯定还有更好的方法
    // 效率稍微好了一点点, 但还是不够好
    public static int[] nextLargerNodesV2(ListNode head) {
        int count = 0;
        List<Integer> result = new ArrayList<>();
        Stack<Map<Integer, Integer>> stack = new Stack<>();

        // 遍历到最后一个元素
        while (head != null) {
            // 默认添加到末尾, 顺序刚好对, 占位
            result.add(0);

            // 栈不为空
            while (!stack.isEmpty()) {
                Map<Integer, Integer> top = stack.peek();
                Integer value = (Integer) top.keySet().toArray()[0];
                // 当前值大于栈顶元素的值时更新栈顶元素的下一个最大值为当前值
                if (head.val > value) {
                    Integer key = top.get(value);
                    result.set(key, head.val);
                    stack.pop();
                } else {
                    // 无法继续出栈时直接终止
                    break;
                }
            }

            // 入哈希, key为当前值, value为顺序索引位置
            Map<Integer, Integer> temp = new HashMap<>();
            temp.put(head.val, count++);
            // 每次其实只入一个元素, 一个能记录key->value的数据结构均可
            stack.push(temp);

            head = head.next;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // 这个是解法中比较优秀的解法
    // 其实是解法1的升级
    public static int[] nextLargerNodesV3(ListNode head) {
        ListNode cur = head;

        // 计算链表的长度
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        // 将链表转化为数组nums
        cur = head;
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = cur.val;
            cur = cur.next;
        }

        // 存储下一个更大的值
        int[] result = new int[count];
        result[count - 1] = 0;
        for (int i = count - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                // 1. 优先比较当前元素跟下一个元素, 如果当前元素已经小于后一个元素则当前元素的下一个更大的元素就是当前元素的下一个元素
                result[i] = nums[i + 1];
            } else if (nums[i] == nums[i + 1]) {
                // 2. 如果当前元素跟后一个元素相等, 则当前元素的下一个更大的元素就是上一次记录的最大元素
                result[i] = result[i + 1];
            } else {
                // 3. 当前元素大于后一个元素, 此时无法知道后一个更大的元素是什么, 需要继续遍历已遍历过的元素
                for (int j = i + 1; j < count; j++) {
                    // 依然是首次遇到比它大的元素就是最接近它的下一个更大的元素
                    if (nums[j] > nums[i]) {
                        result[i] = nums[j];
                        break;
                    }
                    // 否则遍历到0则当前元素的最大元素也是0
                    if (result[j] == 0) {
                        result[i] = 0;
                        break;
                    }
                }
            }
        }

        return result;
    }
}

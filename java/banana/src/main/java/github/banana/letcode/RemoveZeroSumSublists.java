package github.banana.letcode;

import github.banana.common.ListNode;
import github.banana.common.PrintListNode;

import java.util.HashMap;
import java.util.Map;

public class RemoveZeroSumSublists {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(-3);
        node.next.next.next.next = new ListNode(-2);
        node.next.next.next.next.next = new ListNode(-1);

        PrintListNode.print(removeZeroSumSublists(node));
    }

    // 有一个原则是对于前缀和相同的项, 中间的部分是可以消除掉的
    // 相同前缀和的两个节点, 第一个节点的下一个位置指向首次相同前缀和时的下一个节点
    // 借助哈希存储前缀和
    // 一次遍历
    // 105个单元测试耗时10ms, 效率中等偏上, 未能到最优
    public static ListNode removeZeroSumSublists(ListNode head) {
        // 保存前缀和
        Map<Integer, ListNode> map = new HashMap<>();

        // 头哑节点
        ListNode node = new ListNode(0);
        node.next = head;

        // 初始化前缀和
        map.put(0, node);

        ListNode temp = node.next;
        Integer sum = 0, tempSum;

        while (temp != null) {
            // 累加前缀和
            sum += temp.val;
            // 如果已经存在相同的前缀和, 最先出现的前缀和, 其实代表了链表该位置, 下次从改位置继续遍历链表
            // 这也是为什么哈希存储为链表的原因, 确实比较不容易想出来这个解法
            if (map.containsKey(sum)) {
                // 记录上一个相同前缀和的下一个节点
                ListNode curTemp = map.get(sum).next;
                // 将该节点的下一个节点更新为当前节点的下一个节点
                map.get(sum).next = temp.next;

                // 删除哈希中不需要的前缀和项, 即上一次节点更新后呗丢弃的节点的前缀和后续不再需要
                tempSum = sum;
                // temp 为当前遇见跟前面有相同前缀和的项, 当前项不存储, 上一次的前缀和需要保留
                while (curTemp != temp) {
                    tempSum += curTemp.val;
                    map.remove(tempSum);
                    curTemp = curTemp.next;
                }
            } else {
                // 否则将前缀和为当前值的节点记录在哈希中
                map.put(sum, temp);
            }

            temp = temp.next;
        }

        return node.next;
    }
}

package github.banana.letcode;

import github.banana.common.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyRandomList {

    public static void main(String[] args) {
        Node a = new Node(1, null, null);
        Node b = new Node(2, null, null);
        b.random = b;
        a.next = b;
        a.random = b;

        System.out.println(a.random.val);
        System.out.println(b.random.val);

        Node c = copyRandomList(a);
        System.out.println(c.random.val);
        System.out.println(c.next.random.val);
    }

    public static Node copyRandomList(Node head) {
        // 有空的测试用例
        if (head == null) {
            return null;
        }

        // 记录拷贝后的新节点位置关系
        List<Node> nodeList = new ArrayList<>();

        // 对原始链表做一个地址到位置的映射
        Map<Node, Integer> addressMap = new HashMap<>();

        // 遍历链表
        Node temp = head;
        int i = 0;
        while (temp != null) {
            // 记录新的节点位置关系
            nodeList.add(new Node(temp.val, null, null));

            // 记录原始地址映射位置关系
            addressMap.put(temp, i);

            // 链表遍历
            temp = temp.next;
            i++;
        }

        i = 0;
        temp = head;
        while (temp != null) {
            // 将链表的next指针互相连接, 排除最后一个节点
            if (temp.next != null) {
                nodeList.get(i).next = nodeList.get(i + 1);
            }

            // 如果有原始随机指针则需要进行位置查找
            // 最后一个节点可以有自己的随机指针
            if (temp.random != null) {
                int id = addressMap.get(temp.random);
                nodeList.get(i).random = nodeList.get(id);
            }

            temp = temp.next;
            i++;
        }

        return nodeList.get(0);
    }
}

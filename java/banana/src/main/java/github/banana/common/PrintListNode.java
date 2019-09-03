package github.banana.common;

/**
 * 打印链表
 */
public class PrintListNode {

    public static void print(ListNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.val);
            sb.append("->");
            node = node.next;
        }
        String content = sb.toString();
        System.out.println(content.substring(0, content.lastIndexOf("->")));
    }
}

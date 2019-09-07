package github.banana.common;

/**
 * 打印链表
 */
public class PrintListNode {

    public static void print(ListNode node) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (node != null) {
            sb.append(node.val);
            sb.append("->");
            node = node.next;
            // 避免大连表打印
            if (i++ >= 10) {
                break;
            }
        }
        String content = sb.toString();
        System.out.println(content.substring(0, content.lastIndexOf("->")));
    }
}

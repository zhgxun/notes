package github.banana.letcode;

public class DeleteDuplicates {

    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        int count = 0;
        ListNode slow = head, fast = head.next;
        while (slow != null) {
            count++;
            if (slow.val != fast.val) {
                if (count > 1) {
                    slow.next = fast;
                    count = 0;
                }
                slow = slow.next;
            }
            fast = fast.next;
        }

        return head;
    }
}

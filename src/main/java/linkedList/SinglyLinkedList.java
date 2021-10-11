package linkedList;

import java.util.List;

/**
 * 单链表
 *
 * @author zhaoyc
 * @since 2021-10-09
 */
public class SinglyLinkedList {

    /**
     * 单链表反转--迭代
     *
     * @param head
     *
     * @return linkedList.ListNode
     * @author zhaoyc
     * @since 2021-10-09
     */
    public ListNode reverseLinkedList1(ListNode head) {
        // 前驱节点初始位null
        ListNode prev = null;
        // 当前节点为cur
        ListNode cur = head;
        // tmp暂存下一个节点
        ListNode tmp;
        while (cur != null) {
            // 获取下一个节点
            tmp = cur.next;
            // 把当前节点的next指针指向前驱结点
            cur.next = prev;
            // 前驱节点指针指向当前节点
            prev = cur;
            // 当前节点指针指向其下一个节点
            cur = tmp;
        }
        // 返回prev
        return prev;
    }

    /**
     * 单链表反转--递归
     *
     * @param head
     *
     * @return linkedList.ListNode
     * @author zhaoyc
     * @since 2021-10-09
     */
    public ListNode reverseLinkedList2(ListNode head) {
        // 递归终止条件是当前节点为空，或者当前节点的下一个节点为空
        // 毫无疑问，在这道题中返回的就是单链表的尾节点
        if (head == null || head.next == null) {
            return head;
        }
        // 将当前节点之后的子链表反转任务交给子过程处理,不要陷入细节
        // 只需要知道子过程可以完成子链表的反转就够了
        ListNode last = reverseLinkedList2(head.next);
        // 经过上面的反转，子链表已经反转完成，接下来要做的就是处理子链表和当前节点的关系
        // 下面两步配合示意图理解
        head.next.next = head;
        // 防止链表循环，需要将head.next置为空
        head.next = null;
        // 每一层递归函数返回的都是p，也就是初始链表的尾节点
        return last;
    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     *
     * @return linkedList.ListNode
     * @author zhaoyc
     * @since 2021-10-11
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

}

package leetcode.editor.cn;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 2212 👎 0

import java.util.ArrayList;
import java.util.LinkedList;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //该解法是先从头遍历到尾获取链表节点总数，再从头遍历删掉倒数第n个节点
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            //声明originHead变量指向第一个节点
            ListNode originHead = head;
            //获取链表节点总数
            int num = 1;
            while (head.next != null) {
                num++;
                head = head.next;
            }
            //特殊情况处理：n==num,即要删掉的是第一个节点
            if (n == num) {
                return originHead.next;
            }

            //重置head开始第二次遍历以删掉倒数第n个节点，下面逻辑无法处理删掉的倒数第n个节点正好是第一个节点这种情况，所以在上面作了特殊情况处理。
            head = originHead;
            int i = 1;
            while (i + n < num) {
                head = head.next;
                i++;
            }
            ListNode temp = head.next;
            head.next = temp.next;
            return originHead;
        }
    }

    //解法二：双指针解法（遍历一遍即可）
    class Solution1 {

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
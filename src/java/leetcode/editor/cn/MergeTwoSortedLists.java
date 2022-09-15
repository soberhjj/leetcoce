package leetcode.editor.cn;

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 2673 👎 0


public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(1, null);
        solution.mergeTwoLists(node1, node2);

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    public static class ListNode {
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

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            } else if (list2 == null) {
                return list1;
            }
            ListNode reshead = (list1.val < list2.val) ? list1 : list2;
            ListNode resTail = reshead;
            ListNode p1 = (reshead.equals(list1)) ? list1.next : list1;
            ListNode p2 = (reshead.equals(list2)) ? list2.next : list2;
            while (p1 != null && p2 != null) {
                if (p1.val <= p2.val) {
                    resTail.next = p1;
                    resTail = p1;
                    p1 = p1.next;
                } else {
                    resTail.next = p2;
                    resTail = p2;
                    p2 = p2.next;
                }
            }
            if (p1!=null){
                while (p1 != null){
                    resTail.next = p1;
                    resTail = p1;
                    p1 = p1.next;
                }
            }else if (p2!=null){
                while (p2 != null){
                    resTail.next = p2;
                    resTail = p2;
                    p2 = p2.next;
                }
            }
            return reshead;
        }
    }

    //解法2：递归
    class Solution1 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
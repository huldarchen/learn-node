//给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
//
// 示例 2：
//
//
//输入：head = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 100] 内
// 0 <= Node.val <= 100
//
// Related Topics 递归 链表 👍 1191 👎 0


package leetcode.editor.cn;

public class SwapNodesInPairs {

  public static void main(String[] args) {
    Solution solution = new SwapNodesInPairs().new Solution();
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
  class Solution {

    public ListNode swapPairs(ListNode head) {
      // 健壮性判断
      if (head == null || head.next == null) {
        return head;
      }
      ListNode newHead = head.next;
      head.next = swapPairs(newHead.next);
      newHead.next = head;
      return newHead;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

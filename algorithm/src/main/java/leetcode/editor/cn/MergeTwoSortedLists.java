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
// Related Topics 递归 链表 👍 2131 👎 0


package leetcode.editor.cn;

public class MergeTwoSortedLists {

  public static void main(String[] args) {
    Solution solution = new MergeTwoSortedLists().new Solution();
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
      // 特殊情况处理
      if (list1 == null) {
        return list2;
      }
      if (list2 == null) {
        return list1;
      }
      ListNode dump = new ListNode(-1);
      ListNode next = dump;
      while (list1 != null && list2 != null) {
        // 如果list1的值小 先接list1的节点
        if (list1.val < list2.val) {
          next.next = list1;
          next = next.next;
          list1 = list1.next;
        } else {
          next.next = list2;
          next = next.next;
          list2 = list2.next;
        }
      }
      // 如果一个结束,剩余一个还没有结束
      if (list1 != null) {
        next.next = list1;
      }
      if (list2 != null) {
        next.next = list2;
      }
      return dump.next;
    }
  }

  // class ListNode {
  //
  //   int val;
  //   ListNode next;
  //
  //   ListNode() {
  //   }
  //
  //   ListNode(int val) {
  //     this.val = val;
  //   }
  //
  //   ListNode(int val, ListNode next) {
  //     this.val = val;
  //     this.next = next;
  //   }
  // }
  //leetcode submit region end(Prohibit modification and deletion)

}

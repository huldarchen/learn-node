 //给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。
//
//
//
// 示例 1：
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
//
//
// 示例 2：
//
// 输入：lists = []
//输出：[]
//
//
// 示例 3：
//
// 输入：lists = [[]]
//输出：[]
//
//
//
//
// 提示：
//
//
// k == lists.length
// 0 <= k <= 10^4
// 0 <= lists[i].length <= 500
// -10^4 <= lists[i][j] <= 10^4
// lists[i] 按 升序 排列
// lists[i].length 的总和不超过 10^4
//
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 1683 👎 0


  package leetcode.editor.cn;
  public class MergeKSortedLists{
      public static void main(String[] args) {
           Solution solution = new MergeKSortedLists().new Solution();
      }
          //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
      // return mergeKListsOfTwo(lists);
      return mergeKListOfDivideConquer(lists, 0, lists.length - 1);
    }

    // 使用分治:
    // 递归
    private ListNode  mergeKListOfDivideConquer(ListNode[] lists, int l, int r) {
      if (l == r) {
        return lists[l];
      }
      if (l > r) {
        return null;
      }
      int mid = (l + r) >> 1;

      return mergeTwoSortedList(
              mergeKListOfDivideConquer(lists, l, mid),
              mergeKListOfDivideConquer(lists, mid + 1, r)
              );
    }


    // 使用22排序
    private ListNode mergeKListsOfTwo(ListNode[] lists) {
      if (lists == null || lists.length == 0) {
        return null;
      }

      ListNode tmp = mergeTwoSortedList(lists[0], null);
      for (int i = 1; i < lists.length; i++) {
        tmp = mergeTwoSortedList(tmp, lists[i]);
      }
      return tmp;
    }
    private ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
      // 特殊情况处理
      if (l1 == null) {
        return l2;
      }
      if (l2 == null) {
        return l1;
      }
      ListNode dump = new ListNode(-1);
      ListNode next = dump;
      while (l1 != null && l2 != null) {
        // l1小
        if (l1.val < l2.val) {
          next.next = l1;
          l1 = l1.next;
          next = next.next;
        } else {
          next.next = l2;
          l2 = l2.next;
          next = next.next;
        }
      }
      if (l1 != null) {
        next.next = l1;
      }

      if (l2 != null) {
        next.next = l2;
      }

      return dump.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

  }

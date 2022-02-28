//给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
//
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
//
// 请找到和最小的 k 个数对 (u1,v1), (u2,v2) ... (uk,vk) 。
//
//
//
// 示例 1:
//
//
//输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
//输出: [1,2],[1,4],[1,6]
//解释: 返回序列中的前 3 对数：
//     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
//
//
// 示例 2:
//
//
//输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
//输出: [1,1],[1,1]
//解释: 返回序列中的前 2 对数：
//     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
//
//
// 示例 3:
//
//
//输入: nums1 = [1,2], nums2 = [3], k = 3
//输出: [1,3],[2,3]
//解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
//
//
//
//
// 提示:
//
//
// 1 <= nums1.length, nums2.length <= 10⁴
// -10⁹ <= nums1[i], nums2[i] <= 10⁹
// nums1, nums2 均为升序排列
// 1 <= k <= 1000
//
// Related Topics 数组 堆（优先队列） 👍 258 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindKPairsWithSmallestSums {

  public static void main(String[] args) {
    Solution solution = new FindKPairsWithSmallestSums().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      List<List<Integer>> res = new ArrayList<>();
      // 小顶堆
      Queue<int[]> heap = new PriorityQueue<>((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);

      // 从第一个数组中取 k个数与nums2[0]组成数对
      if (nums2.length > 0) {
        for (int i = 0; i < k && i < nums1.length; i++) {
          heap.offer(new int[] {i, 0});
        }
      }

      // 逐个出堆,下标迁移
      while (k-- > 0 && !heap.isEmpty()) {
        int[] ids = heap.poll();
        res.add(Arrays.asList(nums1[ids[0]], nums2[ids[1]]));
        if (ids[1] < nums2.length - 1) {
          heap.offer(new int[] {ids[0], ids[1] + 1});
        }
      }
      return res;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

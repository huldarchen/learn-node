//给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i
//- j) <= k 。如果存在，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1], k = 3
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,0,1,1], k = 1
//输出：true
//
// 示例 3：
//
//
//输入：nums = [1,2,3,1,2,3], k = 2
//输出：false
//
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
// 0 <= k <= 10⁵
//
// Related Topics 数组 哈希表 滑动窗口 👍 377 👎 0


package leetcode.editor.cn;


import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateIi {

  public static void main(String[] args) {
    Solution solution = new ContainsDuplicateIi().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
      // 滑动窗口: 维护一个K长度的窗口, 遍历数组往里面增加元素,当存在重复元素时候则找到
      Set<Integer> winds = new HashSet<>();
      for (int i = 0; i < nums.length; i++) {
        // 窗口超过k,移除元素
        if (i > k) {
          winds.remove(nums[i - k - 1]);
        }
        // 如果包含重复元素,则返回true set的add返回Boolean,即set中不包含改元素 返回true, 包含则返回false
        if (!winds.add(nums[i])) {
          return true;
        }
      }
      return false;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

//给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
//
// 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
//
//
//
// 示例 1：
//
//
//输入：nums = [3,6,1,0]
//输出：1
//解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,4]
//输出：-1
//解释：4 没有超过 3 的两倍大，所以返回 -1 。
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：0
//解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 50
// 0 <= nums[i] <= 100
// nums 中的最大元素是唯一的
//
// Related Topics 数组 排序 👍 109 👎 0


package leetcode.editor.cn;

public class LargestNumberAtLeastTwiceOfOthers {

  public static void main(String[] args) {
    Solution solution = new LargestNumberAtLeastTwiceOfOthers().new Solution();
    solution.dominantIndex(new int[]{0, 0, 0, 1});
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    public int dominantIndex(int[] nums) {
      if (nums == null || nums.length <= 1) {
        return 0;
      }
      int firstMaxIndex = 0;
      int secondMaxIndex = 1;

      // 初始化下标
      if (nums[0] < nums[1]) {
        firstMaxIndex = 1;
        secondMaxIndex = 0;
      }
      // 从第3个下标开始遍历
      for (int i = 2; i < nums.length; i++) {
        if (nums[i] > nums[firstMaxIndex]) {
          // 这里先进行替换第一个大的下标
          secondMaxIndex = firstMaxIndex;
          firstMaxIndex = i;
          // 根据题目 最大值为100 次大值超过100 就退出
          if (nums[secondMaxIndex] > 50) {
            return -1;
          }
        } else if (nums[i] > nums[secondMaxIndex]) {
          secondMaxIndex = i;
          if (nums[secondMaxIndex] > 50) {
            return -1;
          }
        }
      }
      // 找到最大值和次大值,比较返回
      if (nums[firstMaxIndex] >= 2 * nums[secondMaxIndex]) {
        return firstMaxIndex;
      }
      // 没有找到
      return -1;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

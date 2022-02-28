//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
//
//
//
// 示例 1：
//
//
//输入：timePoints = ["23:59","00:00"]
//输出：1
//
//
// 示例 2：
//
//
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
//
//
//
//
// 提示：
//
//
// 2 <= timePoints <= 2 * 10⁴
// timePoints[i] 格式为 "HH:MM"
//
// Related Topics 数组 数学 字符串 排序 👍 131 👎 0


package leetcode.editor.cn;

import java.util.Collections;
import java.util.List;

public class MinimumTimeDifference {

  public static void main(String[] args) {
    Solution solution = new MinimumTimeDifference().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    public int findMinDifference(List<String> timePoints) {
      // 抽屉原理/鸽巢原理 n + 1 个数 n个抽屉,必然有1个抽屉是2个数
      if (timePoints.size() > 24 * 60) {
        return 0;
      }
      // 按照字典顺序排序, 底层用的是归并排序
      // TODO 这里排序 可以自己实现
      Collections.sort(timePoints);

      // 记录头时间
      int t0 = getMinutes(timePoints.get(0));
      // 记录前一个节点
      int pre = t0;
      // 遍历列表
      int res = Integer.MAX_VALUE;
      for (int i = 1; i < timePoints.size(); i++) {
        int minutes = getMinutes(timePoints.get(i));
        res = Math.min(res, minutes - pre);
        pre = minutes;
      }

      // 判断首位
      res = Math.min(res, t0 + 24 * 60 - pre);
      return res;
    }

    private int getMinutes(String time) {

      return ((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0')) * 60 +
              (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

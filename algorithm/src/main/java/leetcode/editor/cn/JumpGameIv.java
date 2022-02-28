//给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
//
// 每一步，你可以从下标 i 跳到下标：
//
//
// i + 1 满足：i + 1 < arr.length
// i - 1 满足：i - 1 >= 0
// j 满足：arr[i] == arr[j] 且 i != j
//
//
// 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
//
// 注意：任何时候你都不能跳到数组外面。
//
//
//
// 示例 1：
//
// 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
//输出：3
//解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
//
//
// 示例 2：
//
// 输入：arr = [7]
//输出：0
//解释：一开始就在最后一个元素处，所以你不需要跳跃。
//
//
// 示例 3：
//
// 输入：arr = [7,6,9,6,9,6,9,7]
//输出：1
//解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
//
//
// 示例 4：
//
// 输入：arr = [6,1,9]
//输出：2
//
//
// 示例 5：
//
// 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= arr.length <= 5 * 10^4
// -10^8 <= arr[i] <= 10^8
//
// Related Topics 广度优先搜索 数组 哈希表 👍 154 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class JumpGameIv {

  public static void main(String[] args) {
    Solution solution = new JumpGameIv().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    public int minJumps(int[] arr) {
      // 使用广度优先
      // 用来存储相同元素
      Map<Integer, List<Integer>> eqMap = new HashMap<>();
      for (int i = 0; i < arr.length; i++) {
        eqMap.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
      }
      // 广度优先的队列
      Queue<int[]> queue = new LinkedList<>();
      queue.add(new int[]{0, 0});
      // 用来记录访问
      boolean[] visited = new boolean[arr.length];
      visited[0] = true;

      while (!queue.isEmpty()) {
        // 出队列
        int[] temp = queue.poll();
        int index = temp[0];
        int step = temp[1];
        if (index == arr.length - 1) {
          return step;
        }
        step++;
        // 向前走
        if (index + 1 < arr.length && !visited[index + 1]) {
          visited[index + 1] = true;
          queue.offer(new int[]{index + 1, step});
        }

        // 向后走
        if (index - 1 >= 0 && !visited[index - 1]) {
          visited[index - 1] = true;
          queue.offer(new int[]{index - 1, step});
        }

        // 向相同元素走
        if (eqMap.get(arr[index]) != null) {
          for (int i : eqMap.get(arr[index])) {
            if (!visited[i]) {
              if (i == arr.length - 1) {
                return step;
              }
              visited[i] = true;
              queue.offer(new int[]{i, step});
            }
          }
          eqMap.remove(arr[index]);
        }

      }
      return -1;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

//在一个 10⁶ x 10⁶ 的网格中，每个网格上方格的坐标为 (x, y) 。
//
// 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表
//，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
//
// 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
//
// 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
//
//
//
// 示例 1：
//
//
//输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
//输出：false
//解释：
//从源方格无法到达目标方格，因为我们无法在网格中移动。
//无法向北或者向东移动是因为方格禁止通行。
//无法向南或者向西移动是因为不能走出网格。
//
// 示例 2：
//
//
//输入：blocked = [], source = [0,0], target = [999999,999999]
//输出：true
//解释：
//因为没有方格被封锁，所以一定可以到达目标方格。
//
//
//
//
// 提示：
//
//
// 0 <= blocked.length <= 200
// blocked[i].length == 2
// 0 <= xi, yi < 10⁶
// source.length == target.length == 2
// 0 <= sx, sy, tx, ty < 10⁶
// source != target
// 题目数据保证 source 和 target 不在封锁列表内
//
// Related Topics 深度优先搜索 广度优先搜索 数组 哈希表 👍 130 👎 0


package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class EscapeALargeMaze {

  public static void main(String[] args) {
    Solution solution = new EscapeALargeMaze().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    int[][] blocked;
    int[] source;
    int[] target;
    int maxPoint;
    // 4个方向
    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    Set<Long> blockSet = new HashSet<>();

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
      this.blocked = blocked;
      this.source = source;
      this.target = target;
      // TODO 为什么是这个值呢
      this.maxPoint = (blocked.length + 1) * (blocked.length + 2) / 2;

      // 将block块,放到哈希表,后面遍历地图会用到
      for (int i = 0; i < blocked.length; i++) {
        blockSet.add(blocked[i][0] * (long) 1e6 + blocked[i][1]);
      }

      // 分别从起点和终点BFS遍历地图

      return bfs(source[0], source[1], true) && bfs(target[0], target[1], false);


    }

    private boolean bfs(int x, int y, boolean isSource) {
      // visited存放已经访问过的点
      Set<Long> visited = new HashSet<>();
      // BFS过程的队列
      Deque<Long> d = new ArrayDeque<>();
      // 将起点放入到队列中,并在visited中记录
      d.add(x * (long) 1e6 + y);
      visited.add(x * (long) 1e6 + y);

      while (!d.isEmpty() && (visited.size() <= maxPoint)) {
        Long p = d.pollFirst();
        // 当前节点的 x, y
        long px = (long) (p / 1e6);
        long py = (long) (p - px * 1e6);

        // 从4个方向进行BFS
        for (int i = 0; i < dir.length; i++) {
          // 下一个 x, y
          long nx = px + dir[i][0];
          long ny = py + dir[i][1];

          // 判断出界 或者 是block块
          if (nx < 0 || nx >= 1e6 || ny < 0 || ny >= 1e6 || blockSet.contains(nx * (long) 1e6 + ny)) {
            continue;
          }

          // 已经访问过
          if (visited.contains(nx * (long)1e6 + ny)) {
            continue;
          }

          // 到达终点
          if (isArrive(isSource, nx, ny)) {
              return true;
          }

          // 访问后加入队列中并标记visited
          d.add(nx * (long) 1e6 + ny);
          visited.add(nx * (long) 1e6 + ny);
        }
      }
      // 最后查看
      // 判断访问过点的数量
      return visited.size() > maxPoint;
    }

    private boolean isArrive(boolean isSource, long x, long y) {

      if (isSource && x == target[0] && y == target[1]) {
        return true;
      }

      if ((!isSource) && x == source[0] && y == source[1]) {
        return true;
      }
      return false;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

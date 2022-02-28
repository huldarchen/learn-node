//给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
//
//
// 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
// 每个元音 'a' 后面都只能跟着 'e'
// 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
// 每个元音 'i' 后面 不能 再跟着另一个 'i'
// 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
// 每个元音 'u' 后面只能跟着 'a'
//
//
// 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
//
//
//
// 示例 1：
//
// 输入：n = 1
//输出：5
//解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
//
//
// 示例 2：
//
// 输入：n = 2
//输出：10
//解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
//
//
// 示例 3：
//
// 输入：n = 5
//输出：68
//
//
//
// 提示：
//
//
// 1 <= n <= 2 * 10^4
//
// Related Topics 动态规划 👍 77 👎 0


package leetcode.editor.cn;

public class CountVowelsPermutation {

  public static void main(String[] args) {
    Solution solution = new CountVowelsPermutation().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    long mod = (long) 1e9 + 7;

    public int countVowelPermutation(int n) {
      // 如果只有1个元音字母 则直接返回
      if (n == 1) {
        return 5;
      }
      // 5个元音字母 a e i o u
      //           0 1 2 3 4
      // a 的前面只能跟 e i u = 1 2 4
      // e 的前面只能 a i = 0 2
      // i 的前面只能跟 e o =  1 3
      // o 的前面只能跟 i = 2
      // u 的前面只能跟 i o =  2 3
      // 从后往前DP
      // DP 状态机
      long[][] dp = new long[n + 1][5]; // 5:以5个元音字母结尾的
      // 初始化状态机
      for (int i = 0; i < dp[0].length ; i++) {
        dp[1][i] = 1;
      }

      long sum = 0L;
      for (int i = 2; i < n + 1; i++) {
        dp[i][0] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]) % mod;
        dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
        dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % mod;
        dp[i][3] = dp[i - 1][2] % mod;
        dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
      }

      // 求和
      for (int i = 0; i < 5; i++) {
        sum = (sum + dp[n][i]) % mod;
      }

      return (int) sum;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

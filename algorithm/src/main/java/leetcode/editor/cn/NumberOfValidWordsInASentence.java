//句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。每个句子
//可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
//
// 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
//
//
// 仅由小写字母、连字符和/或标点（不含数字）。
// 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
// 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
//
//
// 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
//
// 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
//
//
//
// 示例 1：
//
// 输入：sentence = "cat and  dog"
//输出：3
//解释：句子中的有效单词是 "cat"、"and" 和 "dog"
//
//
// 示例 2：
//
// 输入：sentence = "!this  1-s b8d!"
//输出：0
//解释：句子中没有有效单词
//"!this" 不是有效单词，因为它以一个标点开头
//"1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
//
//
// 示例 3：
//
// 输入：sentence = "alice and  bob are playing stone-game10"
//输出：5
//解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
//"stone-game10" 不是有效单词，因为它含有数字
//
//
// 示例 4：
//
// 输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
//输出：6
//解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
//
//
//
//
// 提示：
//
//
// 1 <= sentence.length <= 1000
// sentence 由小写英文字母、数字（0-9）、以及字符（' '、'-'、'!'、'.' 和 ','）组成
// 句子中至少有 1 个 token
//
// Related Topics 字符串 👍 28 👎 0


package leetcode.editor.cn;

public class NumberOfValidWordsInASentence {

  public static void main(String[] args) {
    Solution solution = new NumberOfValidWordsInASentence().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {

    public int countValidWords(String sentence) {
      // 双指针
      int l = 0, r = 0;
      int length = sentence.length();

      int rest = 0;
      while (true) {
        while (l < length && sentence.charAt(l) == ' ') {
          // 是空格 左指针右移1位
          l++;
        }

        if (l >= length) {
          // 左指针超过字符串长度,直接退出
          break;
        }
        r = l + 1;
        while (r < length && sentence.charAt(r) != ' ') {
          r++;
        }
        // 截取了一个组字符串,判断token是否符合单词规则
        if (isValid(sentence.substring(l, r))) {
          rest++;
        }
        // 左指针改变
        l = r + 1;

      }
      return rest;
    }

    private boolean isValid(String word) {
      int n = word.length();
      boolean hasHyphens = false;
      for (int i = 0; i < n; i++) {
        if (Character.isDigit(word.charAt(i))) {
          return false;
        } else if (word.charAt(i) == '-') {
          if (hasHyphens == true || i == 0 || i == n - 1 || !Character.isLetter(word.charAt(i - 1)) || !Character.isLetter(word.charAt(i + 1))) {
            return false;
          }
          hasHyphens = true;
        } else if (word.charAt(i) == '!' || word.charAt(i) == '.' || word.charAt(i) == ',') {
          if (i != n - 1) {
            return false;
          }
        }
      }
      return true;
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)

}

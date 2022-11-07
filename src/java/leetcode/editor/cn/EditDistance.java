package leetcode.editor.cn;

//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 2614 👎 0

/**
 * 思路:动态规划。参考题解：https://leetcode.cn/problems/edit-distance/solutions/6455/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/
 */
public class EditDistance {
    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            //定义dp,dp[i][j]代表 word1 中前 i 个字符，变换到 word2 中前 j 个字符，最短需要操作的次数
            int dp[][] = new int[word1.length() + 1][word2.length() + 1];

            //初始化
            dp[0][0] = 0;//空的word1变换到空的word2所需的操作次数为0，这行删掉也行，因为int数组的元素默认值就是0
            //从空的word1变换到前j个字符的word2所需的最短操作次数
            for (int j = 1; j <= word2.length(); j++) {
                dp[0][j] = dp[0][j - 1] + 1;  //在dp[0][j-1]的基础上再做一次插入操作
            }
            //从前i个字符的word1变换到空的word2所需的最短操作次数
            for (int i = 1; i <= word1.length(); i++) {
                dp[i][0] = dp[i - 1][0] + 1; //在dp[i-1][0]的基础上再做一次删除操作
            }

            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {//word1的前i个字符与word2的前j个字符相等
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        int tmpMin = Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1);
                        dp[i][j] = Math.min(tmpMin, dp[i][j - 1] + 1);
                    }
                }
            }

            return dp[word1.length()][word2.length()];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
package leetcode.editor.cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 4821 👎 0

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution1 solution1 = new LongestPalindromicSubstring().new Solution1();
        Solution2 solution2 = new LongestPalindromicSubstring().new Solution2();
        //System.out.println(solution1.longestPalindrome("abaaabada"));
        System.out.println(solution2.longestPalindrome("bb"));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //解法1：暴力解法
    class Solution1 {
        //判断是否是回文串
        public boolean isPalindromic(String s) {
            int len = s.length();
            for (int i = 0; i < len / 2; i++) {
                if (s.charAt(i) != s.charAt(len - i - 1)) {
                    return false;
                }
            }
            return true;
        }

        public String longestPalindrome(String s) {
            //把res初始化为第一个字符,max初始化为1,是因为 1 <= s.length
            String res = String.valueOf(s.charAt(0));
            int max = 1;
            for (int i = 0; i < s.length(); i++) {
                /**
                 *  因为上面res和max已经这、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、77样初始化了,所以直接让 j=i+1 ,这样就算s的长度为1, 也能返回正确的值;
                 *  如果上面把res初始化为空字符, max初始化为0 , 那么这里要让j=i , 否则当s的长度为1,就无法返回正确的值
                 */
                for (int j = i + 1; j < s.length(); j++) {
                    String temp = s.substring(i, j + 1);
                    if (isPalindromic(temp) && ((j - i + 1) > max)) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
            return res;
        }
    }

    //解法2：动态规划
    class Solution2 {
        public String longestPalindrome(String s) {
            if (s.length() < 2) {
                return s;
            }

            int maxLen = 1;//记录最长回文子串的长度
            int begin = 0;//记录最长回文子串的起始位置

            //定义dp数组：dp[i][j] 表示 s[i..j] 是否是回文串
            boolean[][] dp = new boolean[s.length()][s.length()];
            //初始化
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
            }

            //len表示当前子串的长度
            for (int len = 2; len <= s.length(); len++) {
                for (int i = 0; i <= (s.length() - len); i++) {
                    int left = i;
                    int right = i + len - 1;
                    if (s.charAt(left) == s.charAt(right) && (dp[left + 1][right - 1] == true || len==2)) { //这里的len==2的意义是当len==2时无需考虑dp[left + 1][right - 1]
                        dp[left][right] = true;
                        if (right - left + 1 > maxLen) {
                            maxLen = right - left + 1;
                            begin = left;
                        }
                    }
                }
            }

            return s.substring(begin, begin + maxLen);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
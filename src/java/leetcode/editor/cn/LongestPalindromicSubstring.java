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
        Solution3 solution3 = new LongestPalindromicSubstring().new Solution3();
        System.out.println(solution3.longestPalindrome("abaaabada"));

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
                    if (s.charAt(left) == s.charAt(right) && (dp[left + 1][right - 1] == true || len == 2)) { //这里的len==2的意义是当len==2时无需考虑dp[left + 1][right - 1]
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

    //解法3：中心扩展法
    class Solution3 {
        public String longestPalindrome(String s) {
            if (s.length() < 2) {
                return s;
            }

            int maxLen = 1;
            String res = s.substring(0, 1);

            //这里的i<s.length()-1 意味着以最后一个字符为中心点这种情况直接略过不用考虑，
            //因为以最后一个字符为中心点的回文串就是这个字符本身，最大长度就是1，对最终结果无影响，
            //让i<s.length()-1的好处就是下面的expandAroundCenter(s, i, i+1)不用考虑i+1越界的问题
            for (int i = 0; i < s.length() - 1; i++) {
                int[] expand = expandAroundCenter(s, i, i);
                if (expand[0] > maxLen) {
                    maxLen = expand[0];
                    res = s.substring(expand[1],expand[2]+1);
                }
                expand = expandAroundCenter(s, i, i+1);
                if (expand[0] > maxLen) {
                    maxLen = expand[0];
                    res = s.substring(expand[1],expand[2]+1);
                }
            }
            return res;
        }

        /**
         * @param s
         * @param centerLeft  左中心点
         * @param centerRight 右中心点
         *                    设定左右两个中心点就是为了适配回文串的长度可能是偶数也可能是奇数
         *                    如果回文串的长度是偶数，那么该回文串的中心点就是中间的那两个字符，左中心点就是中间的那两个字符中的左边的那个，右中心点就是右边那个
         *                    如果回文串的长度是奇数，那么该回文串的中心点就是中间的那一个字符，此时左中心点和右中心点是一样的，都是中间那个字符
         * @return 返回一个数组，数组的第1个元素是以左/右中心点为对称中心的字符串s的最长回文子串的长度，第2个元素是该最长回文子串的左边界，第三个元素是该最长回文子串的右边界
         */
        public int[] expandAroundCenter(String s, int centerLeft, int centerRight) {
            int[] res = new int[3];
            //判断左右中心点是否相等，不相等就不是回文串，不用再往两边扩展了
            if (s.charAt(centerLeft) != s.charAt(centerRight)) {
                res[0] = 1;
                res[1] = centerLeft;
                res[2] = centerRight;
                return res;
            }
            while (centerLeft >= 0 && centerRight <= s.length() - 1) {
                if (s.charAt(centerLeft) == s.charAt(centerRight)) {
                    centerLeft--;
                    centerRight++;
                } else {
                    break;
                }
            }

            /**
             *上述while循环结束，意味着此时的centerLeft或centerRight要么已经越过字符串s的边界了，要么 s.charAt(centerLeft)已经和s.charAt(centerRight)不相等了
             * 所以 s.charAt(centerLeft+1) 和 s.charAt(centerRight-1) 才是相等的，即是最长回文子串的边界
             */
            centerLeft += 1;
            centerRight -= 1;

            res[0] = centerRight - centerLeft + 1;
            res[1] = centerLeft;
            res[2] = centerRight;

            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
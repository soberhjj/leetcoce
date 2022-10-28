package leetcode.editor.cn;

//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 
// 👍 1106 👎 0

/**
 * 思路：区间动态规划(即区间dp)， ，可参考题解：https://leetcode.cn/problems/burst-balloons/solution/zhe-ge-cai-pu-zi-ji-zai-jia-ye-neng-zuo-guan-jian-/  以及该题解中评论的内容
 * 参考题解中关键的两句话：1.k是这个区间最后一个被戳爆的气球 2.见一开头的图，除了粉色之外，你还可以戳绿色和红色 所以你枚举一下这几个 k，从中选择使得 total 值最大的即可用来更新 dp[i][j]   然后呢，你就从 (i,j) 开区间只有三个数字的时候开始计算，储存每个小区间可以得到金币的最大值 然后慢慢扩展到更大的区间，利用小区间里已经算好的数字来算更大的区间
 */
public class BurstBalloons {
    public static void main(String[] args) {
        Solution solution = new BurstBalloons().new Solution();
        System.out.println(solution.maxCoins(new int[]{3, 1, 5, 8}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxCoins(int[] nums) {
            int n = nums.length;
            //创建一个辅助数组，并在首尾各添加1，方便处理边界情况
            int[] val = new int[n + 2];
            val[0] = 1;
            val[val.length - 1] = 1;
            for (int i = 0; i < n; i++) {
                val[i + 1] = nums[i];
            }

            //dp[i][j]含义：表示开区间（i,j）能拿到的最多金币数，即从第i气球到第j个气球（不包括i和j这两个气球）所能拿到的最多金币数
            int[][] dp = new int[n + 2][n + 2];
            // len表示开区间长度
            for (int len = 3; len <= n + 2; len++) {
                // i表示开区间左端点
                for (int i = 0; i <= n + 2 - len; i++) {
                    int res = 0;
                    // k为开区间内的索引
                    for (int k = i + 1; k < i + len - 1; k++) {
                        int left = dp[i][k];  //注意数组是有默认值的，int数组元素的默认值为0
                        int right = dp[k][i + len - 1];
                        res = Math.max(res, left + val[i] * val[k] * val[i + len - 1] + right);
                    }
                    dp[i][i + len - 1] = res;
                }
            }
            return dp[0][n + 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
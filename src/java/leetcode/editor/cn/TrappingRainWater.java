package leetcode.editor.cn;

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 3819 👎 0

//

public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            int length = height.length;
            //动态规划求每一列左边/右边的最高的柱子
            int[] dp_max_left = new int[length];
            int[] dp_max_right = new int[length];
            //不需要初始化dp_max_left[0]=0,dp_max_left[length-1]=0，因为int数组的元素默认值就是0
            for (int i = 1; i < length; i++) {
                dp_max_left[i] = Math.max(height[i - 1], dp_max_left[i - 1]);
            }
            for (int i = length - 2; i >= 0; i--) {
                dp_max_right[i] = Math.max(height[i + 1], dp_max_right[i + 1]);
            }

            //求每一列的积水（木桶原理，每一列积水取决于该列左右两边最高柱子中的那个较矮的柱子和该列的高度差）
            int sum = 0;
            for (int i = 0; i < length; i++) {
                int min = Math.min(dp_max_left[i], dp_max_right[i]);
                sum += min > height[i] ? min - height[i] : 0;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
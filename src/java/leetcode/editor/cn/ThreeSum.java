package leetcode.editor.cn;

//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k
// ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请 
//
// 你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 5210 👎 0


/**
 * 思路：排序+双指针
 * 参考：https://leetcode.cn/problems/3sum/solution/3sumpai-xu-shuang-zhi-zhen-yi-dong-by-jyd/
 *
 * 此解法的时间复杂度是排序的O(NlogN) + 后面的遍历*双指针(即O(N2)) ， 所以最终的时间复杂度是O(N2)
 * 如果使用暴力解法 那时间复杂度是O(N3)
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new LinkedList<>();
            //第一步：将数组从小到大排序
            Arrays.sort(nums);
            //第二步：双指针
            for (int k = 0; k < nums.length; k++) {
                //当 nums[k] > 0 时直接break跳出：因为 nums[j] >= nums[i] >= nums[k] > 0，即 3个数字都大于 0，在此固定指针 k 之后不可能再找到结果了。
                if (nums[k] > 0) {
                    break;
                }
                //当 k > 0且nums[k] == nums[k - 1]时即跳过此元素nums[k]：因为已经将 nums[k - 1] 的所有组合加入到结果中，本次双指针搜索只会得到重复组合
                if (k > 0 && nums[k] == nums[k - 1]) {
                    continue;
                }
                /**
                 * i，j 分设在数组索引 (k, len(nums))(k,len(nums)) 两端，当i < j时循环计算s = nums[k] + nums[i] + nums[j]，并按照以下规则执行双指针移动：
                 * 当s == 0时，记录组合[k, i, j]至res，执行i += 1和j -= 1并跳过所有重复的nums[i]和nums[j]，防止记录到重复组合。
                 * 当s < 0时，i += 1并跳过所有重复的nums[i]；
                 * 当s > 0时，j -= 1并跳过所有重复的nums[j]；
                 */
                int i = k + 1;
                int j = nums.length - 1;
                while (i < j) {
                    int sum = nums[k] + nums[i] + nums[j];
                    if (sum == 0) {
                        res.add(new LinkedList<>(Arrays.asList(nums[k], nums[i], nums[j])));
                        while (i < j && nums[i] == nums[++i]) {}
                        while (i<j && nums[j]==nums[--j]){}
                    } else if (sum < 0) {
                        while (i < j && nums[i] == nums[++i]) {}
                    }else {
                        while (i<j && nums[j]==nums[--j]){}
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
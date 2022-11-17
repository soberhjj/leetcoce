package leetcode.editor.cn;

//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 
// 👍 1874 👎 0


import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
        System.out.println(solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            //单调队列：利用双端队列+保持该双端队列的单调性来实现
            Deque<Integer> deque = new LinkedList<>();
            int[] result = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++){
                // 保证队列元素从大到小 如果前面数小则需要依次弹出，直至满足要求
                while (!deque.isEmpty()&&nums[deque.getLast()]<=nums[i]){
                    deque.pollLast();
                }
                //添加当前值对应的数组下标到队列末尾
                deque.addLast(i);
                //判断当前队列的队首元素是否属于当前窗口
                if (deque.getFirst()<=i-k){
                    deque.pollFirst();
                }
                //保存当前窗口中最大值(判断窗口长度是否为K,因为第一个窗口需要i循环k次后才会形成)
                if(i+1 >= k){
                    result[i+1-k] = nums[deque.getFirst()];
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
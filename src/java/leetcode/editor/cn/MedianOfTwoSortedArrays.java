package leetcode.editor.cn;

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治 
// 👍 4848 👎 0

import java.time.temporal.Temporal;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        Solution2 solution2 = new MedianOfTwoSortedArrays().new Solution2();
        int nums1[]={1,2,3,4,5};
        int nums2[]={6,7,8,9,10};
        System.out.println(solution2.findMedianSortedArrays(nums1,nums2));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }
            int m = nums1.length;
            int n = nums2.length;

            int totalLeft = (m + n + 1) / 2;

            int left = 0;
            int right = m;
            while (left < right) {
                int i = (right - left + 1) / 2;
                int j = totalLeft - i;
                if (nums1[i - 1] < nums2[j]) {
                    left = i;
                } else {
                    right = i - 1;
                }
            }

            int i = left;
            int j = totalLeft - i;

            int num1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int num1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
            int num2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int num2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

            if ((m + n) % 2 == 1) {
                return Math.max(num1LeftMax, num2LeftMax);
            } else {
                return (double) Math.max(num1LeftMax, num2LeftMax) + Math.min(num1RightMin, num2RightMin) / 2;
            }
        }
    }

    class Solution2 {
        public int findKthSmallest(int[] A, int[] B, int pa, int delta, int k) {

            int pb = (k - 1) - pa;

            int Ai_1 = ((pa == 0) ? Integer.MIN_VALUE : A[pa - 1]);
            int Bj_1 = ((pb == 0) ? Integer.MIN_VALUE : B[pb - 1]);
            int Ai = ((pa == A.length) ? Integer.MAX_VALUE : A[pa]);
            int Bj = ((pb == B.length) ? Integer.MAX_VALUE : B[pb]);

            //满足其中之一条件，就返回
            if (Bj_1 <= Ai && Ai <= Bj) {
                return Ai;
            }
            if (Ai_1 <= Bj && Bj <= Ai) {
                return Bj;
            }

            //delta表示pa的变化量（增加或者减少）
            //如果 Ai > Bj, 我们要缩小pa的值，即 pa = pa - delta
            //因为 pb = (k - 1) - pa, 所以，如果delta的值太大，
            //pa会变得很小，因而 可能会导致 pb > B.length. 所以需要处理一下。
            // 对于pa = pa + delta 的处理也是一样
            if (Ai > Bj) {
                pa = ((k - 1) - (pa - delta) > B.length) ? k - 1 - B.length : pa - delta;
                return findKthSmallest(A, B, pa, (delta + 1) / 2, k);
            } else {
                pa = (pa + delta > A.length) ? A.length : pa + delta;
                return findKthSmallest(A, B, pa, (delta + 1) / 2, k);
            }
        }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int k1 = (nums1.length + nums2.length + 1) / 2;
            int pa1 = Math.min(nums1.length, k1 - 1);
            int v1 = findKthSmallest(nums1, nums2, pa1, (pa1 + 1) / 2, k1);

            int k2 = (nums1.length + nums2.length + 2) / 2;
            int pa2 = Math.min(nums1.length, k2 - 1);
            int v2 = findKthSmallest(nums1, nums2, pa2, (pa2 + 1) / 2, k2);

            return (Double.valueOf(v1)+Double.valueOf(v2))/2;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}
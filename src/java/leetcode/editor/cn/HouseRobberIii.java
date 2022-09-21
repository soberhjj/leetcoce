package leetcode.editor.cn;

//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。 
//
// 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接
//相连的房子在同一天晚上被打劫 ，房屋将自动报警。 
//
// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [3,2,3,null,3,null,1]
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7 
//
// 示例 2: 
//
// 
//
// 
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 树的节点数在 [1, 104] 范围内 
// 0 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 1439 👎 0

/**
 * 思路：树的深度优先搜索+动态规划
 * 可以看官方题解，给的思路说明非常详细清晰
 */

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIii {
    public static void main(String[] args) {
        Solution solution = new HouseRobberIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        Map<TreeNode, Integer> f = new HashMap<>();
        Map<TreeNode, Integer> g = new HashMap<>();

        public int rob(TreeNode root) {
            dfs(root);
            return Math.max(f.get(root), g.get(root));
        }

        public void dfs(TreeNode node) {
            if (node == null) {
                //官方题解中无下面两行,而是直接return了,这样会运行报错的,因为从map取数时会出现key不存在,所以要加上下面两行
                f.put(node, 0);
                g.put(node, 0);
                return;
            }
            dfs(node.left);
            dfs(node.right);
            f.put(node, node.val + g.get(node.left) + g.get(node.right));
            g.put(node, Math.max(f.get(node.left), g.get(node.left)) + Math.max(f.get(node.right), g.get(node.right)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
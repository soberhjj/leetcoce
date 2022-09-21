package leetcode.editor.cn;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1905 👎 0

/**
 * 思路：参考 https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
 * 写的思路非常清晰
 */

public class NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(solution.numIslands(grid));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            int islandNum = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    //如果不是未遍历过的陆地则直接跳过
                    if (grid[i][j] != '1') {
                        continue;
                    }
                    dfs(grid, i, j);
                    islandNum++;
                }
            }
            return islandNum;
        }

        public void dfs(char[][] grid, int r, int c) {
            // 判断 base case
            if (!inArea(grid, r, c)) {
                return;
            }
            // 如果这个格子不是岛屿，直接返回
            if (grid[r][c] != '1') {
                return;
            }
            //将岛屿标记为"已遍历过",用2表示已遍历过的岛屿
            grid[r][c] = '2';

            //访问上、下、左、右四个相邻结点
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

        public boolean inArea(char[][] grid, int r, int c) {
            return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
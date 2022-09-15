package leetcode.editor.cn;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 2103 👎 0

/**
 * 参考：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/solution/hui-su-fa-ti-jie-by-yue-jian-dan-yue-hao-sdjn/
 * 或官方题解https://leetcode.cn/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode-solutio/
 * 思路是一样的，回溯求所有组合
 */

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        List<String> res = solution.letterCombinations("23");
        res.forEach(v -> {
            System.out.println(v);
        });
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {
            Map<String, List<String>> initMap = new HashMap<String, List<String>>() {{
                put("1", new ArrayList<>());
                put("2", new ArrayList<String>() {{
                    add("a");
                    add("b");
                    add("c");
                }});
                put("3", new ArrayList<String>() {{
                    add("d");
                    add("e");
                    add("f");
                }});
                put("4", new ArrayList<String>() {{
                    add("g");
                    add("h");
                    add("i");
                }});
                put("5", new ArrayList<String>() {{
                    add("j");
                    add("k");
                    add("l");
                }});
                put("6", new ArrayList<String>() {{
                    add("m");
                    add("n");
                    add("o");
                }});
                put("7", new ArrayList<String>() {{
                    add("p");
                    add("q");
                    add("r");
                    add("s");
                }});
                put("8", new ArrayList<String>() {{
                    add("t");
                    add("u");
                    add("v");
                }});
                put("9", new ArrayList<String>() {{
                    add("w");
                    add("x");
                    add("y");
                    add("z");
                }});
            }};

            //res用来保存结果
            List<String> res = new ArrayList<>();
            //处理特殊场景
            if (digits == null || "".equals(digits)) {
                return res;
            }
            //track记录当前路径
            LinkedList<String> track = new LinkedList<>();
            backtrack(initMap, digits, 0, track, res);

            return res;
        }

        public void backtrack(Map<String, List<String>> initMap, String digits,
                              int index, LinkedList<String> track, List<String> res) {
            //说明本条路径到达叶子结节点
            if (track.size() == digits.length()) {
                res.add(String.join("", track));
                return;
            }
            //选择列表：获取当前数字对应的字母集合
            List<String> nums = initMap.get(String.valueOf(digits.charAt(index)));
            for (String num : nums) {
                //做选择
                track.add(num);
                //此处不可用++index,因为本层级index相同,不可改变当前层级的索引
                int indexTemp = index + 1;
                backtrack(initMap, digits, indexTemp, track, res);
                //撤销选择
                track.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
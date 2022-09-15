package leetcode.editor.cn;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 每个右括号都有一个对应的相同类型的左括号。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 3496 👎 0

import java.util.ArrayDeque;
import java.util.LinkedList;

public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            //用LinkedList表达栈
            LinkedList<Character> stack = new LinkedList<>();
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                //如果是左括号则进栈
                if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                    stack.add(chars[i]);
                }
                //如果是右括号则取出当前栈顶元素,判断能否闭合
                else {
                    //如果栈为空直接退出，因为栈空说明该右括号必定没有左括号与之闭合
                    if (stack.isEmpty()) {
                        return false;
                    }
                    //出栈
                    char pop = stack.getLast();
                    stack.removeLast();
                    //判断能否闭合
                    if ((chars[i] == ')' && pop == '(') || (chars[i] == ']' && pop == '[') || (chars[i] == '}' && pop == '{')) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            //最后栈为空说明全部左右括号都能闭合上
            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
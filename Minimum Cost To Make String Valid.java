import java.util.Stack;

class Solution {
    public int minCost(String s) {
        int n = s.length();

        // If the length is odd, it's impossible to balance
        if (n % 2 != 0) {
            return -1;
        }

        Stack<Character> stack = new Stack<>();

        // Traverse the string to remove valid pairs using the stack
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            
            if (ch == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();  // Valid pair found, remove it
            } else {
                stack.push(ch);  // Push the unbalanced bracket onto the stack
            }
        }

        // Now the stack contains unmatched brackets, count the '{' and '}'
        int p = 0, q = 0;
        
        // Count the remaining '{' and '}'
        while (!stack.isEmpty()) {
            if (stack.peek() == '{') {
                p++;
            } else {
                q++;
            }
            stack.pop();
        }

        // Return the minimum cost to balance the remaining unbalanced brackets
        return (p + 1) / 2 + (q + 1) / 2;
    }
}

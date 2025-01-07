import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumCost(int n, String s, int[] cost) {
        int ans = 0;
        int ptrmax = 0;
        
        for (int i = 1; i < n; i++) {
            if (s.charAt(ptrmax) == s.charAt(i)) {
                // Add the minimum of the two costs
                ans += Math.min(cost[ptrmax], cost[i]);
                // Move ptrmax to the character with the higher cost
                if (cost[ptrmax] < cost[i]) {
                    ptrmax = i;
                }
            } else {
                // Move ptrmax to the current position if characters differ
                ptrmax = i;
            }
        }
        
        return ans;
    }
}

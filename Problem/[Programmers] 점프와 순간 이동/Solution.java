import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 1;
        if(n == 1) return 1;
        
        while(true){
            if(n%2 != 0){
                n -= 1;
                ans++;
                continue;
            }
            
            n /= 2;
            if (n == 1) break;
        }

        return ans;
    }
}
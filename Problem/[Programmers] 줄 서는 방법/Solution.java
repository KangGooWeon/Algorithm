import java.util.ArrayList;
import java.util.List;

class Solution {
      public int[] solution(int n, long k) {
        List<Integer> list = new ArrayList<>();
        int[] result = new int[n];
        long max = 1;
        int div = n;
        for(int i=2; i<=n; i++) {
        	max *=i;
        }
        
        for(int i=1; i<=n; i++) {
        	list.add(i);
        }
        
        
        if( n == 1) {
        	result[0] =1;
        	return result;
        }
        
        for(int i=0; i<n; i++) {
        	max = max/div--;
        	long a = (k-1) / max;
        	result[i] = list.get((int) (a%list.size()));
        	list.remove((int) (a%list.size()));
        }
        return result;
    }
}
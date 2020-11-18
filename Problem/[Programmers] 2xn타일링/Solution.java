class Solution {
    public int solution(int n) {
        int answer = 0;
        
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int[] map = new int [n];
        map[0]= 1;
        map[1]= 2;
        for(int i =2; i<n; i++){
            map[i] = (map[i-1] + map[i-2])%1000000007;
        }
        
        return map[n-1];
    }
}
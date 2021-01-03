class Solution {
    public long solution(int n) {
        long answer = 0;      
        
        long[] fibo = new long[2001];
        fibo[0] = 1;
        fibo[1] = 1;
        
        for(int i=2; i<=n; i++){
             fibo[i] = (fibo[i-2]+ fibo[i-1]) % 1234567;
        }
        
        return fibo[n];
    }
}
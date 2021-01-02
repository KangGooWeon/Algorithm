class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        int A = Math.min(a,b);
        int B = Math.max(a,b);
        
        while(true){
            n = n/2;
            
            //만나게 되고, 건너편 대진표가 아닐때
            if( A+1 == B && B%2==0) break;
            
            if( A%2 == 0) A = A/2;
            else A = (A+1)/2;
            
            if( B%2 == 0) B = B/2;
            else B = (B+1)/2;
            
            answer++;
        }

        return answer;
    }
}

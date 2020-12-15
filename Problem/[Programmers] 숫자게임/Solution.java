import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int index_A =0;
        int index_B =0;
        
        while(true){
            if(A[index_A] < B[index_B]){
                index_A++;
                index_B++;
                answer++;
            }else{
                index_B++;
            }
            
            if(index_B == B.length) break;
        }
        
        return answer;
    }
}
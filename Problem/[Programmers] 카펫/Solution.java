class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int mapSize = brown+yellow;
        
        loop:
        for(int i=3; i<=brown; i++){ // 가로
            for(int j=3; j<=i; j++){   // 세로
                if(i*j != mapSize) continue;
                
                // 가장자리 검사
                int a = (i*2) + (j*2) -4; // 겹치는 부분 가장자리 4개
                if( a == brown) {
                    answer[0] = i;
                    answer[1] = j;
                    break loop;
                }
            }
        }
        
        return answer;
    }
}
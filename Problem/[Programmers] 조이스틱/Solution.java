class Solution {
    public int solution(String name) {
        int complete = 0;
        int answer =0;
        boolean[] visited = new boolean[name.length()];
        char[] alpa = name.toCharArray();
        
        for(int i =0; i<alpa.length; i++){
            if(alpa[i] == 'A') {
                visited[i] = true;
                complete++;
            }
        }
        if(complete == visited.length) return 0;
        
        int index =0;
        int length = visited.length;
        
        while(true){
            
            //위 아래 체크
            if(alpa[index] !='A'){
                int a = alpa[index] - 'A';

                if( a <= 13) answer += a;
                else answer += 'Z' - alpa[index] +1;

                visited[index] = true;
                complete++;

                if(complete == length) break;
            }
            
            //오른쪽 검사
            int right = 0;
            for(int i=1; i<length; i++) {
            	if(visited[(index +i)%length] == false) {
            		right = i;
            		break;
            	}
            }
            
            //왼쪽 검사
            int left = 0;
            for(int i=1; i<length; i++) {
            	if(visited[(index +length -i)% length] == false) {
            		left = i;
            		break;
            	}
            }
            
            answer += Math.min(right, left);
            if(right <= left ) index = (index+right)%length;
            else index = (index +length -left)% length;
            
        }
        
        return answer;
    }
}
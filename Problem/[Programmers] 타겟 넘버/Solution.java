class Solution {
    static int answer;
    
    public int solution(int[] numbers, int target) {
        dfs(0,0, numbers, target);
        return answer;
    }
    
    public void dfs(int cnt, int num, int[]numbers, int target){
        if(cnt == numbers.length){
            if(num == target) answer++;
            return;
        }
        
        for(int i=0; i<2; i++){
            if(i== 0) dfs(cnt+1, num + numbers[cnt], numbers, target);
            else dfs(cnt+1, num - numbers[cnt], numbers, target);
        }
    }
}
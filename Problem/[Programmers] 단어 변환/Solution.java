class Solution {
    static int min;
    
    public int solution(String begin, String target, String[] words) {
        min =0;
        
        recursion(0, begin, words, new boolean[words.length], target);
        
        return min;
    }
    
    public void recursion(int cnt, String cur, String[] words ,boolean[] visited, String target){
        if (cur.equals(target)) {
            min = cnt;
            return;
        }
        
        for(int i=0; i<words.length; i++){
            if (visited[i]) continue; 
            
            if (check(cur, words[i]) == 1) {
                visited[i] = true;
                recursion(cnt+1, words[i], words, visited, target);
                visited[i] = false;
            }
        }
    }
    
    public int check(String a, String b){
        int count =0;
        
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) count++;
        }
        
        return count;
    }
}
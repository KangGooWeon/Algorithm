import java.util.List;
import java.util.ArrayList;

class Solution {
    char[][] map;
    boolean[][] visited;
    int N, M, cnt;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];
        N = n;
        M = m;
        
        for(int i =0; i<m; i++){
            char[] c = board[i].toCharArray();
            for(int j=0; j<n; j++){
                map[i][j] = c[j];
            }
        }
        
        while(true){
            boolean ch = false;
            visited = new boolean[m][n];
            for(int i =0; i<m-1; i++){
                for(int j=0; j<n-1; j++){
                    if(map[i][j] != '0' && check(i,j)){
                        ch = true;
                    }
                }
            }
            
            if (!ch) break;
            
            remove();
            move();
        }
        
        return cnt;
    }
    
    public boolean check(int i, int j){
        char c = map[i][j];
        
        if (map[i+1][j] != c || map[i][j+1] != c || map[i+1][j+1] != c) return false;
        
        visited[i][j] = true;
        visited[i+1][j] = true;
        visited[i][j+1] = true;
        visited[i+1][j+1] = true;
        
        return true;
    }
    
    public void remove(){
        for(int i =0; i<M; i++){
            for(int j=0; j<N; j++){
                if(visited[i][j]) {
                    map[i][j] = '0';
                    cnt++;
                }
            }
        }
    }

    public void move(){
        for(int j =0; j<N; j++){
            List<Character> list = new ArrayList<>();
            
            for(int i =M-1; i>=0; i--){
                if (map[i][j] != '0') list.add(map[i][j]);
            }
            
            int a = M-1;
            
            for(int i=0; i<list.size(); i++){
                map[a--][j] = list.get(i); 
            }
            
            for(int i =a; i>=0; i--){
                map[i][j] = '0';
            }
        }
    }
}
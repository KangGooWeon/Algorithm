import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static boolean[][] visited;
    static int maxSize;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        maxSize =0;
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                if(picture[i][j] ==0 || visited[i][j]) continue;
                bfs(i,j,picture[i][j],m,n,picture);
                numberOfArea++;
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, maxSize);
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public void bfs(int x, int y, int pix ,int m, int n, int[][] picture){
        Queue<Pos> qu = new LinkedList<>();
        qu.add(new Pos(x,y));
        visited[x][y] = true;
        int index =0;
        
        while(!qu.isEmpty()){
            int size = qu.size();
            index += size;
            for(int i=0; i<size; i++){
                Pos pos = qu.poll();
                for(int k =0; k<4; k++){
                    int a = pos.x +dx[k];
                    int b = pos.y +dy[k];
                    
                    if(a<0 || a>=m || b<0 || b>=n || picture[a][b] != pix || visited[a][b] == true) continue;
                    
                    qu.add(new Pos(a,b));
                    visited[a][b] = true;
                }
            }
        }
        
        maxSize = index;
    }
    
    static class Pos{
        int x;
        int y;
        public Pos(int x, int y){
            this.x =x;
            this.y =y;
        }
    }
}
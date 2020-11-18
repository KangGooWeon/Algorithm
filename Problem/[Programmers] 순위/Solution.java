class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] map = new int[n+1][n+1];
        
        for(int i =0; i<results.length; i++){
            int a = results[i][0];
            int b = results[i][1];
            map[a][b] = 1;
        }
        
        for(int k =1; k<=n; k++){
            for(int i=1; i<=n; i++){
                if (i==k) continue;
                for(int j=1; j<=n; j++){
                    if (k == j || i==j || map[i][j]==1) continue;
                    
                    if(map[i][k] == 1 && map[k][j] ==1) map[i][j] = 1;
                }
            }
        }
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                map[i][0] += map[i][j];
                map[0][j] += map[i][j];
            }
        }
        
        for(int i=1; i<=n; i++){
            if(map[i][0] + map[0][i] == n-1) answer++;   
        }
        
        return answer;
    }
}
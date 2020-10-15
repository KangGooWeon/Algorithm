class Solution {
    public static int[] solution(int n) {
        int[][] map = new int[n][n];
        int[] dx = {1,0,-1};
        int[] dy = {0,1,-1};
        
        int x=0, y=0, direction=0;
		for(int i =1; i<=(n*(n+1)/2); i++) {
			map [x][y] =i;
			
			if (x+dx[direction] >= n || y+dy[direction] >= n || x+dx[direction] < 0 || y+dy[direction]  < 0 || map[x+dx[direction]][y+dy[direction]] >0) {
				direction++;
				if(direction == 3) direction = 0;
			}
			
			x += dx[direction];
			y += dy[direction];
		}
		
		int[] answer = new int[n*(n+1)/2];
		int count =0;
		
		for(int i =0; i< n; i++) {
			for(int j =0; j< n; j++) {
				if (map[i][j] == 0) continue;
				answer[count++] = map[i][j];
			}
		}
        
        return answer;
    }
}
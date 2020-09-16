import java.util.Arrays;
class Solution {
	public int solution(int m, int n, String[] board) {
		int answer = 0;
		char[][] game = new char[m][n];
		char[][] map = new char[m][n];
		char[] moveArr = new char[m];
		boolean flag = false;
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				game[i][j] = board[i].charAt(j);
			}
		}
		
		while(true) {
			for(int i=0; i<m-1; i++) {
				for(int j=0; j<n-1; j++) {
					int cnt =0;
					if(game[i][j]!='^') {
						if(game[i][j] == game[i+1][j]) cnt++;
						if(game[i][j] == game[i][j+1]) cnt++;
						if(game[i][j] == game[i+1][j+1]) cnt++;
						
						if(cnt == 3) {
							flag = true;
							map[i][j] = '^';
							map[i+1][j] = '^';
							map[i][j+1] = '^';
							map[i+1][j+1] = '^';
						}
					}
				}
			}
			
			if(!flag) break;
			
			for(int i=0; i<n; i++) {
				int index =0;
				for(int j=m-1; j>=0; j--) {
					if(map[j][i] == '^') {
						answer++;
						continue;
					}
					
					moveArr[index++] = game[j][i];
				}
				
				int ind =0;
				for(int j=m-1; j>=0; j--) {
					if( ind < index) {
						game[j][i] = moveArr[ind++];
					}
					else {
						game[j][i] = '^';
					}
				}
			}
			
			for(int i=0; i<m; i++) {
				Arrays.fill(map[i], '>');
			}
			flag= false;
		}
		
		return answer;
	}
}
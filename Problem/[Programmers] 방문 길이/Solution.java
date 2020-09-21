public int solution(String strings) {
        int answer = 0;
    	int[] dx = {0,0,1,-1};
    	int[] dy = {1,-1,0,0};
        boolean[][][] visit = new boolean[11][11][4];
        
        int x =5;
        int y =5;
        
        for(int i =0; i<strings.length(); i++) {
        	int dir =0;
        	if(strings.charAt(i) == 'R') dir = 0;
        	else if(strings.charAt(i) == 'L') dir =1;
        	else if(strings.charAt(i) == 'D') dir =2;
        	else if(strings.charAt(i) == 'U') dir =3;
        	
        	if (x+ dx[dir]<0 || y +dy[dir]<0 || x+ dx[dir]>10 || y+ dy[dir]>10) continue;
        	
        	int reverse =0;
        	if(strings.charAt(i) == 'R') reverse = 1;
        	else if(strings.charAt(i) == 'L') reverse =0;
        	else if(strings.charAt(i) == 'D') reverse =3;
        	else if(strings.charAt(i) == 'U') reverse =2;
        	
        	visit[x][y][reverse] = true;
        	
        	x+= dx[dir];
        	y+= dy[dir];
        	
        	if(!visit[x][y][dir]) answer++;
        	
        	visit[x][y][dir] = true;
        }
        
        return answer;
    }
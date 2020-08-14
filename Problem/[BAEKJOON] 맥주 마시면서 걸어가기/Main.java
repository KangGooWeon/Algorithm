import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
    static int N, M, K;
    static Queue<Pos> qu = new LinkedList<Pos>();
    static Pos []location;
    static int visited[];
    static Pos start, end;
     
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
         
        for(int t=1;t<=TC;++t) {
        	N = Integer.parseInt(br.readLine());
        	location = new Pos[N+2];
        	visited = new int[N+2];
        	
        	for(int i =0; i<N+2; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		 M = Integer.parseInt(st.nextToken());
                 K = Integer.parseInt(st.nextToken());
                 location[i] = new Pos(M,K);
        	}
        	
        	start = location[0];
        	end = location[N+1];
        	
        	String message = BFS();
             
            StringBuilder sb = new StringBuilder();
            sb.append(message);
            System.out.println(sb.toString());
        }
    }

	private static String BFS() {
		qu.offer(start);
		while(!qu.isEmpty()) {
			Pos p = qu.poll();
			if (p.equals(end)) return "happy";
			for (int i = 1; i < N + 2; i++) {
				if(visited[i] == 0 && Math.abs(p.x - location[i].x) + Math.abs(p.y - location[i].y) <= 1000) {
					 qu.add(location[i]);
					 visited[i] = 1;
				}
			}
		}
		return "sad";
	}
}

class Pos{
	int x;
	int y;
	
	public Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

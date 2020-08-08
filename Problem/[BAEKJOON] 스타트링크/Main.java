import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int F,S,G,U,D;
	private static Queue<Integer> qu = new LinkedList<Integer>();
	private static int[] visited;
	private static int[] move = new int[2];

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();
		visited = new int[F+1];
		move[0] = U;
		move[1] = -D;
		
		BFS();
		if (visited[G]>0) System.out.println(visited[G]-1);
		else System.out.println("use the stairs");
	}
	
	private static void BFS() {
		qu.offer(S);
		visited[S] = 1;
		
		while(!qu.isEmpty()) {
			int curr = qu.poll();
			if(curr == G) break;
			
			for(int i =0; i<2; i++) {
				int m = curr + move[i];
				
				if(m<=0 || m>F || visited[m] > 0) continue;
				
				qu.offer(m);
				visited[m] = visited[curr]+1;
			}
		}
	}
}

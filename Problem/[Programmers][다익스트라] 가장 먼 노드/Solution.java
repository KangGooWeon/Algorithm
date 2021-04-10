import java.io.*;
import java.util.*;

class Solution {
	public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Edge>[] map = new List[n];
        for(int i=0; i<n; i++) {
        	map[i] = new ArrayList<>();
        }
        
        //리스트에 담아주기
        for(int i=0; i<edge.length; i++) {
        	map[edge[i][0]-1].add(new Edge(edge[i][1]-1, 1));
        	map[edge[i][1]-1].add(new Edge(edge[i][0]-1, 1));
        }
        
        //다익스트라 변수 선언
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        
        //무한대 대신 Integer.MAX_VALUE로 설정
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        // 노드 1번부터 시작
        distance[0] = 0;
        pq.offer(new Vertex(0,0));
        
        while(!pq.isEmpty()) {
        	Vertex curr = pq.poll();
        	
        	if(visited[curr.no]) continue;
        	
        	visited[curr.no] = true;
        	
        	for(int i=0; i<map[curr.no].size(); i++) {
        		Edge tmp = map[curr.no].get(i);
        		
        		//최소값 갱신
        		if(!visited[tmp.end] && distance[tmp.end] > tmp.wight + curr.totalDistance) {
        			distance[tmp.end] = tmp.wight + curr.totalDistance;
        			pq.offer(new Vertex(tmp.end, distance[tmp.end]));
        		}
        	}
        }
        
        //맥스 값 찾기
        int max =0;
        for(int i=0; i<n; i++) {
        	// 1과 연결이 안된 노드가 존재할 수 있다.
        	if(distance[i] == Integer.MAX_VALUE) continue;
        	
        	max = Math.max(max, distance[i]);
        }
        
        for(int i=0; i<n; i++) {
        	if(distance[i] == max) answer++;
        }
        
        return answer;
    }
    
    static class Vertex implements  Comparable<Vertex>{
    	int no;
    	int totalDistance;
		public Vertex(int no, int totalDistance) {
			super();
			this.no = no;
			this.totalDistance = totalDistance;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.totalDistance - o.totalDistance;
		}
    }
    
    static class Edge{
    	int end;
    	int wight;
		public Edge(int end, int wight) {
			super();
			this.end = end;
			this.wight = wight;
		}
    }

	public static void main(String[] args) {
		new Solution().solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
	}
}
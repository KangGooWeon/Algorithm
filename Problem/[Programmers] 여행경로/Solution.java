import java.util.*;

class Solution {
    static int N;
    static boolean[] visited;
    static String[] tempWay;
    static String[] pathWay;
    
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        pathWay = new String[N+1];
        
        // 출발지 선택
        for(int i=0; i<N; i++){
        	if(tickets[i][0].equals("ICN") == false) continue;
            visited = new boolean[N];
            visited[i] = true;
            tempWay = new String[N+1];
            tempWay[0] = tickets[i][0];
            dfs(1, tickets[i][1], tickets);
        }
        
        return pathWay;
    }
    
    public void dfs(int cnt, String end, String[][] tickets){
        if( cnt == N){
            tempWay[cnt] = end;
            
            //경로 저장이 처음일때
            if(pathWay[0] == null) {
            	pathWay = Arrays.copyOf(tempWay, tempWay.length);
            	return;
            }
            
            //비교, 알파벳 앞설때 교체
            for(int i =0; i<tempWay.length; i++) {
            	if(pathWay[i].compareTo(tempWay[i]) > 0) {
            		pathWay = Arrays.copyOf(tempWay, tempWay.length);
            		break;
            	}
            	else if(pathWay[i].compareTo(tempWay[i]) < 0) break;
            }
            
            return;
        }
        
        // 경유지 확인
        for(int i=0; i<N; i++){
            if(visited[i] == true || end.equals(tickets[i][0]) == false) continue;
            
            visited[i] = true;
            tempWay[cnt] = tickets[i][0];
            dfs(cnt+1, tickets[i][1], tickets);
            visited[i] = false;
        }
    }
}
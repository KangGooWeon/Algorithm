import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        D = Integer.parseInt(input[2]);
        map = new int[N][M];
        selected = new boolean[M];
        
        es = new Enemy[N*M];
        o_es = new Enemy[N*M];
        esLen = 0;
        for(int i = 0 ; i < N ; i++) {
            input = br.readLine().split(" ");
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if(map[i][j] == 1 ) {
                    o_es[esLen] = new Enemy(i, j);
                    es[esLen] = new Enemy(i, j);
                    esLen++;
                }
            }
        }
        
        setAttack(0);
        System.out.println(max);
    }
    static int N, M, D;
    static int[][] map;
    
    static int max = Integer.MIN_VALUE;
    static int[] attackPos = new int[3];
    static boolean[] selected;
    static void setAttack(int pos) {
        
        if(pos == 3) {
            Enemy e, o_e;
            // 적 초기화 
            for(int ei = 0 ; ei < esLen ; ei++) {
                o_e = o_es[ei];
                e = es[ei];
                e.alive = true;
                e.willDie = false;
                e.y = o_e.y;
                e.x = o_e.x;
            }
            
            int cnt = 0;
            for(int turn = 0 ; turn < N ; turn++) {
                for(int a = 0 ; a < 3 ; a++) {
                    int target = -1;
                    // 모든 적 
                    for(int ei = 0 ; ei < esLen ; ei++) {
                        if(es[ei].alive) {
                            if(dis(N, attackPos[a], ei) <= D) {
                                if(target == -1) {
                                    target = ei;
                                }else {
                                    int td = dis(N, attackPos[a], target);
                                    int ed = dis(N, attackPos[a], ei);
                                    if(td > ed) {
                                        target = ei;
                                    }else if(td == ed && es[target].x > es[ei].x) {
                                        target = ei;
                                    }
                                }
                            }
                        }
                    }
                    // 선택한 적 죽을 것 
                    if(target != -1) {
                        es[target].willDie = true;
                    }
                }
                
                // 적 이동 및 죽임 
                for(int ei = 0 ; ei < esLen ; ei++) {
                    e = es[ei];
                    if(e.alive && e.willDie) {
                        cnt++;
                        e.alive = false;
                    }
                    if(e.alive) {
                        e.y++;
                        if(e.y == N)
                            e.alive = false;
                    }
                }  
            }
            if(max < cnt) {
                max = cnt;
            }   
            return;
        }
        
        for(int p = 0 ; p < M ; p++) {
            if(!selected[p]) {
                selected[p] = true;
                attackPos[pos] = p;
                setAttack(pos+1);
                selected[p] = false;
            }
        }
    }
    
    static int esLen;
    static Enemy[] es, o_es ;
    
    static class Enemy{
        int y, x;
        boolean willDie = false;
        boolean alive = true;
        public Enemy(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    static int dis(int y, int x, int e) {
        return Math.abs(y-es[e].y) + Math.abs(x-es[e].x);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
     
    static int[][] map, max;
    static int[] dx = {0,-1,1,0,0};
    static int[] dy = {0,0,0,-1,1};
    static int N, M, K;
    static List<Data> list = new ArrayList<>();
     
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
         
        for(int t=1;t<=TC;++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            max = new int[N][N];
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            list.clear();
             
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                list.add(new Data(x,y,k,dir));
            }
             
            for(int i =0; i<M; i++) {
                for(int j = 0; j<K; j++) {
                    Data data = list.get(j);
                    int x = data.x +dx[data.dir];
                    int y = data.y +dy[data.dir];
                     
                    data.x = x;
                    data.y = y;
                     
                    if( x <= 0|| y <=0 || x>=(N-1) || y >=(N-1)) {
                        data.k = data.k/2;
                        if( data.dir == 1) data.dir =2;
                        else if( data.dir == 2) data.dir =1;
                        else if( data.dir == 3) data.dir =4;
                        else if( data.dir == 4) data.dir =3;
                    }
                }
                check();
            }
             
            int sum =0;
            for(int i=0; i<K; i++) {
                Data data = list.get(i);
                sum += data.k;
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(sum);
            System.out.println(sb.toString());
        }
    }
 
    private static void check() {
        for(int i=0; i<K; i++) {
            Data data = list.get(i);
            map[data.x][data.y] += data.k;
            if(max[data.x][data.y] < data.k) max[data.x][data.y] = data.k;
        }
         
        for(int i=0; i<K; i++) {
            Data data = list.get(i);
            if (map[data.x][data.y] == data.k) {
                map[data.x][data.y] =0;
                max[data.x][data.y] =0;
                continue;
            }
             
            if(data.k == max[data.x][data.y]) {
                data.k = map[data.x][data.y];
            }
            else {
                data.k = 0;
            }
        }
         
        for(int i=0; i<K; i++) {
            Data data = list.get(i);
            map[data.x][data.y] =0;
            max[data.x][data.y] =0;
        }
    }
}
 
class Data{
    int x;
    int y;
    int k;
    int dir;
    public Data (int x, int y, int k, int dir) {
        this.x = x;
        this.y = y;
        this.k = k;
        this.dir = dir;
    }
}
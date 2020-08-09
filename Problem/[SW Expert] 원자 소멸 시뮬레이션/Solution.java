import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N, sum;
    static int[][] oneja;
    static int[][] map = new int [4002][4002];
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            sum =0;
             
            oneja = new int[N][4];
             
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    int a= Integer.parseInt(st.nextToken());
                    if (j <2) oneja[i][j] = (a+1000)*2;
                    else oneja[i][j] = a;
                }
            }
 
            for (int i = 0; i < 4001; i++) {
                for (int j = 0; j < N; j++) {
                    if (oneja[j][2] == -1)continue; // 이미 소멸
                     
                    oneja[j][0] += dx[oneja[j][2]];
                    oneja[j][1] += dy[oneja[j][2]];
                }
                bumpCheck();
            }
            System.out.println("#"+test_case+" "+sum);
        }
    }
 
    private static void bumpCheck() {
        for (int i = 0; i < N; i++) {
            if (oneja[i][2] == -1 || oneja[i][0] > 4001 || oneja[i][1] >4001 || oneja[i][0] < 0 || oneja[i][1] < 0 ) continue;
            map[oneja[i][0]][oneja[i][1]] += oneja[i][3];
        }
         
        for (int i = 0; i < N; i++) {
            if (oneja[i][2] == -1 || oneja[i][0] > 4001 || oneja[i][1] >4001 || oneja[i][0] < 0 || oneja[i][1] < 0 ) continue;
             
            if (map[oneja[i][0]][oneja[i][1]] == oneja[i][3]) map[oneja[i][0]][oneja[i][1]] = 0;
            else {
                sum += oneja[i][3];
                oneja[i][2] = -1;
                map[oneja[i][0]][oneja[i][1]] = 0;
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
   static int N,M,K;
   static int[][] energy;
   static int[][] energyFix;
   static PriorityQueue<Pos> tree;
   static int[][] deadTree;
   static int[] dx = {-1,-1,-1,0,0,1,1,1};
   static int[] dy = {-1,0,1,-1,1,-1,0,1};

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      energy = new int[N+1][N+1];
      energyFix = new int[N+1][N+1];
      tree = new PriorityQueue<>();
      deadTree = new int[N+1][N+1];
      
      for(int i =1; i<=N; i++) {
         st = new StringTokenizer(br.readLine());
         for(int j=1; j<=N; j++) {
            energyFix[i][j] = Integer.parseInt(st.nextToken());
            energy[i][j] = 5;
         }
      }
      
      for(int i =0; i<M; i++) {
         st = new StringTokenizer(br.readLine());
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int year = Integer.parseInt(st.nextToken());
         tree.add(new Pos(x,y,year));
      }
      
      for(int q =0; q<K; q++) {
         int size = tree.size();
         PriorityQueue<Pos> newpq = new PriorityQueue<>();
         for(int i = 0; i<size; i++) {
            Pos pos = tree.poll();
            int x = pos.x;
            int y = pos.y;
            int year = pos.year;
            
            if(year<=energy[x][y]) {
            	newpq.offer(new Pos(x,y,year+1));
               energy[x][y] -= year;
               
               if((year+1)%5 == 0) {
                  for(int k =0; k<8; k++) {
                     int a = x+dx[k];
                     int b = y+dy[k];
                     if (a<=0 || a>N || b<=0 || b>N) continue;
                     
                     newpq.add(new Pos(a,b,1));
                  }
               }
            }else {
               deadTree[x][y] += year/2;
            }
         }
         tree = new PriorityQueue<>(newpq);
         
         for(int i =1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
               energy[i][j] += energyFix[i][j];
               energy[i][j] += deadTree[i][j];
               deadTree[i][j] =0;
            }
         }
      }
      
      System.out.println(tree.size());
   }

   static class Pos implements Comparable<Pos>{
      int x;
      int y;
      int year;
      public Pos(int x, int y, int year) {
         super();
         this.x = x;
         this.y = y;
         this.year = year;
      }
      @Override
      public int compareTo(Pos arg0) {
         return this.year - arg0.year;
      }
      
   }
}
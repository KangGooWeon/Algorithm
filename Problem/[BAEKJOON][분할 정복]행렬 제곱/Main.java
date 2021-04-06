import java.io.*;
import java.util.*;

public class Main {
	static HashMap<Long, int[][]> hm = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		int[][] matrix = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음부터 1000이 넘게 들어오는 경우
		matrix = matrixMod(matrix);
		
		// A^1 과 A^2 저장
		hm.put((long) 1, matrix);
		multiplyMatrix(matrix, matrix,2);
		
		divideConquer(B);
		
		int[][] result = hm.get(B);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void divideConquer(long cnt){
		if(hm.containsKey(cnt)) return;
		
		long first = 0;
		long second = cnt/2;
		
		if(cnt %2 == 0) first = cnt/2;
		else first = (cnt/2)+1;
		
		divideConquer(first);
		divideConquer(second);
		
		multiplyMatrix(hm.get(first), hm.get(second), cnt);
	}
	
	public static int[][] multiplyMatrix(int[][] a, int[][] b, long cnt){
		int[][] temp = new int[a.length][a.length];
		
        for(int i=0; i<a.length;i++){
            for(int j=0; j<b[0].length;j++){
                for(int k=0; k<a[0].length;k++){
                	temp[i][j] += a[i][k]*b[k][j];
                }
                 
            }
        }
        
        temp = matrixMod(temp);
        hm.put(cnt, temp);
        return temp;
        
	}
	
	public static int[][] matrixMod(int[][] matrix){
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				matrix[i][j] %= 1000;
			}
		}
		return matrix;
	}
}

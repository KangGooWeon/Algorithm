import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N, M, index;
	static int[] parents,count;
	static String[] str;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		for(int t =1; t<=N; t++) {		// 전체 테스트 케이스 수
			M = Integer.parseInt(in.readLine());
			parents = new int[(2 * M) + 1];
			str = new String[(2 * M) + 1];
			count = new int[(2 * M) + 1];
			index=1;
			Arrays.fill(count, 1);
			
			for(int i = 0; i<M; i++) {		// 친구 네트워크
				StringTokenizer st = new StringTokenizer(in.readLine());
				String a = st.nextToken();
				int aNum = check(a);			// 중복 검사(이미 존해하면 그 자리 반환, 존재안하면 str에 넣고 parents 생성)
				String b = st.nextToken();
				int bNum = check(b);
				
				int n = Union(aNum, bNum);
				 sb.append(count[n]).append("\n");
			}
		}
		System.out.println(sb);
		in.close();
	}

	private static int FindSet(int a) {
		if (parents[a] == a) return a;
		
		return parents[a] = FindSet(parents[a]);
	}

	private static int Union(int aNum, int bNum) {
		int aRoot = FindSet(aNum);
		int bRoot = FindSet(bNum);
		if (aRoot == bRoot) return aRoot;
		
		parents[bRoot] = aRoot;
		count[aRoot] += count[bRoot];
		return aRoot;
	}

	private static int check(String a) {
		for(int i = 1; i<index; i++) {
			if (str[i].equals(a)) return i;
		}
		
		str[index] = a;
		parents[index] = index;
		return index++;
	}
}


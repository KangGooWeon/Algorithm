
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] fees;
	static int[] year;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			fees = new int[4];
			year = new int[12];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				fees[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				year[i] = Integer.parseInt(st.nextToken());
			}

			min = fees[3];

			dfs(0, 0);

			System.out.println("#" + t + " " + min);
		}
	}

	private static void dfs(int cnt, int fee) {
		if (cnt == 12) {
			min = Math.min(fee, min);
			return;
		}
		if (year[cnt] == 0)
			dfs(cnt + 1, fee);

		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				dfs(cnt + 1, fee + (fees[0] * year[cnt]));
			} else if (i == 1) {
				dfs(cnt + 1, fee + fees[1]);
			} else {
				if (cnt == 10 || cnt == 11) {
					for (int j = 0; j < 2; j++) {
						if (j == 0) {
							dfs(cnt + 1, fee + (fees[0] * year[cnt]));
						} else if (j == 1) {
							dfs(cnt + 1, fee + fees[1]);
						}
					}
				}else {
					dfs(cnt + 3, fee + fees[2]);
				}
			}
		}
	}
}

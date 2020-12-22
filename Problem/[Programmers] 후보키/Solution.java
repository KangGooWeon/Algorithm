import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

class Solution {
    static int N, M;
	static List<String> list;
	static char[] isSelected;
	String[] temp;

	public int solution(String[][] relation) {
		list = new ArrayList<>();
		N = relation.length;
		M = relation[0].length;
		isSelected = new char[M];
		temp = new String[N];
		Arrays.fill(isSelected, 'n');
		
		for(int i=1; i<=M; i++) {
			combi(0,0,i, relation);
		}
		
		return list.size();
	}

	public void combi(int cnt, int start, int limit, String[][] relation) {
		if (cnt == limit) {
			String check = "";
			for (int i = 0; i < N; i++) {
				temp[i] = "";
			}
			// 최소성검사
			for (int i = 0; i < M; i++) {
				if (isSelected[i] != 'n')
					check += String.valueOf(isSelected[i]);
			}
			
			for (int i = 0; i < list.size(); i++) {
				String a = list.get(i);
				int index =0;
				for(int j =0; j<a.length(); j++) {
					String e = "";
					e += a.charAt(j);
					if (check.contains(e)) index++;
				}
				if (index == a.length()) return;
			}

			for (int i = 0; i < M; i++) {
				if (isSelected[i] == 'n')
					continue;
				for (int j = 0; j < N; j++) {
					temp[j] += relation[j][isSelected[i]-'0'];
				}
			}
			// 유일성 체크
			if (!uniqueness(temp))
				return;

			// 최소성 유일성 다 맞음
			list.add(check);
			return;
		}

		for (int i = start; i < M; i++) {
			isSelected[cnt] = (char) ('0'+i);
			combi(cnt + 1, i + 1, limit, relation);
		}
	}

	public boolean uniqueness(String[] tuples) {
		boolean ch = false;
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(tuples[i]);
		}

		if (set.size() == N)
			ch = true;

		return ch;
	}
}
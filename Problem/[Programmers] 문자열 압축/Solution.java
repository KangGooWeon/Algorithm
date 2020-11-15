class Solution {
   public int solution(String s) {
		int answer = s.length();

		for (int i = 1; i <= s.length() / 2; i++) {
			String result = "";
			String temp = s.substring(0, 1 * i);
			int count = 1;

			int a = s.length() / i;
			if (s.length() % i != 0)
				a += 1;

			for (int j = 1; j < a; j++) {
				String e = "";

				if (j == (a - 1))
					e = s.substring(j * i);
				else
					e = s.substring(j * i, (j + 1) * i);

				if (e.length() != i) {
					result += e;
					break;
				}

				if (temp.equals(e)) {
					count++;
				} else {
					if (count == 1) {
						result += temp;
					} else {
						result += Integer.toString(count) + temp;
					}
					temp = e;
					count = 1;
				}

				if (j == (s.length() / i) - 1) {
					if (count == 1) {
						result += e;
					} else {
						result += Integer.toString(count) + e;
					}
				}

			}
			answer = Math.min(result.length(), answer);
		}

		return answer;
	}
}
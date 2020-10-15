import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
   public int solution(String[] lines) {
		int answer = 0;

		List<Data> list = new ArrayList<>();

		for (String li : lines) {
			StringTokenizer st = new StringTokenizer(li);
			String next = st.nextToken();
			String time = st.nextToken();
			String[] data = time.split(":");
			int hour = Integer.parseInt(data[0]);
			int minute = Integer.parseInt(data[1]);
			double sec = Double.parseDouble(data[2]);

			String run = st.nextToken();
			String[] r = run.split("s");
			double runTime = Double.parseDouble(r[0]);

			// 완료시간 저장
			double end = Double.parseDouble(data[0] + data[1] + data[2]);

			double d = sec - runTime;
			sec = d+0.001;
			if (sec < 0) {
				sec = 60.0 + d;
				minute -= 1;
				if (minute < 0) {
					minute = 60 + minute;
					hour -= 1;
				}
			}

			data[0] = String.valueOf(hour);
			if (minute < 10)
				data[1] = "0" + String.valueOf(minute);
			if (sec < 10)
				data[2] = "0" + String.valueOf(sec);

			double start = Double.parseDouble(data[0] + data[1] + data[2]);

			list.add(new Data(start, end));
		}

		// end 처리
		int size = list.size();
		for (int i = 0; i < size; i++) {
			int count = 1;
			Data data = list.get(i);
			double end = data.end + 1.0;

			for (int j = i+1; j < size; j++) {
				Data check = list.get(j);
				if (check.start < end)
					count++;
			}
			if (answer < count)
				answer = count;
		}

		return answer;
	}
    static class Data {
        double start;
        double end;

        public Data(double start, double end) {
            super();
            this.start = start;
            this.end = end;
        }
    }
}
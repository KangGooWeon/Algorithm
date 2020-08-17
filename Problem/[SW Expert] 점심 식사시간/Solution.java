package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{
	private static int[] isSelected;
	private static boolean[] isChecked;
	private static int person, totalCnt, min, minute;
	private static List<Data> list = new ArrayList<Data>();
	private static List<Floor> floorList = new ArrayList<Floor>();
	private static List<Integer> firstFloor = new ArrayList<Integer>();
	private static List<Integer> secondFloor = new ArrayList<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; ++t) {
			int N = Integer.parseInt(br.readLine());
			person = 0;
			min = 1000000;
			list.clear();
			floorList.clear();
			firstFloor.clear();
			secondFloor.clear();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int a = Integer.parseInt(st.nextToken());
					if (a == 1) {
						list.add(new Data(i, j, 0, 0));
						person++;
					}

					if (a > 1)
						floorList.add(new Floor(i, j, a));
				}
			}

			isSelected = new int[person]; // 경우의 수
			isChecked = new boolean[person]; // 계단 통과 체크

			permutation(0);

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(min);
			System.out.println(sb.toString());
		}
	}

	private static void permutation(int cnt) {
		if (cnt == person) {
			for (int i = 0; i < person; i++) { // 해당 경의의 수로 초기화
				Data data = list.get(i);
				Floor floor = floorList.get(isSelected[i] - 1);
				data.distance = Math.abs(data.x - floor.x) + Math.abs(data.y - floor.y);
				data.floorCnt = floor.floorCnt;
			}

//			for (int i = 0; i < person; i++) {
//				System.out.print(isSelected[i]+" ");
//			}
//			System.out.println();
			totalCnt = person;
			minute = 0;

			while (true) {
				if (totalCnt == 0)
					break;
				minute++;
				downFloor();
				move();
			}

			if (minute < min)
				min = minute;
			Arrays.fill(isChecked, false);
//			System.out.println("------------------------------------끝");
			return;
		}

		for (int i = 1; i < 3; i++) {
			isSelected[cnt] = i;
			permutation(cnt + 1);
		}
	}

	private static void downFloor() {
		for (int i = 0; i < firstFloor.size(); i++) {// 첫번째 계단
			int x = firstFloor.get(i);
			firstFloor.set(i, --x);
			if (x == 0) {
				firstFloor.remove(i);
				i--;
				totalCnt--;
//				System.out.println(minute + "분" + "이동완료");
			}
		}

		for (int i = 0; i < secondFloor.size(); i++) {// 두번째 계단
			int x = secondFloor.get(i);
			secondFloor.set(i, --x);
			if (x == 0) {
				secondFloor.remove(i);
				i--;
				totalCnt--;
//				System.out.println(minute + "분" + "이동완료");
			}
		}

		for (int i = 0; i < person; i++) {
			Data data = list.get(i);
			if (data.distance == 0 && !isChecked[i]) {
				if (isSelected[i] == 1 && firstFloor.size() != 3) {
					firstFloor.add(data.floorCnt);
					isChecked[i] = true;
//					System.out.println(minute + "분 : " +  (i+1) + "사람이" + isSelected[i] + "계단을 내려가기 시작");
				}
				if (isSelected[i] == 2 && secondFloor.size() != 3) {
					secondFloor.add(data.floorCnt);
					isChecked[i] = true;
//					System.out.println(minute + "분 : " +  (i+1) + "사람이" + isSelected[i] + "계단을 내려가기 시작");
				}
			}

		}
	}

	private static void move() {
		for (int i = 0; i < person; i++) {
			Data data = list.get(i);
			if (data.distance == 0)
				continue;
//			if (data.distance == 1 && isSelected[i] == 1 && firstFloor.size() == 3)
//				continue;
//			if (data.distance == 1 && isSelected[i] == 2 && secondFloor.size() == 3)
//				continue;
			data.distance -= 1;
//			if (data.distance == 0) System.out.println(minute + "분 : " + (i+1) + "사람이" + isSelected[i] + "번 계단 입구 도착");
		}
	}
}

class Data {
	int x;
	int y;
	int distance;
	int floorCnt;

	public Data(int x, int y, int distance, int floorCnt) {
		this.x = x;
		this.y = y;
		this.distance = distance;
		this.floorCnt = floorCnt;
	}
}

class Floor {
	int x;
	int y;
	int floorCnt;

	public Floor(int x, int y, int floorCnt) {
		this.x = x;
		this.y = y;
		this.floorCnt = floorCnt;
	}
}

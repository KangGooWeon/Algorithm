class Solution {
   public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[arr1.length];
		String[] biArr1 = new String[arr1.length];
		String[] biArr2 = new String[arr2.length];
		for (int i = 0; i < arr1.length; i++) {
			biArr1[i] = Integer.toBinaryString(arr1[i]);
		}
		
		for (int i = 0; i < arr2.length; i++) {
			biArr2[i] = Integer.toBinaryString(arr2[i]);
		}
		
		for (int i = 0; i < biArr1.length; i++) {
			String a = "";
			for(int k= biArr1.length; k>biArr1[i].length(); k--) {
				a += " ";
			}
			
			for(int j =0; j< biArr1[i].length(); j++) {
				if(biArr1[i].charAt(j) == '1') {
					a += "#";
				}
				else {
					a += " ";
				}
			}
			answer[i] = a;
		}
		
		for (int i = 0; i < biArr2.length; i++) {
			String b = "";
			for(int k= biArr2.length; k>biArr2[i].length(); k--) {
				b += " ";
			}
			for(int j =0; j< biArr2[i].length(); j++) {
				if(biArr2[i].charAt(j) == '1') {
					b += "#";
				}
				else {
					b += " ";
				}
			}
			biArr2[i] = b;
		}
		
		for (int i = 0; i < biArr2.length; i++) {
			String a = "";
			for(int j =0; j< biArr2.length; j++) {
				if(answer[i].charAt(j) == '#') {
					a += answer[i].charAt(j);
				}
				else {
					if (biArr2[i].charAt(j) == '#') {
						a += "#";
					}
					else {
						a += " ";
					}
				}
			}
			answer[i] = a;
		}
		
		return answer;
	}
}
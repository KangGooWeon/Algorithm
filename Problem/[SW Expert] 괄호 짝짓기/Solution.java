
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Stack;

class Solution
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack<Character>();

		for (int test_case = 1; test_case <= 10; test_case++) {
			int num = sc.nextInt();
			String str = sc.next();
			int check =1;
			for (int i = 0; i < num; i++) {
				if (str.charAt(i) == '}' || str.charAt(i) == ']' || str.charAt(i) == '>' || str.charAt(i) == ')')
				{
					char b =' ';
					switch(str.charAt(i)) {
					case '}':
						b = '{';
						break;
					case ']':
						b = '[';
						break;
					case '>':
						b = '<';
						break;
					case ')':
						b = '(';
						break;
						
					}
					
					char a = stack.pop();
					if( a != b) {
						check =0;
						break;
					}
				}
				else{
					stack.push(str.charAt(i));
				}
			}
			System.out.println("#"+ test_case +" "+ check);
		}
	}

}
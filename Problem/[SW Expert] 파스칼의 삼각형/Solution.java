import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int triangle = sc.nextInt();
            int[][] arr = new int[triangle][triangle];
            arr[0][0] = 1; // 첫번째는 무조건 1
            
            for(int col = 1; col <triangle; col++)
            {
                arr[col][0] = 1; // 항상 시작은 1
                arr[col][col] = 1; // 항상 마지막은 1
                for(int row =1; row <col; row++)
                {
                    arr[col][row]=arr[col-1][row-1] + arr[col-1][row];
                }
            }
            
            System.out.println("#" + test_case);
            for(int i =0; i < triangle; i++)
            {
                for(int j = 0; j <= i; j++)
                    System.out.print(arr[i][j] + " ");
                
                 System.out.println();
            }
        }
	}
}
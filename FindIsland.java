import java.util.Scanner;

public class Practise8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int M[][] = {{0},{1},{0},{1},{0}};
		boolean[][] array = new boolean[M.length][M[0].length];
		int ans = 0;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (M[i][j] == 0 && array[i][j] == false) {
					countIsland(M, i, j, array);
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	
	public static void countIsland(int[][] arr, int i, int j, boolean[][] array) {
		if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || array[i][j] == true || arr[i][j] == 1) {
			return;
		}
		
		array[i][j] = true;
		countIsland(arr, i + 1, j, array);
		countIsland(arr, i, j + 1, array);
		countIsland(arr, i - 1, j, array);
		countIsland(arr, i, j - 1, array);
	}
}

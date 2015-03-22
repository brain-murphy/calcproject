

import java.util.Arrays;
import java.util.Random;

/**
 *Created bu Yen Huang
*/ 

public class ConvolutionalCode {

	//This will generate a n x 1 matrix
	private static int[][] generateX(int length) {
		int[][] x = new int[length][1];
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < 1; j++) {
				x[i][j] = rand.nextInt(2);
			}	
		}
		return x;
	}

	private static int[][] generateA0(int[][] arr) {
		int size = arr.length;
		int[][] a = new int[size][size];


		return a;
	
 	}



	public static void printMatrix(int[][] matrix) {
        for (int[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

	public static int[][] findY(int len) {
		int[][] x = generateX(len);

		return x;
	}

	public static void main(String[] args) {
		int[][] x = generateX(4);
		int s = generateA0(x);
		System.out.println(s);
				
	}


	
	
}
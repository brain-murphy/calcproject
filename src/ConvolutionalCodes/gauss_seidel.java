package ConvolutionalCodes;
import java.util.Arrays;

public class gauss_seidel {

    // finds b matrix
    // input is a nx(n+1) matrix
    private static double[][] findB(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length;
        double[][] b = new double[length][1];
        for (int i = 0; i < length; i++) {
            for (int j = width - 1; j < width; j++) {
                b[i][0] = matrix[i][j];
            }
        }
//        System.out.println("This is b matrix");
//        printMatrix(b);
//        System.out.println("");
        return b;
    }


    // finds upper triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findUpper(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] upper = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < width; j++) {
                upper[i][j] = matrix[i][j];
            }
        }
//        System.out.println("This is Upper matrix");
//        printMatrix(upper);
//        System.out.println("");
        return upper;
    }

    // finds lower triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findLower(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] lower = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                lower[i][j] = matrix[i][j];
            }
        }
//        System.out.println("This is lower matrix");
//        printMatrix(lower);
//        System.out.println("");
        return lower;
    }

    // finds diagonal triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findDiagonal(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] diag = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i == j) {
                    diag[i][j] = matrix[i][j];
                }

            }
        }
//        System.out.println("This is the diagonal matrix");
//        printMatrix(diag);
//        System.out.println("");
        return diag;
    }

    //a method for adding two matrices
    private static double[][] matrixAdd(double[][] A, double[][] B) {
        double[][] C = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }


    // a method for multiplying matrices
    private static double[][] multiplyTwoX(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {

                    c[i][j] += a[i][k] * b[k][j];

                }
            }
        }
        return c;
    }

    // a method for negating a matrix
    private static double[][] negateMatrix(double[][] matrix) {
        double[][] neg = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                neg[i][j] = -matrix[i][j];
            }
        }
        return neg;
    }

    // This is -(Ux) + b
    private static double[][] combineUpperXandB(double[][] matrix, double[][] initialX) {
        double[][] upper = findUpper(matrix);

        double[][] b = findB(matrix);
        double[][] mult = multiplyTwoX(upper, initialX);
        double[][] negatedMult = negateMatrix(mult);


        double[][] result = matrixAdd(negatedMult, b);
        System.out.println("This is -Ux + b");
        printMatrix(result);
        return result;
    }

    //L+D
    private static double[][] combineLowerAndDiagonal(double[][] matrix) {
        double[][] lower = findLower(matrix);
        double [][] diag = findDiagonal(matrix);
        double[][] result = matrixAdd(lower, diag);
//        System.out.println("This is L + D");
//        printMatrix(result);
        return result;
    }

    // row reducing an augmented matrix (L+D)|(-Ux + b)
    private static double[][] gaussElimination(double[][] a, double[][] b) {
        double[][] combined = new double[a.length][a[0].length + 1];
        for (int c = 0; c < combined.length; c++) {
            for (int d = 0; d < combined[0].length - 1; d++) {
                combined[c][d] = a[c][d];
            }
        }

        for (int e = 0; e < combined.length; e++) {
            for (int f = combined[0].length - 1; f < combined[0].length; f++) {
                combined[e][f] = b[e][0];
            }
        }

        //row-reducing starts now
        for (int col = 0; col < combined.length; col++) {
            int topRow = col;
            for (int row = col + 1; row < combined.length; row++) {
                if (Math.abs(combined[row][col]) > Math.abs(combined[topRow][col])) {
                    topRow = row;
                }
            }

            // swapping for partial pivoting
            double[] swapped = combined[col];
            combined[col] = combined[topRow];
            combined[topRow] = swapped;


            // making it in echelon form
            for (int i = col + 1; i < combined.length; i++) {
                double multFactor = combined[i][col] / combined[col][col];
                for (int j = col; j < combined.length + 1; j++) {
                    combined[i][j] = combined[i][j] - multFactor * combined[col][j];
                }

            }

            double divisionFactor = 0;
            for (int i = 0; i < combined.length; i++) {

                for (int j = 0; j < combined[0].length; j++) {

                    if (i == j) {
                        divisionFactor = combined[i][j];
                    }


                    combined[i][j] = combined[i][j] / divisionFactor;
                }

            }


        }

        //backward substitution

    

        printMatrix(combined);

        return combined;
    }



    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    public static void main(String[] args) {

        double[][] initialX = new double[3][1];
        initialX[0][0] = 1;
        initialX[1][0] = 1;
        initialX[2][0] = 1;


        double[][] test = new double[3][4];
        test[0][0] = 2;
        test[0][1] = 3;
        test[0][2] = 4;
        test[0][3] = 5;

        test[1][0] = 2;
        test[1][1] = 4;
        test[1][2] = 6;
        test[1][3] = 5;

        test[2][0] = 9;
        test[2][1] = 8;
        test[2][2] = 7;
        test[2][3] = 6;


        System.out.println("This is test matrix");
        printMatrix(test);
        System.out.println("");
        double[][] u = findUpper(test);

        double[][] lAndD = combineLowerAndDiagonal(test);
        double[][] r = combineUpperXandB(test, initialX);
        double[][] e = gaussElimination(lAndD, r);

    }
}

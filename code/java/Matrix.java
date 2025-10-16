import java.util.Random;

public class Matrix {

    // Genera una matriz cuadrada de tamaño n x n con valores aleatorios
    public static double[][] generateMatrix(int n) {
        double[][] matrix = new double[n][n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextDouble(); // valores entre 0 y 1
            }
        }
        return matrix;
    }

    // Multiplica dos matrices cuadradas de tamaño n x n
    public static double[][] multiply(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }
}


import java.io.FileWriter;
import java.io.IOException;

public class Benchmark {
    public static void main(String[] args) {
        // Matrix sizes to test (same as in Python)
        int[] sizes = {10, 25, 50, 100, 200, 300, 400, 500, 700};

        // Number of repetitions per matrix size
        int repetitions = 7;

        // CSV file to store results (same format as Python)
        String outputFile = "code/java/data/output/results_java.csv";

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("Matrix size,Average time (s)\n");

            for (int n : sizes) {
                double totalTime = 0;

                for (int i = 0; i < repetitions; i++) {
                    double[][] A = Matrix.generateMatrix(n);
                    double[][] B = Matrix.generateMatrix(n);

                    long start = System.nanoTime();
                    double[][] result = Matrix.multiply(A, B);
                    long end = System.nanoTime();

                    // Convert to seconds (same as in Python)
                    double elapsedSec = (end - start) / 1e9;
                    totalTime += elapsedSec;
                }

                double avgTime = totalTime / repetitions;

                // Display results in console
                System.out.printf("Matrix %dx%d | Avg time: %.5f s%n", n, n, avgTime);

                // Save results in the CSV file
                writer.write(n + "," + avgTime + "\n");
            }

            System.out.println("\nResults saved in: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}


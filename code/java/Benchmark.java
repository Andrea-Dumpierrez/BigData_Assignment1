import java.io.FileWriter;
import java.io.IOException;

public class Benchmark {
    public static void main(String[] args) {
        // Tamaños de matrices que quieres probar (igual que en Python)
        int[] sizes = {10, 25, 50, 100, 200, 300, 400, 500, 700};

        // Número de repeticiones por tamaño
        int repetitions = 7;

        // Archivo CSV para guardar resultados (igual formato que Python)
        String outputFile = "code/java/data/output/results_java.csv";

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("Tamaño matriz,Tiempo promedio (s)\n");

            for (int n : sizes) {
                double totalTime = 0;

                for (int i = 0; i < repetitions; i++) {
                    double[][] A = Matrix.generateMatrix(n);
                    double[][] B = Matrix.generateMatrix(n);

                    long start = System.nanoTime();
                    double[][] result = Matrix.multiply(A, B);
                    long end = System.nanoTime();

                    // Convertir a segundos (igual que en Python)
                    double elapsedSec = (end - start) / 1e9;
                    totalTime += elapsedSec;
                }

                double avgTime = totalTime / repetitions;

                // Mostrar resultados en consola
                System.out.printf("Matrix %dx%d | Avg time: %.5f s%n", n, n, avgTime);

                // Guardar en el archivo CSV
                writer.write(n + "," + avgTime + "\n");
            }

            System.out.println("\nResultados guardados en: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }
}


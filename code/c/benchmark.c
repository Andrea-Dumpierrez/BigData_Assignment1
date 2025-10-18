#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include "matrix.c"

// Mide el tiempo que tarda en multiplicar dos matrices n x n
double measureTime(int n) {
    struct timeval start, stop;

    double **A = generateMatrix(n);
    double **B = generateMatrix(n);
    double **C = malloc(n * sizeof(double*));
    for (int i = 0; i < n; i++)
        C[i] = malloc(n * sizeof(double));

    gettimeofday(&start, NULL);
    C = multiply(A, B, n);
    gettimeofday(&stop, NULL);

    double elapsed = (stop.tv_sec - start.tv_sec) + 1e-6 * (stop.tv_usec - start.tv_usec);

    for (int i = 0; i < n; i++) {
        free(A[i]); free(B[i]); free(C[i]);
    }
    free(A); free(B); free(C);

    return elapsed;
}

int main() {
    int sizes[] = {10, 25, 50, 100, 200, 300, 400, 500, 700};
    int repetitions = 7;
    int numSizes = sizeof(sizes) / sizeof(sizes[0]);

    system("mkdir code\\c\\data\\output 2>nul");

    FILE *file = fopen("code/c/data/output/results_c.csv", "w");
    if (file == NULL) {
        printf("Error al crear el archivo CSV.\n");
        return 1;
    }

    fprintf(file, "Tamaño matriz,Tiempo promedio (s)\n");

    for (int i = 0; i < numSizes; i++) {
        int n = sizes[i];
        double totalTime = 0.0;

        for (int j = 0; j < repetitions; j++) {
            totalTime += measureTime(n);
        }

        double avgTime = totalTime / repetitions;
        printf("Matrix %dx%d | Avg time: %.6f s\n", n, n, avgTime);
        fprintf(file, "%d,%.6f\n", n, avgTime);
    }

    fclose(file);
    printf("\nResultados guardados en: code/c/data/output/results_c.csv\n");
    return 0;
}

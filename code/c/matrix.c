#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Genera una matriz n x n con valores aleatorios entre 0 y 1
double** generateMatrix(int n) {
    double** matrix = malloc(n * sizeof(double*));
    for (int i = 0; i < n; i++) {
        matrix[i] = malloc(n * sizeof(double));
        for (int j = 0; j < n; j++) {
            matrix[i][j] = (double) rand() / RAND_MAX;
        }
    }
    return matrix;
}

// Multiplica dos matrices cuadradas A y B
double** multiply(double** A, double** B, int n) {
    double** C = malloc(n * sizeof(double*));
    for (int i = 0; i < n; i++) {
        C[i] = malloc(n * sizeof(double));
        for (int j = 0; j < n; j++) {
            double sum = 0.0;
            for (int k = 0; k < n; k++) {
                sum += A[i][k] * B[k][j];
            }
            C[i][j] = sum;
        }
    }
    return C;
}


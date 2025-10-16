import random
import time
import csv

def multiply_matrices(n):
    """Multiplica dos matrices cuadradas de tamaño n y mide el tiempo."""
    A = [[random.random() for _ in range(n)] for _ in range(n)]
    B = [[random.random() for _ in range(n)] for _ in range(n)]
    C = [[0 for _ in range(n)] for _ in range(n)]

    start = time.time()

    for i in range(n):
        for j in range(n):
            for k in range(n):
                C[i][j] += A[i][k] * B[k][j]

    end = time.time()
    return end - start


# Tamaños de matrices que quieres probar
matrix_sizes = [10, 25, 50, 100, 200,300,400, 500,700]

# Repeticiones por tamaño
repetitions = 7

# Crear o abrir el archivo CSV para guardar resultados
with open("data/output/results.csv", mode="w", newline="") as file:
    writer = csv.writer(file)
    writer.writerow(["Tamaño matriz", "Tiempo promedio (s)"])

    for n in matrix_sizes:
        times = []
        for _ in range(repetitions):
            t = multiply_matrices(n)
            times.append(t)

        avg_time = sum(times) / repetitions
        print(f"{n}x{n} → {avg_time:.4f} segundos")
        writer.writerow([f"{n}x{n}", f"{avg_time:.4f}"])

print("\nResultados guardados en data/output/results.csv ")

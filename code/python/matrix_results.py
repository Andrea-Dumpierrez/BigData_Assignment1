import random
import time
import csv

def multiply_matrices(n):
    """Multiplies two square matrices of size n and measures the execution time."""
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


# Matrix sizes to test
matrix_sizes = [10, 25, 50, 100, 200,300,400, 500,700]

# Number of repetitions per matrix size
repetitions = 7

# Create or open the CSV file to save the results
with open("data/output/results.csv", mode="w", newline="") as file:
    writer = csv.writer(file)
    writer.writerow(["Matrix size", "Average time (s)"])

    for n in matrix_sizes:
        times = []
        for _ in range(repetitions):
            t = multiply_matrices(n)
            times.append(t)

        avg_time = sum(times) / repetitions
        print(f"{n}x{n} → {avg_time:.4f} seconds")
        writer.writerow([f"{n}x{n}", f"{avg_time:.4f}"])

print("\nResults saved in data/output/results.csv")

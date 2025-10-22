import random
import time
import sys

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


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("python matrix.py <matrix_size>")
        sys.exit(1)

    n = int(sys.argv[1])
    repetitions = 5
    times = []

    for _ in range(repetitions):
        t = multiply_matrices(n)
        times.append(t)

    avg_time = sum(times) / repetitions

    print(f"Matrix size: {n}x{n}")
    print(f"Average execution time ({repetitions} repetitions): {avg_time:.4f} seconds")


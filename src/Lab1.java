import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter size of Matrix A (rows, columns): ");
            int rowsA = scanner.nextInt();
            int colsA = scanner.nextInt();

            System.out.println("Enter size of Matrix B (rows, columns): ");
            int rowsB = scanner.nextInt();
            int colsB = scanner.nextInt();

            // Виводимо помилку, коли матриці не можут множитися
            if (colsA != rowsB) {
                throw new IllegalArgumentException(
                        "Matrix multiplication impossible: columns A must equal rows B."
                );
            }

            long[][] matrixA = new long[rowsA][colsA];
            long[][] matrixB = new long[rowsB][colsB];

            System.out.println("\nFill Matrix A:");
            fillMatrix(scanner, matrixA);

            System.out.println("\nFill Matrix B:");
            fillMatrix(scanner, matrixB);

            System.out.println("\nMatrix A:");
            printMatrix(matrixA);

            System.out.println("\nMatrix B:");
            printMatrix(matrixB);

            long[][] matrixC = multiplyMatrices(matrixA, matrixB);

            System.out.println("\nMatrix C = A × B:");
            printMatrix(matrixC);

            long result = sumEvenMaxOddMin(matrixC);

            System.out.println("\nResult of sum:");
            System.out.println(result);

        } catch (InputMismatchException e) {
            System.out.println("Error: You entered a non-numeric value!");
        } catch (IllegalArgumentException e) {
            System.out.println("Argument error: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array bounds exceeded: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null reference error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown error: " + e.getMessage());
        }
    }

    // Додала інтерактив, щоб можна було вводити матрицю вручну
    private static void fillMatrix(Scanner scanner, long[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextLong();
            }
        }
    }

    // Розрахунок матричного добутку
    private static long[][] multiplyMatrices(long[][] a, long[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int shared = b.length;

        long[][] result = new long[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                long sum = 0;
                for (int k = 0; k < shared; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    //Обчислення суми найбільших елементів в рядках матриці з парними номерами та найменших елементів в рядках матриці з непарними номерами
    private static long sumEvenMaxOddMin(long[][] matrix) {
        long sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            long value;
            if (i % 2 == 0) {
                value = findMax(matrix[i]);
            } else {
                value = findMin(matrix[i]);
            }
            sum += value;
        }
        return sum;
    }

    private static long findMax(long[] row) {
        long max = row[0];
        for (long num : row) {
            if (num > max) max = num;
        }
        return max;
    }

    private static long findMin(long[] row) {
        long min = row[0];
        for (long num : row) {
            if (num < min) min = num;
        }
        return min;
    }

    // Виводим результат
    private static void printMatrix(long[][] matrix) {
        for (long[] row : matrix) {
            for (long num : row) {
                System.out.printf("%5d", num);
            }
            System.out.println();
        }
    }
}


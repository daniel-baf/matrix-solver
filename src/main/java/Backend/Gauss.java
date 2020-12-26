package Backend;

import javax.swing.JOptionPane;

public class Gauss {
    // this is a generic class for gauss, gauss jordan and gauss seidel

    public Gauss() {
    }// this is a constructor

    public String[] resolveByGauss(double[][] matrix) {
        // we will always use 3 x so make it easier
        int n = 3;
        double constant, x1, x2, x3;
        String[] results = new String[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                constant = (-matrix[j][i]) / (matrix[i][i]);
                for (int k = i; k < n + 1; k++) {
                    matrix[j][k] = ((matrix[i][k]) * constant) + matrix[j][k];
                }
            }
        }

        x3 = matrix[n - 1][n] / matrix[n - 1][n - 1];
        x2 = (matrix[n - 2][n] - x3 * matrix[n - 2][n - 1]) / matrix[n - 2][n - 2];
        x1 = (matrix[n - 3][n] - x2 * matrix[n - 3][n - 2] - x3 * matrix[n - 3][n - 1]) / matrix[n - 3][n - 3];

        results[0] = String.valueOf(x1);
        results[1] = String.valueOf(x2);
        results[2] = String.valueOf(x3);

        return results;
    }

    public String[] resolveByGaussJordan(double[][] matrix) {
        int n = 3;
        double constant;
        String[] results = new String[3];
        // 1st trianngle
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                constant = (-matrix[j][i]) / (matrix[i][i]);
                for (int k = i; k < n + 1; k++) {
                    matrix[j][k] = ((matrix[i][k]) * constant) + matrix[j][k];
                }
            }
        }
        // upper triangle
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                constant = (-matrix[j][i]) / (matrix[i][i]);
                for (int k = i; k < n + 1; k++) {
                    matrix[j][k] = ((matrix[i][k]) * constant) + matrix[j][k];
                }
            }
        }
        // final results
        results[0] = String.valueOf(matrix[0][3] / matrix[0][0]);
        results[1] = String.valueOf(matrix[1][3] / matrix[1][1]);
        results[2] = String.valueOf(matrix[2][3] / matrix[2][2]);

        return results;
    }

    public String[] resolveBySeidel(double[][] matrix) {
        double x0, x1, x2, tolerancy, e;
        String[] results = new String[3];

        tolerancy = Double.valueOf(
                JOptionPane.showInputDialog(null, "Ingrese el valor de tolerancia:\n ej:\"7.5 para un 75 porciento\""));

        x1 = 0.0;
        x2 = 0.0;
        do {
            e = x1;
            x0 = (matrix[0][3] - x1 * matrix[0][1] - x2 * matrix[0][2]) / matrix[0][0];
            x1 = (matrix[1][3] - x0 * matrix[1][0] - x2 * matrix[1][2]) / matrix[1][1];
            x2 = (matrix[2][3] - x0 * matrix[2][0] - x1 * matrix[2][1]) / matrix[2][2];
        } while (Math.abs(e - x1) > tolerancy);

        results[0] = String.valueOf(x0);
        results[1] = String.valueOf(x1);
        results[2] = String.valueOf(x2);

        return results;
    }
}

package com.example.compgrahp_lr1;

import javafx.scene.shape.Line;

public class Triangle {
    MyLine side1;
    MyLine side2;
    MyLine side3;

    public Triangle() {
        this.side1 = new MyLine(100, 200, 35, 200);
        this.side2 = new MyLine(35, 200, 250, 350);
        this.side3 = new MyLine(250, 350, 100, 200);
    }

    public Line[] reflection(double[] lineSX, double[] lineSY, double[] lineEX, double[] lineEY) {
        double m = -lineSX[0], n = -lineSY[0];
// матрица перемещения
        double[][] parallelTransfer = {{1, 0, 0},
                                       {0, 1, 0},
                                       {m, n, 1}};
        double[][] newTriangle = {{side1.startX, side1.startY, 1},
                                  {side2.startX, side2.startY, 1},
                                  {side3.startX, side3.startY, 1}};
        double[][] newLine = {{lineSX[0], lineSY[0], 1},
                {lineEX[0], lineEY[0], 1}};

        newTriangle = matrixMultiply(newTriangle, parallelTransfer, 3, 3, 3);
        newLine = matrixMultiply(newLine, parallelTransfer, 2, 3, 3);

//Поворот относительно начало координат до совпадения с осью Ox
        double fi = -Math.atan((newLine[1][1]) / (newLine[1][0]));
        double[][] matrRotate = {{Math.cos(fi), Math.sin(fi)}, {-Math.sin(fi),
                Math.cos(fi)}};
        newTriangle = matrixMultiply(newTriangle, matrRotate, 3, 2, 2);
//Отражение относительно оси Ox
        double matrReflection[][] = {{1, 0},
                                     {0, -1}};
        newTriangle = matrixMultiply(newTriangle, matrReflection, 3, 2, 2);
//Обратный поворот
        double[][] unmatrRotate = {{Math.cos(fi), -Math.sin(fi)},
                {Math.sin(fi), Math.cos(fi)}};
        newTriangle = matrixMultiply(newTriangle, unmatrRotate, 3, 2, 2);
//Возвращение в начальную позицию
        double[][] unparallelTransfer = {{1, 0, 0},
                                         {0, 1, 0},
                                         {-m, -n, 1}};
        double[][] revhelpnewTriangle = {{newTriangle[0][0], newTriangle[0][1], 1},
                                         {newTriangle[1][0], newTriangle[1][1], 1},
                                         {newTriangle[2][0], newTriangle[2][1], 1}};
        revhelpnewTriangle = matrixMultiply(revhelpnewTriangle,
                unparallelTransfer, 3, 3, 3);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 2; ++j) {
                newTriangle[i][j] = revhelpnewTriangle[i][j];
            }
        }
        // Массив сторон отзеркаленного треугольника
        return new Line[]{
                new Line(400 + newTriangle[0][0], 400 - newTriangle[0][1],
                        400 + newTriangle[1][0], 400 - newTriangle[1][1]),
                new Line(400 + newTriangle[1][0], 400 - newTriangle[1][1],
                        400 + newTriangle[2][0], 400 - newTriangle[2][1]),
                new Line(400 + newTriangle[2][0], 400 - newTriangle[2][1],
                        400 + newTriangle[0][0], 400 - newTriangle[0][1])};
    }

    public double[][] matrixMultiply(double[][] first, double[][] second, int l, int n, int m) {
        double[][] result = new double[l][n];
        for (int i = 0; i < l; ++i)
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < m; ++k)
                    result[i][j] += first[i][k] * second[k][j];
        return result;
    }

    public Line[] getSides() {
        return new Line[]{side1.line, side2.line, side3.line};
    }
}


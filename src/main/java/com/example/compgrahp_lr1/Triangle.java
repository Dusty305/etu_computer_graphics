package com.example.compgrahp_lr1;

import javafx.scene.shape.Line;

public class Triangle {
    myLine side1;
    myLine side2;
    myLine side3;
    public Triangle(myLine side1,myLine side2,myLine side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    public Triangle(){
        double side1startX=100, side1startY=200, side1endX=20, side1endY=200;
        double side2startX=20, side2startY=200, side2endX=150, side2endY=250;
        double side3startX=150, side3startY=250, side3endX=100, side3endY=200;
        this.side1=new myLine(side1startX,side1startY,side1endX,side1endY);
        this.side2=new myLine(side2startX,  side2startY, side2endX, side2endY);
        this.side3=new myLine(side3startX,  side3startY, side3endX, side3endY);
    }
    //todo rename function name
    public Line[] rotate(double[] lineSX,double[] lineSY,double[] lineEX, double[] lineEY){
        double m = -lineSX[0],n = -lineSY[0];
// матрица перемещения
        double [][] parallelTransfer = {{1,0,0},
                {0,1,0},
                {m,n,1}};
        double [][] newTriangle = {{side1.startX,side1.startY,1},
                                   {side2.startX,side2.startY,1},
                                   {side3.startX,side3.startY,1}};
        double [][] newLine = {{lineSX[0], lineSY[0], 1},
                {lineEX[0], lineEY[0], 1}};

        newTriangle = matrixMultiply(newTriangle, parallelTransfer, 3, 3, 3);
        newLine = matrixMultiply(newLine, parallelTransfer, 2, 3, 3);

//Поворот относительно начало координат до совпадения с осью Ox
        double fi = -Math.atan((newLine[1][1])/(newLine[1][0]));
        double [][] matrRotate = {{Math.cos(fi), Math.sin(fi)}, {-Math.sin(fi),
                Math.cos(fi)}};
        newTriangle = matrixMultiply(newTriangle, matrRotate, 3, 2, 2);
//Отражение относительно оси Ox
        double matrReflection[][] = {{1, 0}, {0, -1}};
        newTriangle = matrixMultiply(newTriangle, matrReflection, 3, 2, 2);
//Обратный поворот
        double [][] unmatrRotate = {{Math.cos(fi), -Math.sin(fi)},
                {Math.sin(fi), Math.cos(fi)}};
        newTriangle = matrixMultiply(newTriangle, unmatrRotate, 3,2,2);
//Возвращение в начальную позицию
        double [][] unparallelTransfer = {{1,0,0},{0,1,0},{-m,-n,1}};
        double [][] revhelpnewTriangle =
                {{newTriangle[0][0],newTriangle[0][1],1}, {newTriangle[1][0],newTriangle[1][1],1},
                        {newTriangle[2][0],newTriangle[2][1],1}};
        revhelpnewTriangle = matrixMultiply(revhelpnewTriangle,
                unparallelTransfer, 3,3,3);
        for(int i =0; i <3 ; ++i){
            for(int j = 0; j <2; ++j){
                newTriangle[i][j] = revhelpnewTriangle[i][j];
            }
        }
        Line newside1 = new Line();
        Line newside2 = new Line();
        Line newside3 = new Line();
        Line[] linesArray = new Line[3];

        newside1.setStartX(400 + newTriangle[0][0]);
        newside1.setStartY(400 - newTriangle[0][1]);
        newside1.setEndX(400 + newTriangle[1][0]);
        newside1.setEndY(400 - newTriangle[1][1]);
        newside2.setStartX(400 + newTriangle[1][0]);
        newside2.setStartY(400 - newTriangle[1][1]);
        newside2.setEndX(400 + newTriangle[2][0]);
        newside2.setEndY(400 - newTriangle[2][1]);
        newside3.setStartX(400 + newTriangle[2][0]);
        newside3.setStartY(400 - newTriangle[2][1]);
        newside3.setEndX(400 + newTriangle[0][0]);
        newside3.setEndY(400 - newTriangle[0][1]);

        linesArray[0] = newside1;
        linesArray[1] = newside2;
        linesArray[2] = newside3;
        return linesArray;
    }
    public double[][] matrixMultiply(double[][] first, double[][] second, int l, int n, int m){
        double[][] result = new double[l][n];
        for (int i=0; i<l; ++i)
            for (int j=0; j<n; ++j)
                for (int k=0; k<m; ++k)
                    result[i][j] += first[i][k] * second[k][j];
        return result;
    }
    public Line[] getSides(){
        Line[] linesArray = new Line[3];
        linesArray[0] = side1.line;
        linesArray[1] = side2.line;
        linesArray[2] = side3.line;
        return linesArray;
    }
}


package com.example.compgrahp_lr1;

import javafx.scene.shape.Line;

public class myLine{
    double startX;
    double startY;
    double endX;
    double endY;
    Line line;
    public myLine(double startX,double startY,double endX,double endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        line = new Line(400+startX,400-startY,400+endX,400-endY);
    }
}

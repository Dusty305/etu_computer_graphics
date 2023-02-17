package com.example.compgrahp_lr1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) {
// Координаты прямой
        double[] lineSX = new double[1];
        double[] lineSY = new double[1];
        double[] lineEX = new double[1];
        double[] lineEY = new double[1];
//оси координат
        Line oy = new Line(400,800,400,0);
        Line ox = new Line(0,400,800,400);
//стрелочки
        Line arrow21 = new Line(800,400,790,395);
        Line arrow22 = new Line(800,400,790,405);
        Line arrow11 = new Line(400,0,395,10);
        Line arrow12 = new Line(400,0,405,10);
// прямая отражения
        Line refLine = new Line();

//задаем начальный треугольник
        Triangle baseTriangle = new Triangle();

        Group root = new Group();
        Button enterLine = new Button("Задать координаты прямой");
        Button Reflect = new Button("Отобразить треугольник");
        HBox buttons = new HBox(30, enterLine, Reflect);
        BorderPane pane = new BorderPane();
        buttons.setAlignment(Pos.CENTER);
        pane.setTop(buttons);
        pane.setCenter(root);
//новые стороны треугольника
        final Line[][] newSides = {new Line[3]};
//функция отражения
        Reflect.setOnAction(actionEvent -> {
            newSides[0] =  baseTriangle.rotate(lineSX,lineSY,lineEX,lineEY);
            root.getChildren().add(newSides[0][0]);
            root.getChildren().add(newSides[0][1]);
            root.getChildren().add(newSides[0][2]);

        });
        enterLine.setOnAction(event -> {
            final Stage dialog = new Stage();
            dialog.setTitle("Задать прямую");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            dialog.setHeight(250);
            dialog.setWidth(250);
            GridPane pane1 =new GridPane();
            pane1.setAlignment(Pos.CENTER);
            BorderPane boPane=new BorderPane(pane1);
            TextField enterStartX=new TextField("X1");
            enterStartX.setPrefColumnCount(15);
            pane1.add(enterStartX, 1, 1);
            TextField enterStartY=new TextField("Y1");
            enterStartY.setPrefColumnCount(15);
            pane1.add(enterStartY, 1, 2);
            TextField enterEndX=new TextField("X2");
            enterEndX.setPrefColumnCount(15);
            pane1.add(enterEndX, 1, 3);
            TextField enterEndY=new TextField("Y2");
            enterEndX.setPrefColumnCount(15);
            pane1.add(enterEndY, 1, 4);
            Button btn=new Button("Ввод");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    lineSX[0] = Double.parseDouble(enterStartX.getText());
                    refLine.setStartX(400 + lineSX[0]);
                    lineSY[0] = Double.parseDouble(enterStartY.getText());
                    refLine.setStartY(400 - lineSY[0]);
                    lineEX[0] = Double.parseDouble(enterEndX.getText());
                    refLine.setEndX(400 + lineEX[0]);
                    lineEY[0] = Double.parseDouble(enterEndY.getText());
                    refLine.setEndY(400 - lineEY[0]);
                }
            });
            pane1.add(btn, 1, 5);
            Scene dialogScene = new Scene(boPane, 400, 400);
            dialog.setScene(dialogScene);
            dialog.show();
        });

        root.getChildren().add(oy);
        root.getChildren().add(arrow11);
        root.getChildren().add(arrow12);
        root.getChildren().add(ox);
        root.getChildren().add(arrow21);
        root.getChildren().add(arrow22);
        Line[] lineArr = baseTriangle.getSides();
        root.getChildren().add(lineArr[0]);
        root.getChildren().add(lineArr[1]);
        root.getChildren().add(lineArr[2]);
        refLine.setStyle("-fx-stroke: red;");
        root.getChildren().add(refLine);
        Scene scene = new Scene(pane,800,800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Отображение треугольника");
        primaryStage.setHeight(800);
        primaryStage.setWidth(1200);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
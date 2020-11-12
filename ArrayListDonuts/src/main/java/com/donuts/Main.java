package com.donuts;

//TODO consider adding more classes so that the code is easier to read in the main method (abstraction be good)
//TODO add image files for all classes (transparent backgrounds)

import com.donuts.fillings.*;
import com.donuts.toppings.*;
import com.donuts.ChooseTypeScene.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    Stage window;

    double totalCost = 0;
    final int WINDOW_WIDTH = 600;
    final int WINDOW_HEIGHT = 300;
    Scene welcomeScene, donutTypeScene, glazeTypeScene, fillingTypeScene, chooseToppingsScene, finalScene;

    final Donut[] DONUT_ARRAY = new Donut[] {new OriginalDonut(), new FilledDonut()};
    final Scene[] CORRESPONDING_DONUT_SCENE = new Scene[] {glazeTypeScene, fillingTypeScene};
    final Filling[] FILLING_ARRAY = new Filling[] {new BostonCreamFilling(), new ChocolateCreamFilling(), new StrawberryJamFilling()};
    final Scene[] CORRESPONDING_FILLING_SCENE = new Scene[] {glazeTypeScene};
    final Topping[] GLAZE_ARRAY = new Topping[] {new ChocolateGlaze(), new ClearGlaze(), new MapleGlaze()};
    final Scene[] CORRESPONDING_GLAZE_SCENE = new Scene[] {chooseToppingsScene};
    final Topping[] TOPPING_ARRAY = new Topping[] {new Bacon(), new RainbowSprinkles(), new ToastedCoconut()};
    final Scene[] CORRESPONDING_TOPPING_SCENE = new Scene[] {finalScene};

    //main method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Sunshine Donuteria");

        //welcome scene
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(20);
        mainVBox.setAlignment(Pos.CENTER);

        Button startBtn = new Button("Start");
        startBtn.setPrefSize(100, 30);
        startBtn.setOnAction(e -> window.setScene(new ChooseTypeScene().chooseTypeScene(window, totalCost, DONUT_ARRAY, CORRESPONDING_DONUT_SCENE)));
        //startBtn.setOnAction(e -> window.setScene(chooseTypeScene(DONUT_ARRAY, CORRESPONDING_DONUT_SCENE)));

        Label welcomeLabel = new Label("Welcome to Sunshine Donuteria!");
        welcomeLabel.setStyle("-fx-font-size: 30");

        mainVBox.getChildren().addAll(welcomeLabel, startBtn);

        welcomeScene = new Scene(mainVBox, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
        window.setScene(welcomeScene);
        window.show();

    }

}
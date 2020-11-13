package com.donuts;

import com.donuts.fillings.*;
import com.donuts.toppings.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends Application {
    Stage window;

    double totalCost = 0;
    final int WINDOW_WIDTH = 600;
    final int WINDOW_HEIGHT = 300;
    Scene welcomeScene, donutTypeScene, glazeTypeScene, fillingTypeScene, chooseToppingsScene, finalScene;

    Donut[] DONUT_ARRAY = new Donut[] {new OriginalDonut(), new FilledDonut()};
    Filling[] FILLING_ARRAY = new Filling[] {new BostonCreamFilling(), new ChocolateCreamFilling(), new StrawberryJamFilling()};
    Scene[] CORRESPONDING_FILLING_SCENE = new Scene[] {glazeTypeScene};
    Topping[] GLAZE_ARRAY = new Topping[] {new ChocolateGlaze(), new ClearGlaze(), new MapleGlaze()};
    Scene[] CORRESPONDING_GLAZE_SCENE = new Scene[] {chooseToppingsScene};
    Topping[] TOPPING_ARRAY = new Topping[] {new Bacon(), new RainbowSprinkles(), new ToastedCoconut()};
    Scene[] CORRESPONDING_TOPPING_SCENE = new Scene[] {finalScene};

    //main method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Sunshine Donuteria");

        //filling scene
        VBox fillingVBox = new VBox();
        fillingVBox.setSpacing(20);
        fillingVBox.setAlignment(Pos.CENTER);

        Label fillingHeader = new Label("Choose your filling:");
        fillingHeader.setStyle("-fx-font-size: 30");
        fillingTypeScene = new Scene(fillingHeader, WINDOW_WIDTH, WINDOW_HEIGHT);
        fillingTypeScene = new ChooseTypeScene().chooseTypeScene(window, totalCost, FILLING_ARRAY, CORRESPONDING_FILLING_SCENE);
        Scene[] CORRESPONDING_DONUT_SCENE = new Scene[] {glazeTypeScene, fillingTypeScene};

        //welcome scene
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(20);
        mainVBox.setAlignment(Pos.CENTER);

        Button startBtn = new Button("Start");
        startBtn.setPrefSize(100, 30);
        donutTypeScene = new ChooseTypeScene().chooseTypeScene(window, totalCost, DONUT_ARRAY, CORRESPONDING_DONUT_SCENE);
        startBtn.setOnAction(e -> window.setScene(donutTypeScene));

        Label welcomeLabel = new Label("Welcome to Sunshine Donuteria!");
        welcomeLabel.setStyle("-fx-font-size: 15; -fx-text-fill: #ae8fb8");

        Image coverImg = null;

        try {
            coverImg = new Image(new FileInputStream("src/main/images/cover.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(coverImg);
        imageView.setFitWidth(160);
        imageView.setPreserveRatio(true);
        mainVBox.getChildren().addAll(welcomeLabel, imageView, startBtn);

        welcomeScene = new Scene(mainVBox, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
        window.setScene(welcomeScene);
        window.show();



    }

}
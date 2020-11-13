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
    Donut myDonut = new OriginalDonut();

    final int WINDOW_WIDTH = 600;
    final int WINDOW_HEIGHT = 300;
    Scene welcomeScene, donutTypeScene, glazeTypeScene, fillingTypeScene, chooseToppingsScene, finalScene;

    Donut[] DONUT_ARRAY = new Donut[] {new OriginalDonut(), new FilledDonut()};
    Filling[] FILLING_ARRAY = new Filling[] {new BostonCreamFilling(), new ChocolateCreamFilling(), new StrawberryJamFilling()};
    Topping[] GLAZE_ARRAY = new Topping[] {new ChocolateGlaze(), new ClearGlaze(), new MapleGlaze()};
    Topping[] TOPPING_ARRAY = new Topping[] {new Bacon(), new RainbowSprinkles(), new ToastedCoconut()};

    //main method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Sunshine Donuteria");

        //final scene
        VBox finalVBox = new VBox();
        Label totalLabel = new Label("Total cost: " + myDonut.getPrice());
        totalLabel.setStyle("-fx-font-size: 20");
        finalVBox.getChildren().add(totalLabel);
        finalScene = new Scene(finalVBox, WINDOW_WIDTH, WINDOW_HEIGHT);

        Scene[] CORRESPONDING_TOPPING_SCENE = new Scene[] {finalScene};

        //topping scene
        chooseToppingsScene = new ChooseTypeScene(myDonut).chooseTypeScene(window, TOPPING_ARRAY, CORRESPONDING_TOPPING_SCENE);
        Scene[] CORRESPONDING_GLAZE_SCENE = new Scene[] {chooseToppingsScene};

        //glaze scene
        glazeTypeScene = new ChooseTypeScene(myDonut).chooseTypeScene(window, GLAZE_ARRAY, CORRESPONDING_GLAZE_SCENE);
        Scene[] CORRESPONDING_FILLING_SCENE = new Scene[] {glazeTypeScene};

        //filling scene
        myDonut = new FilledDonut();
        fillingTypeScene = new ChooseTypeScene(myDonut).chooseTypeScene(window, FILLING_ARRAY, CORRESPONDING_FILLING_SCENE);
        Scene[] CORRESPONDING_DONUT_SCENE = new Scene[] {glazeTypeScene, fillingTypeScene};

        //welcome scene
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(20);
        mainVBox.setAlignment(Pos.CENTER);

        Button startBtn = new Button("Start");
        startBtn.setPrefSize(100, 30);
        ChooseTypeScene donutChooseTypeScne = new ChooseTypeScene(myDonut);
        donutTypeScene = donutChooseTypeScne.chooseTypeScene(window, DONUT_ARRAY, CORRESPONDING_DONUT_SCENE);
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
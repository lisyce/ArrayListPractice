package com.donuts;

//TODO consider adding more classes so that the code is easier to read in the main method (abstraction be good)
//TODO add image files for all classes (transparent backgrounds)

import com.donuts.fillings.*;
import com.donuts.fillings.StrawberryJamFilling;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    Stage window;

    double totalCost;
    final int windowWidth = 600;
    final int windowHeight = 300;
    Scene welcomeScene, donutTypeScene, glazeTypeScene, fillingTypeScene, chooseToppingsScene, finalScene;

    final Donut[] donutArray = new Donut[] {new OriginalDonut(), new FilledDonut()};
    final Scene[] correspondingDonutScene = new Scene[] {glazeTypeScene, fillingTypeScene};
    final Filling[] fillingArray = new Filling[] {new BostonCreamFilling(), new ChocolateCreamFilling(), new StrawberryJamFilling()};
    final Scene[] corespondingFillingScene = new Scene[] {glazeTypeScene};

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
        startBtn.setOnAction(e -> window.setScene(chooseTypeScene(donutArray, correspondingDonutScene)));

        Label welcomeLabel = new Label("Welcome to Sunshine Donuteria!");
        welcomeLabel.setStyle("-fx-font-size: 30");

        mainVBox.getChildren().addAll(welcomeLabel, startBtn);

        welcomeScene = new Scene(mainVBox, windowWidth, windowHeight, Color.WHITE);
        window.setScene(welcomeScene);
        window.show();

    }

    public Scene chooseTypeScene(Product[] productList, Scene[] correspondingScenes) {
        //choose type scene
        VBox chooseTypeVBox = new VBox();
        chooseTypeVBox.setSpacing(20);
        chooseTypeVBox.setAlignment(Pos.CENTER);
        HBox typeHBox = GenericHBoxPickerFromArrayList(productList, correspondingScenes);
        chooseTypeVBox.getChildren().add(typeHBox);


        return new Scene(chooseTypeVBox, windowWidth, windowHeight, Color.WHITE);
    }

    //doesn't work with toppings
    public HBox GenericHBoxPickerFromArrayList(Product[] productList, Scene[] correspondingScenes) {
        HBox choicesHBox = new HBox();
        choicesHBox.setAlignment(Pos.CENTER);

        for (int i=0; i<=productList.length-1; i++) {
            Object product = productList[i];
            choicesHBox.setSpacing(50);

            //add the image
            Image productImage = null;
            //TODO remove this try/catch once all products have an image associated if that works lol
            try {
                productImage = new Image(new FileInputStream(((Product) product).getImageSrc()));
                System.out.println(((Product) product).getImageSrc());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView imageView = new ImageView(productImage);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            VBox choiceVBox = new VBox();
            choiceVBox.getChildren().add(imageView);

            //add the item label
            Label itemLabel = new Label(((Product) product).getName());
            itemLabel.setStyle("-fx-font-size: 15");
            choiceVBox.getChildren().add(itemLabel);

            //add the button
            Button selectButton = new Button("Select");
            selectButton.setPrefSize(75, 30);

            Scene buttonScene = correspondingScenes[0];

            try {
                buttonScene = correspondingScenes[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                buttonScene = correspondingScenes[0];
            }

            Scene finalButtonScene = buttonScene;
            selectButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    totalCost += ((Product) product).getPrice();
                    System.out.println("Scene: " + finalButtonScene);
                    window.setScene(finalButtonScene);
                }
            });

                    choiceVBox.getChildren().add(selectButton);
            choiceVBox.setAlignment(Pos.BOTTOM_CENTER);
            choiceVBox.setSpacing(10);

            choicesHBox.getChildren().add(choiceVBox);
        }

        return choicesHBox;
    }


}
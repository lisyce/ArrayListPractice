package com.donuts;

//TODO consider adding more classes so that the code is easier to read in the main method (abstraction be good)
//TODO add image files for all classes (transparent backgrounds)

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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    Stage window;

    public double totalCost;
    public final int windowWidth = 600;
    public final int windowHeight = 300;
    public Scene welcomeScene, donutTypeScene, glazeTypeScene, fillingTypeScene, chooseToppingsScene, finalScene;

    public final ArrayList<Donut> donutArrayList = new ArrayList<>(List.of(new OriginalDonut(), new FilledDonut()));
    public final ArrayList<Scene> correspondingDonutArrayList = new ArrayList<>(List.of(glazeTypeScene, fillingTypeScene));


    //main method
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Sunshine Donuteria");

        //welcome scene
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(20);
        mainVBox.setAlignment(Pos.CENTER);

        Button startBtn = new Button("Start");
        startBtn.setPrefSize(100, 30);
        //startBtn.setOnAction(e -> stage.setScene(chooseTypeScene(donutArrayList, correspondingDonutArrayList)));

        Label welcomeLabel = new Label("Welcome to Sunshine Donuteria!");
        welcomeLabel.setStyle("-fx-font-size: 30");

        mainVBox.getChildren().addAll(welcomeLabel, startBtn);

        welcomeScene = new Scene(mainVBox, windowWidth, windowHeight, Color.WHITE);
        window.setScene(welcomeScene);
        window.show();

    }

    //currently testing with the donut picker
    public Scene chooseTypeScene(ArrayList productList, ArrayList<Scene> correspondingScenes) {
        //choose type scene
        VBox chooseTypeVBox = new VBox();
        chooseTypeVBox.setSpacing(20);
        chooseTypeVBox.setAlignment(Pos.CENTER);
        //HBox typeHBox = GenericHBoxPickerFromArrayList(productList, correspondingScenes);
        //chooseTypeVBox.getChildren().add(typeHBox);


        return new Scene(chooseTypeVBox, windowWidth, windowHeight, Color.WHITE);
    }

    //doesn't work with toppings
    public HBox GenericHBoxPickerFromArrayList(ArrayList productList, ArrayList<Scene> correspondingScenes) {
        HBox choicesHBox = new HBox();
        choicesHBox.setAlignment(Pos.CENTER);

        for (int i=0; i<productList.size()-1; i++) {
            Object product = productList.get(i);
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

            //TODO Handle button presses with a corresponding arraylist of scenes to set
            //the i value in the top for loop can match up with the scene that the button needs to send the user to
            //this will work for the donut glaze, and filling selections
            //add an if/else so that the topping selection can implement features to select multiple toppings before proceeding
            selectButton.setOnAction(e -> {
                totalCost += ((Product) product).getPrice();

            });

            choiceVBox.getChildren().add(selectButton);
            choiceVBox.setAlignment(Pos.BOTTOM_CENTER);
            choiceVBox.setSpacing(10);

            choicesHBox.getChildren().add(choiceVBox);
        }

        return choicesHBox;
    }


}
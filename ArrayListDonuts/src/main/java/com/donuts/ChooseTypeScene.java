package com.donuts;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ChooseTypeScene {

    public ChooseTypeScene() {
    }

    public Scene chooseTypeScene(Stage window, Double costVariable, Product[] productList, Scene[] correspondingScenes) {
        //choose type scene
        VBox chooseTypeVBox = new VBox();
        chooseTypeVBox.setSpacing(20);
        chooseTypeVBox.setAlignment(Pos.CENTER);
        HBox typeHBox = GenericHBoxPickerFromArrayList(window, costVariable, productList, correspondingScenes);
        chooseTypeVBox.getChildren().add(typeHBox);


        return new Scene(chooseTypeVBox, window.getWidth(), window.getHeight(), Color.WHITE);
    }

    private HBox GenericHBoxPickerFromArrayList(Stage window, Double costVariable, Product[] productList, Scene[] correspondingScenes) {
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
            selectButton.setOnAction(e -> {
                incrementCost(costVariable, ((Product) product).getPrice());
                System.out.println("Scene: " + finalButtonScene);
                window.setScene(finalButtonScene);
            });

            choiceVBox.getChildren().add(selectButton);
            choiceVBox.setAlignment(Pos.BOTTOM_CENTER);
            choiceVBox.setSpacing(10);

            choicesHBox.getChildren().add(choiceVBox);
        }

        return choicesHBox;
    }

    private void incrementCost(Double variable, Double increment) {
        variable += increment;
    }

}

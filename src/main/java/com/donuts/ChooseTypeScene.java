package com.donuts;

import com.donuts.fillings.Filling;
import com.donuts.toppings.Topping;
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

    private Donut donut;


    public ChooseTypeScene(Donut donut) {
        this.donut = donut;
    }

    public Scene chooseTypeScene(Stage window, Product[] productList, Scene[] correspondingScenes) {
        //choose type scene
        VBox chooseTypeVBox = new VBox();
        chooseTypeVBox.setSpacing(20);
        chooseTypeVBox.setAlignment(Pos.CENTER);
        HBox typeHBox = GenericHBoxPickerFromArrayList(window, productList, correspondingScenes);
        chooseTypeVBox.getChildren().add(typeHBox);


        return new Scene(chooseTypeVBox, 600, 300, Color.WHITE);
    }

    private HBox GenericHBoxPickerFromArrayList(Stage window, Product[] productList, Scene[] correspondingScenes) {
        HBox choicesHBox = new HBox();
        choicesHBox.setAlignment(Pos.CENTER);

        for (int i=0; i<=productList.length-1; i++) {
            Product product = productList[i];
            choicesHBox.setSpacing(50);

            //add the image
            Image productImage = null;
            try {
                productImage = new Image(new FileInputStream(product.getImageSrc()));
                System.out.println(product.getImageSrc());

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
            Label itemLabel = new Label(product.getName());
            itemLabel.setStyle("-fx-font-size: 15");
            choiceVBox.getChildren().add(itemLabel);

            //add the button
            Button selectButton = new Button("Select");
            selectButton.setPrefSize(75, 30);

            Scene buttonScene = correspondingScenes[0];
            try {
                buttonScene = correspondingScenes[i];
            } catch (ArrayIndexOutOfBoundsException e) {

            }

            Scene finalButtonScene = buttonScene;
            selectButton.setOnAction(e -> {
                if (product instanceof Filling) {
                    System.out.println("adding filling");
                    (donut).addFilling((Filling) product);
                }

                try {
                    donut.addTopping((Topping) product);
                    System.out.println("adding topping");
                } catch (Exception ClassCastException) {
                    if(product instanceof OriginalDonut) {
                        donut = new OriginalDonut();
                        System.out.println("new original donut");
                    } else if(product instanceof  FilledDonut) {
                        donut = new FilledDonut();
                        System.out.println("new filled donut");
                    }
                }
                window.setScene(finalButtonScene);
            });

            choiceVBox.getChildren().add(selectButton);
            choiceVBox.setAlignment(Pos.BOTTOM_CENTER);
            choiceVBox.setSpacing(10);

            choicesHBox.getChildren().add(choiceVBox);
        }
        System.out.println("Price of Donut: " + donut.getPrice());
        return choicesHBox;
    }



}

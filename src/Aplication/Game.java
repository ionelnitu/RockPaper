package Aplication;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Game {

    static Random random = new Random();
    public ImageView computer(){
        int computer = random.nextInt(3) + 1;
        Image rock = new Image("resources/rock.png");
        Image scissors = new Image("resources/scissors.png");
        Image paper = new Image("resources/paper.png");
        ImageView imageView = new ImageView();
        switch (computer) {
            case 1:
                imageView.setImage(rock);
                break;

            case 2:
                imageView.setImage(scissors);
                break;
            case 3:
                imageView.setImage(paper);
                break;
            default: {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.showAndWait();
                alert.setContentText("Error!");
            }
        }
        return imageView;

    }




}

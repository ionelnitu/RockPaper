package Aplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.Locale;
import java.util.Objects;

public class Controller {
    MySql mySql = new MySql();
    Game game = new Game();
    Image image = new Image("resources/win.gif");
    ImageView win = new ImageView(image);


    @FXML
    TextField userName, password, userCreate, passCreate;

    @FXML
    ImageView player, computer, playerImage;

    @FXML
    Label points;
    static Person person = new Person();


    @FXML
    private void LogIn(ActionEvent actionEvent) throws SQLException, IOException {
        person.setPass(password.getText());
        person.setUserName(userName.getText());
        String points2 = String.valueOf(mySql.Points(person.getUserName(), person.getPass()));
        mySql.logIN(userName.getText(), password.getText());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/gameFrame.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Create_Page(ActionEvent actionEvent) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/secondeFrame.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void CreateUser(ActionEvent actionEvent) throws SQLException, IOException {
        mySql.Create(userCreate.getText(), passCreate.getText());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/mainFrame.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Rock() throws SQLException {
        player.setImage(new Image("resources/rock.png"));
        computer.setImage(game.computer().getImage());
        if (Objects.equals(player.getImage().getUrl(), computer.getImage().getUrl())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Draw");
            alert.showAndWait();
        } else if (computer.getImage().getUrl().contains("scissors.png")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Win!");
            Integer a = mySql.Points(person.getUserName(), person.getPass());
            Integer b = a + 1;
            mySql.Update(b, person.getUserName(), person.getPass());
            points.setText(String.valueOf(b));
            alert.setGraphic(win);
            alert.showAndWait();
        } else if (computer.getImage().getUrl().contains("paper.png")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Lose");
            alert.showAndWait();
        }

    }

    @FXML
    public void Paper() throws SQLException {
        player.setImage(new Image("resources/paper.png"));
        computer.setImage((Image) game.computer().getImage());
        if (Objects.equals(player.getImage().getUrl(), computer.getImage().getUrl())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Draw!");
            alert.showAndWait();
        } else if (computer.getImage().getUrl().contains("scissors.png")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Lose");
            alert.showAndWait();
        } else if (computer.getImage().getUrl().contains("rock.png")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Win!");
            Integer a = mySql.Points(person.getUserName(), person.getPass());
            Integer b = a + 1;
            points.setText(String.valueOf(b));
            mySql.Update(b, person.getUserName(), person.getPass());
            alert.setGraphic(win);
            alert.showAndWait();
        }
    }

    @FXML
    public void Scissors() throws SQLException {
        player.setImage(new Image("resources/scissors.png"));
        computer.setImage((Image) game.computer().getImage());
        if (Objects.equals(player.getImage().getUrl(), computer.getImage().getUrl())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Draw");
            alert.showAndWait();
        } else if (computer.getImage().getUrl().contains("rock.png")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Lose");
            alert.showAndWait();
        } else if (computer.getImage().getUrl().contains("paper.png")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Win!");
            Integer a = mySql.Points(person.getUserName(), person.getPass());
            Integer b = a + 1;
            points.setText(String.valueOf(b));
            mySql.Update(b, person.getUserName(), person.getPass());
            alert.setGraphic(win);
            alert.showAndWait();
        }
    }

    @FXML
    public void ChoosePhoto() throws IOException {
        FileChooser fileChooser= new FileChooser();
        File file=fileChooser.showOpenDialog(null);
        Image image1=new Image(file.toURI().toURL().toString());
        playerImage.setImage(image1);
    }



}

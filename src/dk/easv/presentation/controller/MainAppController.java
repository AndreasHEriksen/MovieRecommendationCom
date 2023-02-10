package dk.easv.presentation.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {

    @FXML
    public GridPane gridPaneMovies;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                String filename = "C:\\Users\\jonas\\Desktop\\Github ekstra\\MovieRecommendationCom\\data\\Image\\billede.png";
                ImageView imgView = new ImageView(filename);
                imgView.setFitHeight(225);
                imgView.setFitWidth(150);

                //VBox vbox = new VBox();
                //vbox.getChildren().add(imgView);

                //gridPaneMovies.getChildren().add(vbox);

                VBox vbox = new VBox();
                vbox.getChildren().add(imgView);
                //i = i -1;
                gridPaneMovies.add(vbox,j,i);
                //gridPaneMovies.add(imgView,0,i);
            }
       }
    }
}

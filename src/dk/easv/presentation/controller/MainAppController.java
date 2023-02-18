package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {
    private AppModel model;
    private Movie movie;
    private ObservableList<Movie> currentMovieView = null;

    @FXML
    public GridPane gridPaneMovies;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                String filename = "C:\\Users\\thoma\\Documents\\GitHub\\MovieRecommendationCom\\data\\Image\\billede.png";
                ImageView imgView = new ImageView(filename);
                imgView.setFitHeight(225);
                imgView.setFitWidth(150);
                currentMovieView = model.getObsTopMovieNotSeen();
                //var movie = currentMovieView.get(i);

                //VBox vbox = new VBox();
                //vbox.getChildren().add(imgView);

                //gridPaneMovies.getChildren().add(vbox);

                VBox vbox = new VBox();
                vbox.getChildren().add(imgView);
                vbox.getChildren().add(new Label(movie.getTitle()));
                //i = i -1;
                gridPaneMovies.add(vbox,j,i);

                //gridPaneMovies.add(imgView,0,i);
            }
       }*/
    }

    public void setModel(AppModel model) {
        this.model = model;


        model.getObsTopMovieNotSeen();

        model.loadUsers();
        model.loadData(model.getObsLoggedInUser());

        handleTopMovies();
    }

    public void handleTopMovies(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {

                String filename = "C:\\Users\\thoma\\Documents\\GitHub\\MovieRecommendationCom\\data\\Image\\billede.png";
                ImageView imgView = new ImageView(filename);
                imgView.setFitHeight(225);
                imgView.setFitWidth(150);
                currentMovieView = model.getObsTopMovieNotSeen();

                //VBox vbox = new VBox();
                //vbox.getChildren().add(imgView);

                //gridPaneMovies.getChildren().add(vbox);

                VBox vbox = new VBox();
                vbox.getChildren().add(imgView);
                var movie = currentMovieView.get(j);
                vbox.getChildren().add(new Label(movie.getTitle()));

                //i = i -1;
                gridPaneMovies.add(vbox,j,i);

                //gridPaneMovies.add(imgView,0,i);
            }

        }
    }

    public void handleMoreMovies(ActionEvent actionEvent) {

    }

    public void handleLessMovies(ActionEvent actionEvent) {
    }

}

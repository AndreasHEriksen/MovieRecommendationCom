package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.lang.model.util.Elements;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {
    @FXML
    private Button btnHandleTopMoviesNotSeen;
    @FXML
    private Button btnHandleTopMoviesSeen;
    private AppModel model;
    @FXML
    private Movie movie;
    private ObservableList<Movie> currentMovieView = null;

    private int amountMovies = 0;

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

    public void setModel(AppModel model) throws IOException {
        this.model = model;
        model.getObsTopMovieNotSeen();
        model.loadUsers();
        model.loadData(model.getObsLoggedInUser());
        handleTopMoviesNotSeen();
    }
    @FXML
    private void handleTopMoviesNotSeen() throws IOException {
        amountMovies = -1; // -1 because the method increases +1 every loop
        gridPaneMovies.getChildren().clear();
        currentMovieView = model.getObsTopMovieNotSeen();
        showMovies();
    }
    @FXML
    private void handleTopMoviesSeen(ActionEvent actionEvent) throws IOException {
        amountMovies = -1; // -1 because the method increases +1 every loop
        gridPaneMovies.getChildren().clear();
        currentMovieView = model.getObsTopMovieSeen();
        showMovies();
    }
    @FXML
    private void handleMoreMovies(ActionEvent actionEvent) throws IOException {
        int increaseAmount = 8;
        if(increaseAmount > currentMovieView.size())
        {
            return;
        }
        else{
            showMovies();

            }
    }
    @FXML
    private void handleLessMovies(ActionEvent actionEvent) throws IOException {
        int decreaseAmount = 16;
        if (amountMovies - decreaseAmount < -1) { // -1 Because else we wont see the last movie of the list
            return;
        } else {
            amountMovies = amountMovies - decreaseAmount;
            showMovies();
        }
    }

    private void showMovies() throws IOException {
        gridPaneMovies.getChildren().clear();
        for (int rows = 0; rows < 2; rows++) {
            for (int column = 0; column < 4; column++) {
                amountMovies++;
                movie = currentMovieView.get(amountMovies); // Gets the current gridpane value
                // Setting up the image
                String filename = imageChooser();
                ImageView imgView = new ImageView(filename);
                imgView.setFitHeight(225);
                imgView.setFitWidth(150);
                // Instantiating the VBox
                VBox vbox = new VBox();
                vbox.getChildren().add(imgView);
                vbox.getChildren().add(new Label(movie.getTitle()));

                gridPaneMovies.add(vbox, column, rows);
            }
        }
    }
    private String imageChooser() throws IOException {
        String searchQuery = "https://www.google.com/search?q=" + movie.getTitle() + "&tbm=isch";
        Document document = (Document) Jsoup.connect(searchQuery).get();
        Elements images = (Elements) document.getElementsByTagName("img");

        /*
        Random r = new Random();
        String filePath = "dk/easv/presentation/view/Images/Movieposters/m"+r.nextInt(24) + ".jpg";
        return filePath;
         */
        return searchQuery;
    }

}

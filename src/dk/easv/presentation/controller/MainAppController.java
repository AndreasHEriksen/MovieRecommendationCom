package dk.easv.presentation.controller;

import dk.easv.entities.Movie;
import dk.easv.presentation.model.AppModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {
    public Text TopMoviesNotSeen;
    private AppModel model;
    @FXML
    private Movie movie;
    private ObservableList<Movie> currentMovieView = null;

    private int amountMovies = 0;

    @FXML
    public GridPane gridPaneMovies;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Todo: Have it Awesome :))
    }

    public void setModel(AppModel model) {
        this.model = model;
        model.getObsTopMovieNotSeen();
        model.loadUsers();
        model.loadData(model.getObsLoggedInUser());
        handleTopMoviesNotSeen();
    }
    @FXML
    private void handleTopMoviesNotSeen() {
        amountMovies = 0;
        gridPaneMovies.getChildren().clear();
        currentMovieView = model.getObsTopMovieNotSeen();
        TopMoviesNotSeen.setText("Top movies you have not seen");



        showMovies();
    }
    @FXML
    private void handleTopMoviesSeen(ActionEvent actionEvent) {
        amountMovies = 0;
        gridPaneMovies.getChildren().clear();
        currentMovieView = model.getObsTopMovieSeen();
        TopMoviesNotSeen.setText("Top movies you have seen");

        showMovies();
    }
    @FXML
    private void handleMoreMovies(ActionEvent actionEvent) {
        int increaseAmount = amountMovies + 8;
        if(increaseAmount > currentMovieView.size())
        {
            return;
        }
        else{
            showMovies();
            }
    }
    @FXML
    private void handleLessMovies(ActionEvent actionEvent) {
        int decreaseAmount = 16;
        if (amountMovies - decreaseAmount < 0) {
            return;
        } else {
            amountMovies = amountMovies - decreaseAmount;
            showMovies();
        }
    }

    private void showMovies() {
        gridPaneMovies.getChildren().clear();
        for (int rows = 0; rows < 2; rows++) {
            for (int column = 0; column < 4; column++) {
                movie = currentMovieView.get(amountMovies); // Gets the current gridpane value
                amountMovies++;
                // Setting up the image
                String filename = imageChooser();
                ImageView imgView = new ImageView(filename);
                imgView.setFitHeight(225);
                imgView.setFitWidth(150);
                //Adds the text in the right gridpane window
                addTextToVbox(imgView, column,rows);
            }
        }
    }

    private String imageChooser(){
        Random r = new Random();
        String filePath = "dk/easv/presentation/view/Images/Movieposters/m"+r.nextInt(24) + ".jpg";
        return filePath;
    }
    private void addTextToVbox(ImageView imgView, int column, int rows) {
        // Instantiating the VBox
        VBox vbox = new VBox();
        vbox.getChildren().add(imgView);
        Label title = new Label(movie.getTitle());
        title.setStyle("-fx-text-fill: White");
        vbox.getChildren().add(title);
        String averageRating = movie.getAverageRating() + "";
        String shortRating = averageRating.substring(0,3);
        Label rating = new Label(shortRating);
        rating.setStyle("-fx-text-fill: White");
        vbox.getChildren().add(rating);
        gridPaneMovies.add(vbox, column, rows);
    }

    public void handleExit(ActionEvent actionEvent) {
            Stage stage = (Stage) gridPaneMovies.getScene().getWindow();
            stage.close();
    }
}

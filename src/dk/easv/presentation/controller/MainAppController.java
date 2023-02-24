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

import java.net.URL;
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
        showMovies();
    }
    @FXML
    private void handleTopMoviesSeen(ActionEvent actionEvent) {
        amountMovies = 0;
        gridPaneMovies.getChildren().clear();
        currentMovieView = model.getObsTopMovieSeen();
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
        if (amountMovies - decreaseAmount < 0) { // -1 Because else we wont see the last movie of the list
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
}

package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import resources.GetResources;

import java.io.IOException;

/**
 * Klasa odpowiadająca za zbudowanie odpowiednio działjacego okna startowego aplikacji. Jest równocześnie kontrolerem dla jego widoku.
 * Posiada kilka metod inicjujących prawidłowe załadowanie elementów, w tym metodę pozwalająca załadować w prawidłowy sposób klasę
 * Plansza będącą główną klasą obługującą logikę gry oraz będącą kontrolerem dla widoku planszy jako głównego widoku rozgrywki.
 * @author Arkadiusz Zimny
 * @version 1.0
 */

public class GraStart {

    /**
     * Składnik określający jeden z kontrolerów widoku javaFX (obiekt stworzonej przez nas klasy Controller, będącej kontrolerem widoku
     * dla okna startowego aplikacji).
     */
    private Controller controller;

    @FXML
    private ToggleButton musicBTN;
    @FXML
    private Button playBTN;
    @FXML
    private Button exitBTN;
    @FXML
    private ImageView logo;

    @FXML
    void actionMusic() {
        if(GetResources.getMediaPlayer()==null) {
            GetResources.loadMusic();
        }

        if(GetResources.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
            musicBTN.setGraphic(GetResources.loadImage("musicoff.png"));
            GetResources.getMediaPlayer().pause();
        }
        else {
            musicBTN.setGraphic(GetResources.loadImage("musicon.png"));
            GetResources.getMediaPlayer().play();
        }
    }

    @FXML
    protected void initialize() {
        logo.setImage(GetResources.loadJustImage("logo.png"));
        musicBTN.setGraphic(GetResources.loadImage("musicon.png"));
        musicBTN.setFocusTraversable(false);
        playBTN.setGraphic(GetResources.loadImage("play1.png"));
        playBTN.setFocusTraversable(false);
        exitBTN.setGraphic(GetResources.loadImage("exit1.png"));
        exitBTN.setFocusTraversable(false);
    }
    /**
     * Metoda wywoływana podczas naciśnięcia przycisku rozpoczynającego grę w oknie startowym aplikacji.
     * Laduje ona prawidłowy widok, ustawia prawidłowy kontroler oraz rozkład.
     */
    @FXML
    public void startGame() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("plansza.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Plansza plansza = loader.getController();
        plansza.setController(controller);
        controller.setScreen(pane);
    }

    /**
     * Metoda wywoływana przez przycisk kończący działanie aplikacji. Powoduje zamkniecie okna aplikacji i zakończenie procesu.
     */
    @FXML
    public void exit() {
        Platform.exit();
    }

    /**
     * Metoda wywoływana przez przycisk sygnalizujący chęć otwarcia listy z zapisem ostatnich wyników rozgrywki.
     * Realizuje załadowanie widoku oraz jego kontrolera.
     */
    @FXML
    public void openRanking() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("rankingView.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rankingView plansza = loader.getController();
        plansza.setController(controller);
        controller.setScreen(pane);
    }

    /**
     * Metoda gwarantująca ustawienie prawidłowego kontrolera dla widoku.
     * @param controller Parametr przekazywany do metody na podsawie, którego ustawiany jest kontroler.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
}

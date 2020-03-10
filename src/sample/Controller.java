package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import resources.GetResources;

import java.io.IOException;

/**
 * Klasa, dzięki której możliwe jest płynne przechodzenie miedzy oknami i ich przeładowywanie. Klasa realizuję wyświetlanie prawidłowych
 * widoków po naciśnieciu przycisku rozpoczęcia rozgrywki lub powrotu do ekranu startowego.
 *
 * @author Arkadiusz Zimny
 * @version 1.0
 */
public class Controller {

    @FXML
    private Pane mainStackPane;

    /**
     * Metoda pozwalająca na prawidłowe załadowanie okna startowego aplikacji.
     */
    @FXML
    public void initialize() {
        if(GetResources.getMediaPlayer()==null) {
            GetResources.loadMusic();
            GetResources.getMediaPlayer().play();
        }

        loadGraStart();
    }

    /**
     * Metoda pozwalająca na prawidłowe załadowanie okna aplikacji (wyczysczenie go oraz dodanie odpowiednich struktur)
     * @param pane Struktura dodawana do obiektu mainStackPane
     */
    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

    /**
     * Metoda, dzięki której możliwe jest ujrzenie menu startowego aplikacji.
     */
    public void loadGraStart() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("graStart.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraStart graStart = loader.getController();
        graStart.setController(this);
        setScreen(pane);
    }
}

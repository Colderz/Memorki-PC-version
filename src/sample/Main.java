package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Klasa umożliwiająca aplikacji prawidłowy start, inicjalizująca ramkę okna, wymiary oraz dobór sceny. Wedle założeń modelu jako
 * jedyna klasa posiada metodę main, w której usukteczniamy start całej aplikacji.
 *
 * @author Arkadiusz Zimny
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Metoda nadpisana na rzecz klasy po której dziedziczy, to jest Application. Realizuje obłsugę incjalizacji okna aplikacji oraz
     * prawidłowe jego wyświetlenie.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("sample.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Memorki - Zagraj i przetestuj swoją pamięć!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metoda main, w której uskuteczniamy start całej aplikacji.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

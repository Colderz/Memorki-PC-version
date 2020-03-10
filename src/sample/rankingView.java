package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import resources.GetResources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Klasa odpowiadająca za budowę elementu aplikacji jakim jest lista ostatnich wyników zapisywanych w przypadku sukcesu rozgrywki.
 * Jednocześnie jest kontrolerem dla widoku/sceny z listą przedstawiającą te wyniki.
 * @author Arkadiusz Zimny
 * @version 1.0
 */
public class rankingView {

    /**
     * Składnik będący kontrolką dla obiektu TextArea, utworzony w celu operacji zapisu w nim odczytanych z pliku danych.
     */
    @FXML
    private TextArea tekstarena;
    /**
     * Zmienna pośrednicząca przy przepisywaniu danych do aplikacji.
     */
    String dane;

    /**
     * Metoda domyślnie wywoływana przy załadowywaniu widoku listy ostatnich wyników, więc w momencie naciśnięcia przycisku
     * umieszczonego w widoku startowym aplikacji. Metoda realizuje odczyt danych z pliku zważając na występujące przy tym błędy.
     */
    @FXML
    protected void initialize() {
        tekstarena.setEditable(false);
        try {
            FileReader czyt = new FileReader(GetResources.loadScores());
            BufferedReader czytb = new BufferedReader(czyt);
            dane = null;
            while((dane = czytb.readLine()) != null) {
                tekstarena.appendText(dane+"\r\n");
            }
            czytb.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zapewniająca prawidłowy sposób powrotu do ekranu głównego.
     */
    @FXML
    public void backToStart() {
        controller.loadGraStart();
    }

    /**
     * Składnik określający jeden z kontrolerów widoku javaFX (obiekt stworzonej przez nas klasy Controller, będącej kontrolerem widoku
     * dla okna startowego aplikacji).
     */
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

}

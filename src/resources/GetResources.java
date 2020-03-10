package resources;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

/**
 * Klasa zawierająca wszystkie odniesienia do zasobów aplikacji. Są to między innymi pliki graficzne i muzyka.
 * W klasie zawarte są metody pozwalające na uzyskanie prawidłowej ścieżki odczytu plików.
 *
 * @author Arkadiusz Zimny
 * @version 1.0
 */
public class GetResources {
    public static MediaPlayer mediaPlayer;

    public static ImageView loadImage(String imageName) {
        String sciezka = GetResources.class.getResource(imageName).toString();
        Image image = new Image(sciezka);
        return new ImageView(image);
    }

    public static Image loadJustImage(String imageName) {
        String sciezka = GetResources.class.getResource(imageName).toString();
        Image image = new Image(sciezka);
        return image;
    }

    public static File loadScores() {
        File file = new File("file.txt");
        try {
            if(file.createNewFile()){
                System.out.println("file.txt File Created in Project root directory");
            }else System.out.println("File file.txt already exists in the project root directory");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void loadMusic() {
        String path = GetResources.class.getResource("music.mp3").toString();
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

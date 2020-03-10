package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.util.Duration;
import resources.GetResources;

import java.io.*;
import java.util.*;

/**
 * Klasa odpowiadająca za zbudowanie odopowiednio działającej planszy, inicjująca właściwy widok gry oraz
 * mechanizm dobrych i złych dopasowań kart. Klasa zawiera metody dostępne i użyte wyłącznie przez nią, korzysta
 * z zaimportowanych bibliotek javafx (kolekcje, zdarzenia i scena), java.io, oraz util.
 * Najważniejsza i najbardziej rozbudowana klasa w aplikacji, jest również kontrolerem dla widoku planszy, co zostało
 * zainicjowane w pliku typu fxml dla tego widoku.
 *
 * @author Arkadiusz Zimny
 * @version 1.0
 */

public class Plansza {

    /**
     * Parametr oznaczony jako FXML-owy, kontrolka dla elementu utworzonego w pliku typu fxml dla tego widoku.
     * Jeden z przycisków tworzących planszę.
     */
    @FXML ToggleButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12,
    button13, button14, button15, button16, button17, button18, button19, button20, button21, button22, button23, button24, button25,
    button26, button27, button28;

    /**
     * Parametr oznaczony jako FXML-owy, kontrolka dla elementu utworzonego w pliku typu fxml dla tego widoku.
     * Etykieta z łańcuchem znaków określającym liczbę punktów.
     */
    @FXML
    private Label levelNumber;
    /**
     * Parametr oznaczony jako FXML-owy, kontrolka dla elementu utworzonego w pliku typu fxml dla tego widoku.
     * Lista rozwijana, niezbędna do wyboru pozomiu trudności.
     */
    @FXML
    private ComboBox<String> comboBoxPlansza;
    /**
     * Parametr oznaczony jako FXML-owy, kontrolka dla elementu utworzonego w pliku typu fxml dla tego widoku.
     * Etykieta z łańcuchem znaków nazywającą przeznaczenie elementu poniżej.
     */
    @FXML
    private Label wybierzLabel;

    @FXML
    private ProgressBar czas;

    Timeline timeline;

    /**
     *Lista zawierająca poziomy
     */
    private ObservableList<String> levelLista;
    /**
     * Tablica zapisująca losowo wygenerowany przydział grafik do przycisków.
     */
    int[] wygenerowane = new int[28];

    int attempt = 0;

    int tematyka = 2;

    /**
     * Lista zawierająca przyciski, potrzebna do odwoływania sie do nich po indeksie.
     */
    private List<ToggleButton> buttonsList= new ArrayList<>();
    /**
     * Zmienna przekazywana dwóm metodom (initialGame oraz mechanism), kopia zmiennej poziom.
     */
    String poziom2="Hard";
    /**
     * Zmienna określająca stan punktacji.
     */
    int punkty = 0;
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika1 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika2 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika3 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika4 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika5 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika6 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika7 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika8 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika9 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika10 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika11 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika12 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika13 = new int[2];
    /**
     * Tablica dla jednej z czternastu grafik, określająca, które przyciski są dla niej przypisane.
     */
    int[] grafika14 = new int[2];
    int n = 0;
    /**
     * Zmienna używane w metodzie chooseCard wywoływanej podczas naciśniecia któregoś z przysków na planszy.
     */
    ToggleButton pierwszy, drugi;
    /**
     * Zmienna używane w metodzie chooseCard wywoływanej podczas naciśniecia któregoś z przysków na planszy.
     */
    int numerPierwszy, numerDrugi, ikona, ikona2;
    int match = 0;
    int licznik = 0;
    /**
     * Obiekt typu Alert używany w kilku miejscach, potrzebny do wyświetlenia odpowiednich komunikatów z
     * rezultatem gry na końcu rozgrywki.
     */
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    /**
     * Przycisk umieszczony na alercie pozwalający na powrót do ekranu głównego.
     */
    ButtonType buttonTypeOne = new ButtonType("Powrót do ekranu głównego");

    int startCondition;
    List<String> czytList = new ArrayList<>();

    /**
     * Metoda wywoławana tylko raz podczas każdgo nowego rozpoczęcia gry. Założeniem metody jest przypisanie
     * każdemu z przycisków losowo dobranej grafiki (jednej z czternastu). Każda grafika musi być użyta dokładnie
     * dwa razy. Każda grafika posiada swoją małą, dwuelementową tablicę, która określa, który z przycisków należy
     * do danej grafiki. Tablice te użyte są później do sprawdzania dopasowań podczas każdego odsłonięcia dwóch kart.
     * Metoda wygenerujPary generuje 28 liczb, za każdym razem w losowej kolejności.
     */
    private void wygenerujPary() {
        int m = 0;
        int size = 27;
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for(int i = 0;i<=size;i++) {
            list.add(i);
        }
        Random rand = new Random();
        while(list.size()>0) {
            int index = rand.nextInt(list.size());
            int taLiczba = list.remove(index);
            wygenerowane[m]=taLiczba;
            m++;
        }
        grafika1[0] = wygenerowane[0];
        grafika1[1] = wygenerowane[1];
        grafika2[0] = wygenerowane[2];
        grafika2[1] = wygenerowane[3];
        grafika3[0] = wygenerowane[4];
        grafika3[1] = wygenerowane[5];
        grafika4[0] = wygenerowane[6];
        grafika4[1] = wygenerowane[7];
        grafika5[0] = wygenerowane[8];
        grafika5[1] = wygenerowane[9];
        grafika6[0] = wygenerowane[10];
        grafika6[1] = wygenerowane[11];
        grafika7[0] = wygenerowane[12];
        grafika7[1] = wygenerowane[13];
        grafika8[0] = wygenerowane[14];
        grafika8[1] = wygenerowane[15];
        grafika9[0] = wygenerowane[16];
        grafika9[1] = wygenerowane[17];
        grafika10[0] = wygenerowane[18];
        grafika10[1] = wygenerowane[19];
        grafika11[0] = wygenerowane[20];
        grafika11[1] = wygenerowane[21];
        grafika12[0] = wygenerowane[22];
        grafika12[1] = wygenerowane[23];
        grafika13[0] = wygenerowane[24];
        grafika13[1] = wygenerowane[25];
        grafika14[0] = wygenerowane[26];
        grafika14[1] = wygenerowane[27];
    }

    /**
     * Metoda pozwalająca w prawidłowy sposób rozpocząć rozgrywkę. Gwaratnuje odpowiednią kolejność, odblokowanie i zablokowanie
     * elementów. Metoda nie pobiera argumentów, ale operuje na zmiennych dostępnych w klasie. Określa działanie listy rozwijanej
     * oraz przypisuje odpowiednią wartość zmiennej punkty (widoczna nad planszą).
     */
    @FXML
    private void initialGame() {
        pokazAlertTematyczny();
        for(int i=0;i<28;i++) {
            buttonsList.get(i).setDisable(false);
        }
        String poziom = comboBoxPlansza.getValue();
        poziom2 = poziom;
        if(poziom.contains("Hard")) {
            levelNumber.setText("35");
            punkty = 35;
        }
        if(poziom.contains("Medium")) {
            levelNumber.setText("50");
            punkty = 50;
        }
        if(poziom.contains("Easy")) {
            levelNumber.setText("65");
            punkty = 65;
        }
        if(poziom.contains("Użytkownika")) {
            TextInputDialog dialog = new TextInputDialog("Liczba punktów");
            dialog.setTitle("Poziom użytkownika");
            dialog.setHeaderText("Stwórz własny poziom trudności");
            dialog.setContentText("Podaj liczbę punktów, od której chcesz zacząć:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                levelNumber.setText(result.get());
                punkty = Integer.parseInt(result.get());
            }
        }
    }

    public void pokazAlert() {
        alert.setTitle("KONIEC GRY");
        alert.setHeaderText(null);
        alert.setContentText("PRZEGRANA! SPRÓBUJ JESZCZE RAZ!");
        levelNumber.setText("0");
        alert.getButtonTypes().setAll(buttonTypeOne);
        Optional<ButtonType> result = alert.showAndWait();
        timeline.stop();
        if (result.get() == buttonTypeOne){
            controller.loadGraStart();
        }
    }

    public void pokazAlertTematyczny() {
        List<String> choices = new ArrayList<>();
        choices.add("ŻYCIE");
        choices.add("WARZYWA I OWOCE");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("ŻYCIE", choices);
        dialog.setTitle("WYBIERZ TEMATYKE KART");
        dialog.setHeaderText("Wybierz jedną z dostępych tematyk kart");
        dialog.setContentText("Twój wybór:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if(result.get().equals("ŻYCIE")) {
                tematyka = 1;
            } else {
                tematyka = 2;
            }
        }
    }

    /**
     * Metoda domyślnie wywoływana podczas otworzenia okna rozpoczynającego grę (podczas uruchomienia klasy Plansza.java).
     * Inicjalizuje ona wszystkie wcześniej określone obiekty, zmienne itp. (Te, którym wartość nie została nadana wcześniej).
     * Wypełnia listę przycisków wg. indeksów oraz listę grafik. Metoda wywołuję również wcześniej określoną metodę generującą
     * losowo pary przycisków z taką samą grafiką (wygenerujPary()). Naturalnie nie przekazując jej żadnych argumentów.
     */
    @FXML
    protected void initialize() {
        startCondition = 0;
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Hard");
        arrayList.add("Medium");
        arrayList.add("Easy");
        arrayList.add("Użytkownika");
        IntegerProperty seconds = new SimpleIntegerProperty();
        czas.progressProperty().bind(seconds.divide(130.0));
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
                new KeyFrame(Duration.seconds(130), e-> {
                    alert.setTitle("KONIEC CZASU!");
                    alert.setHeaderText(null);
                    alert.setContentText("CZAS MINAL! SPROBUJ JESZCZE RAZ!");
                    alert.show();
                    attempt++;
                    System.out.println("Time over");
                }, new KeyValue(seconds, 130))
        );
        levelLista = FXCollections.observableArrayList(arrayList);
        comboBoxPlansza.getItems().addAll(levelLista);
        levelNumber.setText("");
        buttonsList.add(0, button1);
        buttonsList.add(1,button2);
        buttonsList.add(2,button3);
        buttonsList.add(3,button4);
        buttonsList.add(4,button5);
        buttonsList.add(5,button6);
        buttonsList.add(6,button7);
        buttonsList.add(7,button8);
        buttonsList.add(8,button9);
        buttonsList.add(9,button10);
        buttonsList.add(10,button11);
        buttonsList.add(11,button12);
        buttonsList.add(12,button13);
        buttonsList.add(13,button14);
        buttonsList.add(14,button15);
        buttonsList.add(15,button16);
        buttonsList.add(16,button17);
        buttonsList.add(17,button18);
        buttonsList.add(18,button19);
        buttonsList.add(19,button20);
        buttonsList.add(20,button21);
        buttonsList.add(21,button22);
        buttonsList.add(22,button23);
        buttonsList.add(23,button24);
        buttonsList.add(24,button25);
        buttonsList.add(25,button26);
        buttonsList.add(26,button27);
        buttonsList.add(27,button28);
        for(int r=0;r<buttonsList.size();r++) {
            buttonsList.get(r).setFocusTraversable(false);
        }
        for(int i=0;i<28;i++) {
            buttonsList.get(i).setCursor(Cursor.HAND);
            buttonsList.get(i).setDisable(true);
            buttonsList.get(i).setVisible(true);
            buttonsList.get(i).setGraphic(GetResources.loadImage("backside1.png"));
        }
        wygenerujPary();
    }

    /**
     * Jedna z najważniejszych metod w aplikacji, gwarantująca prawidłowe działanie obłsugi przycisków oraz niezbędna do
     * prawidłowego ciągu rozgrywki. W bloku try-catch sprawdza ona, na którym z przycisków została wywołana akcja. Na podstawie
     * wcześniej przyznanego losowo doboru grafik do przycisków zapewnia ona prawidłowe wyświetlenie grafiki i nadanie stanu przyciskowi.
     * Napotykając błąd metoda stara się niezmieniając danych wejściowych wywołanych i określonych przez wcześniej wywołane metody,
     * uruchomić rozgrywkę ponownie, robiąc to niezauważalnie dla użytkownika. Przez to rozgrywka kontynuowana jest w prawidłowy sposób.
     * @param eve Parametr, dzięki któremu możemy określić, na rzecz którego przycisku wykonujemy akcję.
     */
    private void game(ActionEvent eve){
        try {
            int a = 0;
            for(int f=0;f<28;f++) {
                if (eve.getSource() == buttonsList.get(wygenerowane[f])) {
                    if(f<2) a = 1;
                    if(f>1&&f<4) a = 2;
                    if(f>3&&f<6) a = 3;
                    if(f>5&&f<8) a = 4;
                    if(f>7&&f<10) a = 5;
                    if(f>9&&f<12) a = 6;
                    if(f>11&&f<14) a = 7;
                    if(f>13&&f<16) a = 8;
                    if(f>15&&f<18) a = 9;
                    if(f>17&&f<20) a = 10;
                    if(f>19&&f<22) a = 11;
                    if(f>21&&f<24) a = 12;
                    if(f>23&&f<26) a = 13;
                    if(f>25&&f<28) a = 14;
                    if (buttonsList.get(wygenerowane[f]).isSelected()) {
                        if(tematyka==1) {
                            buttonsList.get(wygenerowane[f]).setGraphic(GetResources.loadImage("ikona"+a+"a.png"));
                        }
                        if(tematyka==2) {
                            buttonsList.get(wygenerowane[f]).setGraphic(GetResources.loadImage("ikona"+a+"aa.png"));
                        }
                        buttonsList.get(wygenerowane[f]).setDisable(true);
                        buttonsList.get(wygenerowane[f]).setStyle("-fx-opacity: 1.0;");
                    }
                }
            }
        } catch (Exception e) {
            initialize();
            if(poziom2.contains("Hard")) {
                levelNumber.setText("35");
                punkty = 35;
            }
            if(poziom2.contains("Medium")) {
                levelNumber.setText("50");
                punkty = 50;
            }
            if(poziom2.contains("Easy")) {
                levelNumber.setText("65");
                punkty = 65;
            }
            if(poziom2.contains("Użytkownika")) {
                TextInputDialog dialog = new TextInputDialog("Liczba punktów");
                dialog.setTitle("Poziom użytkownika");
                dialog.setHeaderText("Stwórz własny poziom trudności");
                dialog.setContentText("Podaj liczbę punktów, od której chcesz zacząć:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    levelNumber.setText(result.get());
                    punkty = Integer.parseInt(result.get());
                }
            }
            comboBoxPlansza.setDisable(true);
            wybierzLabel.setDisable(true);
            for(int i=0;i<28;i++) {
                buttonsList.get(i).setDisable(false);
            }
        }
    }

    /**
     * Metoda użyteczna przy określanie z którego przycisku została wywołana akcja oraz która grafika jest jemu przypisana.
     * Celem metody jest wyłącznie określenie prawidłowego id przycisku, w celu wybrania odpowiedniego z listy przycisków.
     * @param id Parametr będący łańcuchem znaków, na podstawie przekazanego tego paramteru, metoda określa id przycisku.
     * @return Zwracany jest parametr liczba typu int, będący właśnie indeksem przycisku w liście tych elementów.
     */
    private int sprawdzPoId(String id) {
        int liczba = 0;
        for(int i=1; i<29; i++) {
            if(id.contains("button"+i+"")) {
                liczba = (i-1);
            }
        }
        return liczba;
    }

    /**
     * Metoda wywoływana przy każdym naciśnięciu przycisków planszy. Za pomocą parametru określa, z którego przycisku pochodzi zdarzenie,
     * a następnie operuje na nim. Określając kolejność naciskania przycisków i tworzenia z nich par, sprawdza zgodność ich grafik.
     * W metodzie tej znalazło się również miejsce dla obsługi systemu punktowego gry, mechanizm zakończenia gry z sukcesem lub porażką oraz
     * wywołanie wcześniej określonej metody game. W przypadku zwycięstwa w metodzie zagwarantowana obsługa zapisu wyniku do listy rankingowej.
     * @param eve Parametr, dzięki któremu możemy określić, na rzecz którego przycisku wykonujemy akcję.
     */
    @FXML
    private void chooseCard(ActionEvent eve) {
        if(startCondition==0) {
            timeline.play();
            startCondition++;
        }
        if(attempt>0) {
            n = -1;
            timeline.stop();
            for(int i=0;i<28;i++) {
                buttonsList.get(i).setSelected(false);
                buttonsList.get(i).setGraphic(null);
                buttonsList.get(i).setText("");
                pierwszy.setDisable(false);
                drugi.setDisable(false);
            }
            initialize();
            if(poziom2.contains("Hard")) {
                levelNumber.setText("35");
                punkty = 35;
            }
            if(poziom2.contains("Medium")) {
                levelNumber.setText("50");
                punkty = 50;
            }
            if(poziom2.contains("Easy")) {
                levelNumber.setText("65");
                punkty = 65;
            }
            if(poziom2.contains("Użytkownika")) {
                TextInputDialog dialog = new TextInputDialog("Liczba punktów");
                dialog.setTitle("Poziom użytkownika");
                dialog.setHeaderText("Stwórz własny poziom trudności");
                dialog.setContentText("Podaj liczbę punktów, od której chcesz zacząć:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    levelNumber.setText(result.get());
                    punkty = Integer.parseInt(result.get());
                }
            }
            comboBoxPlansza.setDisable(true);
            wybierzLabel.setDisable(true);
            for(int i=0;i<28;i++) {
                buttonsList.get(i).setDisable(false);
            }
            timeline.play();
            attempt = 0;
        }
        comboBoxPlansza.setDisable(true);
        wybierzLabel.setDisable(true);
        ToggleButton wybrany = (ToggleButton) eve.getSource();
        if(n==0) {
            pierwszy = wybrany;
            numerPierwszy = sprawdzPoId(pierwszy.getId());
            int t=1;
            int z=0;
            for(int y=0;y<28;y++) {
                if(z==2) z=0;
                if(numerPierwszy==wygenerowane[y]) ikona=t;
                z++;
                if(z==2) t++;
            }
        }
        if(n==1) {
            drugi = wybrany;
            numerDrugi = sprawdzPoId(drugi.getId());
            int t=1;
            int z=0;
            for(int y=0;y<28;y++) {
                if(z==2) z=0;
                if(numerDrugi==wygenerowane[y]) ikona2=t;
                z++;
                if(z==2) t++;
            }
        }
        if(n==2) {
            n=-1;
            for(int i=0;i<28;i++) {
                buttonsList.get(i).setSelected(false);
                buttonsList.get(i).setGraphic(null);
                buttonsList.get(i).setText("");
                buttonsList.get(i).setGraphic(GetResources.loadImage("backside1.png"));
                pierwszy.setDisable(false);
                drugi.setDisable(false);
            }
            if(ikona==ikona2) {
                pierwszy.setVisible(false);
                drugi.setVisible(false);
                punkty = punkty + 4;
                match++;
            }
            if(ikona!=ikona2) {
                punkty = punkty-2;
            }
        }
        game(eve);
        n++;
        levelNumber.setText(""+punkty+"");
        if(match>=13) {
            licznik++;
        }
        if(licznik==3) {
            timeline.stop();
            pierwszy.setVisible(false);
            pierwszy.setGraphic(null);
            pierwszy.setSelected(false);
            drugi.setVisible(false);
            drugi.setGraphic(null);
            drugi.setSelected(false);
            punkty = punkty + 4;
            levelNumber.setText(punkty+"");
            alert.setTitle("KONIEC GRY");
            alert.setHeaderText(null);
            alert.setContentText("WYGRANA! TWÓJ WYNIK: "+punkty+"!");
            alert.getButtonTypes().setAll(buttonTypeOne);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                controller.loadGraStart();
            }
            try {

                Scanner scanner = new Scanner(GetResources.loadScores());
                while(scanner.hasNextLine()) {
                    czytList.add(scanner.nextLine());
                }
                String str = punkty+"";
                String poziomr ="";
                FileWriter fileWriter = new FileWriter(GetResources.loadScores());
                //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(GetResources.loadScores().getAbsolutePath(), true)));
                if(poziom2.contains("Hard")) {
                    poziomr = "Hard";
                }
                if(poziom2.contains("Medium")) {
                    poziomr = "Medium";
                }
                if(poziom2.contains("Easy")) {
                    poziomr = "Easy";
                }
                if(poziom2.contains("Użytkownika")) {
                    poziomr = "Użytkownika";
                }
                czytList.add(str + "  - POZIOM: "+poziomr);
                for(int i = 0; i<czytList.size(); i++) {
                    fileWriter.write(czytList.get(i));
                    if(i!= czytList.size()-1){
                        fileWriter.append("\r\n");
                    }
                    //fileWriter.append("\r\n");
                }
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(punkty<=0) {
            timeline.stop();
            pokazAlert();
        }
    }

    /**
     * Składnik określający jeden z kontrolerów widoku javaFX (obiekt stworzonej przez nas klasy Controller, będącej kontrolerem widoku
     * dla okna startowego aplikacji).
     */
    private Controller controller;

    /**
     * Metoda zapewniająca prawidłowy sposób powrotu do ekranu głównego.
     */
    @FXML
    public void backToMenu() {
        timeline.stop();
        controller.loadGraStart();
    }

    /**
     * Metoda gwarantująca ustawienie prawidłowego kontrolera dla widoku.
     * @param controller Parametr przekazywany do metody na podsawie, którego ustawiany jest kontroler.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

}

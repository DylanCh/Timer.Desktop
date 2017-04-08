package desktopTimer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;
import java.io.*;
//import org.apache.log4j.*;

public class Controller implements Initializable{
//    public Controller() {
//        logger=Logger.getLogger(Controller.class.getName());
//    }
//
//    private static Controller controller = new Controller();
//
//    public static Controller getInstance(){return  new Controller();}

    MediaView mediaView;
    @FXML private VBox vBox;
    @FXML private Button pauseButton;
    @FXML private TextField timeBox;
    @FXML private Label displayTime; // TODO: display set time
    @FXML private FlowPane statusBar;
    @FXML private Hyperlink inputTips;

    Timer timer;

    public void start(long setTime) {

        timer = new Timer();

        TimerTask timerTask = null;

        createMediaView();
        if (mediaView!=null) {
            vBox.getChildren().add(mediaView);

            timerTask = new TimerTask() {
                public void run() {
                    mediaView.getMediaPlayer().seek(Duration.ZERO);
                    mediaView.getMediaPlayer().play();
                }
            };
            timer.schedule(timerTask, setTime);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusBar.getChildren().add(new DigitalClock());
        timeBox.setPromptText("1 hour = 60 minutes");
        inputTips.setTooltip(
            new Tooltip("For example: enter \"75\" if you want a timer that for 1 hour and 15 minutes")
        );
    }

    private void createMediaView(){
        try {
            Media media = new Media(new File(
                    "res/Alarm.wav").toURI().toString());
            mediaView = new MediaView(new MediaPlayer(media));
        }
        catch (MediaException me){
            me.printStackTrace();
            new Alert(Alert.AlertType.INFORMATION,"Ringtone format is wrong", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void startBtn_click(ActionEvent event){
        try {
            this.start(
                    Long.parseLong(timeBox.getText()) * 1000 * 60
            ); // 1 minute equals 1000 milliseconds times 60 seconds

            displayTime.setText("Timer activated: "+timeBox.getText()+" minutes.");

            if (pauseButton.isDisable())
                pauseButton.setDisable(false);
        }
        catch (NumberFormatException ne){
            new Alert (Alert.AlertType.ERROR,ne.getMessage(),ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void pause_click(ActionEvent event) {
        if (mediaView!=null)
            mediaView.getMediaPlayer().pause();

        if (timer!=null) {

            displayTime.setText("Timer has been disbanded");

            timer.cancel();
            timer.purge();
        }

        pauseButton.setDisable(true);
    }
}

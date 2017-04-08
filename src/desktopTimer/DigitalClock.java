package desktopTimer;

import javafx.animation.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.util.*;

import java.util.Calendar;

public class DigitalClock extends Label {
    public DigitalClock() {
        bindToTime();
    }

    // the digital clock updates once a second.
    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                String hourString = StringUtilities.pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
                                String minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
                                String secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
                                String ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
                                setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}


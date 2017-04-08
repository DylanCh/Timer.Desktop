package desktopTimer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("desktopTimer.xml"));
        //Controller controller = (Controller) fxmlLoader.getController();

        primaryStage.setTitle("Timer");

        primaryStage.setOnCloseRequest(event -> Platform.exit());

        Scene scene = new Scene(root,300,275);
        scene.getStylesheets().add("/desktopTimer/Style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}

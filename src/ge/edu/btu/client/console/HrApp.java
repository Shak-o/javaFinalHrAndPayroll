package ge.edu.btu.client.console;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;


public class HrApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = FXMLLoader.load(getClass().getResource("Hr.fxml"));
        stage.setTitle("HR application");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        launch(args);

    }
}


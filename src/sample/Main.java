package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 400, 80));
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
//   C:/Users/leona/OneDrive/Dokumente/Klassenarbeiten1Hj1718-v1.pdf
//   C:/Users/leona/Neuer Ordner/
//   C:\Users\leona\AndroidStudioProjects\mpsPlans\app\src\main\assets

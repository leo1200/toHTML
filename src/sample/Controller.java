package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField textfield;

    @FXML
    TextField textfield2;

    public void starte(){
        String ordner = textfield2.getText().replace("\\", "/");
        if(!ordner.endsWith("/")){
            ordner+="/";
        }
        try {
            getData.main(textfield.getText().replace("\\", "/"),ordner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Ferien f = new Ferien(ordner);
    }
}

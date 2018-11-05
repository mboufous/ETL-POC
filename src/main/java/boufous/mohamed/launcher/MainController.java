package boufous.mohamed.launcher;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    static String filename = "TEST.xlsx";

    static AnchorPane columns;

    public void onLoad() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        columns = (AnchorPane)loader.getNamespace().get("Content");


    }
}

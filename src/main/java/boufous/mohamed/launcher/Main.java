package boufous.mohamed.launcher;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class Main extends Application {

    TransformationResult transformationResult;
    StringBuilder myQuery = new StringBuilder("SELECT * ");
    StringBuilder fields = new StringBuilder("SELECT");

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World!");
        SplitPane root = new SplitPane();

        root.setDividerPositions(.25,.8);
        AnchorPane columnsPane = new AnchorPane();
        AnchorPane filterPane = new AnchorPane();
        AnchorPane confPane = new AnchorPane();

        Label lbl1 = new Label("Columns");
        Label lbl2 = new Label("Filter");
        Label lbl3 = new Label("Configuration");


        filterPane.getChildren().add(lbl2);
        confPane.getChildren().add(lbl3);

        VBox columnsVbox = new VBox(8);
        columnsPane.getChildren().add(columnsVbox);
        VBox.setMargin(lbl1, new Insets(10,0,0,0));
        columnsVbox.getChildren().add(lbl1);

        //Extractor extractor = new XLSXExtractor(getClass().getClassLoader().getResource("TEST.xlsx").getPath());
        Extractor extractor = new CSVExtractor(getClass().getClassLoader().getResource("Korea_Policy_File.csv").getPath());
        Dataset<Row> d = extractor.getTransformedData(myQuery.toString());
        String[] h = d.schema().fieldNames();

        for (int i=0; i<h.length; i++) {
            CheckBox c = new CheckBox(h[i]);
            c.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue == true){
                        fields.append(" " + c.getText() + ",");
                    }else{
                        int j = fields.indexOf(c.getText());
                        if (j != -1) {
                            fields.delete(j, j + c.getText().length()+1);
                        }
                    }
                }

            });
            VBox.setMargin(c, new Insets(0, 0, 0, 8));
            columnsVbox.getChildren().add(c);
        }

        HBox buttonsPan = new HBox();
        Button closeBtn = new Button("Close");
        Button saveBtn= new Button("Save");

        saveBtn.setOnAction(event -> {
            transformationResult = new TransformationResult(extractor.sourceFilePath, h, extractor.getTransformedData(fields.toString()));
            transformationResult.getData().printSchema();
//            transformationResult.setData(extractor.getTransformedData("Select Policy_ID "));
//            transformationResult.getData().printSchema();
        });

        closeBtn.setOnAction(event -> {
            dbSingleton.instance.getDb().close();
            primaryStage.close();
        });

        HBox.setMargin(closeBtn, new Insets(15,10,10,10));
        HBox.setMargin(saveBtn, new Insets(15,10,10,10));
        buttonsPan.getChildren().addAll(closeBtn, saveBtn);
        buttonsPan.setAlignment(Pos.BOTTOM_RIGHT);

        filterPane.getChildren().add(buttonsPan);

        root.getItems().addAll(columnsPane, filterPane, confPane);

        primaryStage.setScene(new Scene(root, 1024, 720));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

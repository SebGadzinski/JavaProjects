import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.*;

public class AlarmApp extends Application{
    public void start(Stage primaryStage) {
        Pane BackgroundPane = new Pane();
        BackgroundPane.setStyle("-fx-background-color: white;");
        //Under Background Pane

        //Under First Pane
        Pane fPane = new Pane();
        fPane.setStyle("-fx-background-color: white; -fx-border-color:gray; -fx-padding: 4 4;");
        fPane.setPrefSize(200, 90);
        fPane.relocate(10,10);
        //First pane label
        Label label1 = new Label("Remaining Time");
        label1.relocate(10, 0);
        label1.setStyle("-fx-background-color: white; -fx-translate-y: -8; -fx-translate-x: 10;");
        //First pane label 00:00:00
        Label label4 = new Label("00:00:00");
        label4.relocate(12, 10);
        label4.setStyle("-fx-font: 48 SystemFont; -fx-base: green; -fx-text-fill: green;");
        fPane.getChildren().addAll(label1, label4);

        //Under Second Pane
        Pane sPane = new Pane();
        sPane.setStyle("-fx-background-color: white; -fx-border-color:gray; -fx-padding: 4 4;");
        sPane.setPrefSize(200, 40);
        sPane.relocate(220, 10);
        //Second pane Label
        Label label2 = new Label("Current Time");
        label2.relocate(0, 0);
        label2.setStyle("-fx-background-color: white; -fx-translate-y: -8; -fx-translate-x: 10;");
        //Second pane Text field
        TextField text1 = new TextField();
        text1.setText("01:54:16 PM");
        text1.setAlignment(Pos.CENTER_RIGHT);
        text1.relocate(5, 10);
        text1.setPrefSize(190, 25);
        sPane.getChildren().addAll(label2, text1);

        //Under Third Pane
        Pane tPane = new Pane();
        tPane.setStyle("-fx-background-color: white; -fx-border-color:gray; -fx-padding: 4 4;");
        tPane.setPrefSize(200, 40);
        tPane.relocate(220, 60);
        //Third pane label
        Label label3 = new Label("Alarm Time");
        label3.relocate(0, 0);
        label3.setStyle("-fx-background-color: white; -fx-translate-y: -8; -fx-translate-x: 10;");
        //Third pane TextField
        TextField text2 = new TextField();
        text2.setText("10:30:00 AM");
        text2.setAlignment(Pos.CENTER_RIGHT);
        text2.relocate(5, 10);
        text2.setPrefSize(190, 25);
        tPane.getChildren().addAll(label3, text2);

        ObservableList<String> options = FXCollections.observableArrayList("Weekday", "Saturday", "Sunday");
        ComboBox combo1 = new ComboBox(options);
        combo1.setPromptText("Select Alarm");
        combo1.relocate(10,110);
        combo1.setPrefSize(410, 30 );

        Button b1 = new Button("New Alarm");
        b1.relocate(10 ,150);
        b1.setPrefSize(100, 30);
        Button b2 = new Button("Edit");
        b2.relocate(120 ,150);
        b2.setPrefSize(80, 30);
        Button b3 = new Button("Delete");
        b3.relocate(210 ,150);
        b3.setPrefSize(80, 30);

        ToggleGroup  group1 = new ToggleGroup();
        RadioButton radio1 = new RadioButton();
        radio1.setText("ON");
        radio1.relocate(310,150);
        radio1.setPrefSize(50,30);
        radio1.setToggleGroup(group1);
        RadioButton radio2 = new RadioButton();
        radio2.setText("OFF");
        radio2.relocate(360,150);
        radio2.setPrefSize(50,30);
        radio2.setToggleGroup(group1);

        BackgroundPane.getChildren().addAll(fPane, sPane, tPane, combo1, b1, b2, b3, radio1, radio2);

        primaryStage.setTitle("Alarm App");
        primaryStage.setScene(new Scene(BackgroundPane, 430, 190));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


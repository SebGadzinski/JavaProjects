import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.util.Random;

public class SliderPuzzleGame extends Application { //Doesn't matter if its private just did it for no reason
    public int thecounter;
    private Button[][] buttons;
    private Label thumbnail;
    private ListView<String> namesList;
    private Button start;
    private Label time;
    private TextField stopwatch;
    Timeline updateTimer;
    public int minute = 0;
    public int seconds = 0;
    Random rNum = new Random();
    private String[][] buttonNames = new String[4][4];
    String[][] buttonCheck = new String[4][4];

    public void start(Stage primaryStage) {
        Pane aPane = new Pane();
        buttons = new Button[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) { //Initiate the buttons and there qualities
                buttons[row][col] = new Button();
                buttons[row][col].relocate(10 + 187 * col + col, 10 + 187 * row + row);
                buttons[row][col].setPrefSize(187, 187);
                buttons[row][col].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("BLANK.png"))));
                buttons[row][col].setPadding(new Insets(0, 0, 0, 0));
                aPane.getChildren().add(buttons[row][col]);
            }
        }
        //Initiate the thumbnail
        thumbnail = new Label();
        thumbnail.relocate(771, 10);
        thumbnail.setPrefSize(187, 187);
        thumbnail.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Lego_Thumbnail.png"))));
        thumbnail.setDisable(false);
        // Initiate the list of names of the puzzle lists
        namesList = new ListView<String>();
        String[] names = {"Lego", "Numbers", "Pets", "Scenery"};
        namesList.setItems(FXCollections.observableArrayList(names));
        namesList.relocate(771, 207);
        namesList.setPrefSize(187, 138);
        //initiate the start button
        start = new Button("Start");
        start.setPrefSize(187, 30);
        start.relocate(771, 355);
        start.setStyle("-fx-base: DARKGREEN; -fx-text-fill: WHITE;");
        //initiate the time
        time = new Label("Time:");
        time.relocate(771, 395);
        //initate the stopwatch
        stopwatch = new TextField("0:00");
        stopwatch.setPrefSize(122, 30);
        stopwatch.relocate(836, 395);
        //add all of the elements to the pane
        aPane.getChildren().addAll(thumbnail, namesList, start, time, stopwatch);
        //set the stage
        primaryStage.setTitle("Slide Puzzle Game");
        primaryStage.setScene(new Scene(aPane, 958, 771));
        primaryStage.setResizable(false); // preference
        primaryStage.show();
        //get the timer to work
        updateTimer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (seconds < 60) {
                    seconds++;
                } else {
                    seconds = 0;
                    minute++;
                }

                stopwatch.setText(String.format("%d:%02d", minute, seconds));

            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);


        namesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                thumbnail.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(namesList.getSelectionModel().getSelectedItem() + "_Thumbnail.png"))));
            }
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if the buttons text is "Stop" do all of the requirements
                if (start.getText() == "Stop") {
                    updateTimer.stop();
                    ;
                    for (int row = 0; row < 4; row++) {
                        for (int col = 0; col < 4; col++) {
                            buttons[row][col].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("BLANK.png"))));
                        }
                    }
                    thumbnail.setDisable(false);
                    start.setText("Start");
                    start.setStyle("-fx-base: DARKGREEN; -fx-text-fill: WHITE;");
                    stopwatch.setText("0:00");
                    minute = 0;
                    seconds = 0;
                    // if the button anything is selected in the names list
                } else if (!(namesList.getSelectionModel().getSelectedItem() == null)) {
                    updateTimer.play();
                    thumbnail.setDisable(true);
                    start.setText("Stop");
                    start.setStyle("-fx-base: DARKRED; -fx-text-fill: WHITE;");
                    for (int row = 0; row < 4; row++) {
                        // get all of the files names and add them to the both list one for checking and one for changing as the user plays the game
                        for (int col = 0; col < 4; col++) {
                            buttonNames[row][col] = namesList.getSelectionModel().getSelectedItem() + "_" + String.format("%d%d", row, col) + ".png";
                            buttonCheck[row][col] = namesList.getSelectionModel().getSelectedItem() + "_" + String.format("%d%d", row, col) + ".png";
                            buttons[row][col].setDisable(false);
                        }
                    }
                    int c = rNum.nextInt(4);
                    int d = rNum.nextInt(4);
                    buttonNames[c][d] = "BLANK.png";
                    buttonCheck[c][d] = "BLANK.png";
                    String match = "";
                    //Randomize by picking random numbers and using them to switch teh strings around
                    for (int i = 0; i < 1; i++) {
                        for (int y = 0; y < 4; y++) {
                            for (int z = 0; z < 4; z++) {
                                int r = rNum.nextInt(4);
                                int o = rNum.nextInt(4);
                                if (r != y && z != o) {
                                    match = buttonNames[y][z];
                                    buttonNames[y][z] = buttonNames[r][o];
                                    buttonNames[r][o] = match;
                                }
                            }
                        }
                    }
                    // display the images to each button properly
                    for (int row = 0; row < 4; row++) {
                        for (int col = 0; col < 4; col++) {
                            buttons[row][col].setGraphic(new ImageView(new Image(getClass().getResourceAsStream(buttonNames[row][col]))));
                            buttons[row][col].setOnAction(new EventHandler<ActionEvent>() {
                                // This is the single event handler for all of the buttons
                                public void handle(ActionEvent event) {
                                    // Find the row and column of the pressed button
                                    for (int row = 0; row < 4; row++) {
                                        for (int col = 0; col < 4; col++) {
                                            if (event.getSource() == buttons[row][col]) {
                                                Swap(row, col);
                                            }
                                        }
                                    }
                                    // check to see if the puzzle is completed
                                    for (int row = 0; row < 4; row++) {
                                        for (int col = 0; col < 4; col++) {
//                                            System.out.print(buttonCheck[row][col]);
//                                            System.out.print("  =  "  );
//                                            System.out.print(buttonNames[row][col]);
                                            if (buttonCheck[row][col].equals(buttonNames[row][col])) {
                                                thecounter++;
                                            }
                                        }
                                    }
                                    // if the game is completed do the following
                                    if(thecounter == 16){
                                        updateTimer.stop();
                                        start.setText("Start");
                                        start.setStyle("-fx-base: DARKGREEN; -fx-text-fill: WHITE;");
                                        thumbnail.setDisable(false);
                                        for (int row = 0; row < 4; row++) {
                                            for (int col = 0; col < 4; col++) {
                                                buttons[row][col].setDisable(true);
                                            }
                                        }
                                    }
                                    else
                                        thecounter =0;
                                }
                            });
                        }
                    }
                }
            }
        });
    }
    //swap method checks to see if the black tile is around MAKING SURE IT STAYS WITHIN THE BOUNDRIES
    public void Swap(int row, int col) {
        for (int i = 0; i < 1; i++) {
            if (row == 0) {
                if (col == 3) {
                    if (buttonNames[row + 1][col] == "BLANK.png") {
                        buttonNames[row + 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col - 1] == "BLANK.png") {
                        buttonNames[row][col - 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }

                } else if (col > 0 && col < 3) {
                    if (buttonNames[row + 1][col] == "BLANK.png") {
                        buttonNames[row + 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col + 1] == "BLANK.png") {
                        buttonNames[row][col + 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col - 1] == "BLANK.png") {
                        buttonNames[row][col - 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }

                } else if (col == 0) {
                    if (buttonNames[row + 1][col] == "BLANK.png") {
                        buttonNames[row + 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col + 1] == "BLANK.png") {
                        buttonNames[row][col + 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }
                }
            } else if (row > 0 && row < 3) {
                if (col == 3) {
                    if (buttonNames[row + 1][col] == "BLANK.png") {
                        buttonNames[row + 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col - 1] == "BLANK.png") {
                        buttonNames[row][col - 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row - 1][col] == "BLANK.png") {
                        buttonNames[row - 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }
                } else if (col > 0 && col < 3) {
                    if (buttonNames[row + 1][col] == "BLANK.png") {
                        buttonNames[row + 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col + 1] == "BLANK.png") {
                        buttonNames[row][col + 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col - 1] == "BLANK.png") {
                        buttonNames[row][col - 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row - 1][col] == "BLANK.png") {
                        buttonNames[row - 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }
                } else if (col == 0) {
                    if (buttonNames[row + 1][col] == "BLANK.png") {
                        buttonNames[row + 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col + 1] == "BLANK.png") {
                        buttonNames[row][col + 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row - 1][col] == "BLANK.png") {
                        buttonNames[row - 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }
                }
            } else if (row == 3) {
                if (col == 3) {
                    if (buttonNames[row][col - 1] == "BLANK.png") {
                        buttonNames[row][col - 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row - 1][col] == "BLANK.png") {
                        buttonNames[row - 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }
                } else if (col > 0 && col < 3) {
                    if (buttonNames[row][col - 1] == "BLANK.png") {
                        buttonNames[row][col - 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row - 1][col] == "BLANK.png") {
                        buttonNames[row - 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row][col + 1] == "BLANK.png") {
                        buttonNames[row][col + 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }
                } else if (col == 0) {
                    if (buttonNames[row][col + 1] == "BLANK.png") {
                        buttonNames[row][col + 1] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    } else if (buttonNames[row - 1][col] == "BLANK.png") {
                        buttonNames[row - 1][col] = buttonNames[row][col];
                        buttonNames[row][col] = "BLANK.png";
                        break;
                    }
                }

            }

        }
        //update the GUI
        for (int ro = 0; ro < 4; ro++) {
            for (int co = 0; co < 4; co++) {
                buttons[ro][co].setGraphic(new ImageView(new Image(getClass().getResourceAsStream(buttonNames[ro][co]))));
            }

        }
    }
}

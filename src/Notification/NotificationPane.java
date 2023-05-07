package Notification;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.LessonModel;

public class NotificationPane extends AnchorPane {

    private Label lblWord,
            lblSynonym,
            lblMeaning,
            lblPhonetic,
            lblExamples;

    private LessonModel lessonModel;

    public NotificationPane(LessonModel lessonModel) {
        super();
        this.setPrefSize(420, 165);
        this.setStyle(""
                + "-fx-background-color: #2a2e37;"
                + "-fx-background-radius: 8px;"
                + "");
        this.lessonModel = lessonModel;
        initComponents();
        setData();
        addComponentsToPane();
    }

    private void initComponents() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((VBox) getPane().getParent()).getChildren().remove(getPane());
            }
        });

        this.lblWord = new Label();
        this.lblWord.setFont(new Font(16d));
        this.lblWord.setStyle("-fx-font-weight: bold");
        this.lblWord.setTextFill(Color.WHITE);
        this.lblWord.setAlignment(Pos.CENTER_LEFT);
        this.lblWord.setPrefWidth(190d);

        this.lblMeaning = new Label();
        this.lblMeaning.setFont(new Font(16d));
        this.lblMeaning.setStyle("-fx-font-weight: bold");
        this.lblMeaning.setPrefWidth(190d);
        this.lblMeaning.setTextFill(Color.WHITE);
        this.lblMeaning.setAlignment(Pos.CENTER_RIGHT);

        this.lblSynonym = new Label();
        this.lblSynonym.setFont(new Font(14d));
        this.lblSynonym.setTextFill(Color.WHITE);
        this.lblSynonym.setWrapText(true);
        this.lblSynonym.setPrefSize(390d, 42d);


        this.lblPhonetic = new Label();
        this.lblPhonetic.setTextFill(Color.WHITE);

        this.lblExamples = new Label();
        this.lblExamples.setTextFill(Color.WHITE);
        this.lblExamples.setWrapText(true);
        this.lblExamples.setPrefSize(390d, 84d);
    }

    private void setData() {
        this.lblWord.setText(this.lessonModel.getWord());
        this.lblMeaning.setText(this.lessonModel.getMeaning());
        this.lblSynonym.setText(this.lessonModel.getSynonym());
        this.lblPhonetic.setText(this.lessonModel.getPhonetic());
        this.lblExamples.setText(this.lessonModel.getExamples());
    }

    private void addComponentsToPane() {
        this.lblWord.setLayoutX(10d);
        this.lblWord.setLayoutY(10d);

        this.lblMeaning.setLayoutX(195d);
        this.lblMeaning.setLayoutY(10d);

        this.lblSynonym.setLayoutX(10d);
        this.lblSynonym.setLayoutY(35d);

        this.lblExamples.setLayoutX(10d);
        this.lblExamples.setLayoutY(80d);

        this.getChildren().addAll(
                this.lblWord,
                this.lblMeaning,
                this.lblSynonym,
                this.lblExamples
        );
    }

    public AnchorPane getPane() {
        return this;
    }
}


package Notification;

import java.awt.Toolkit;
import javafx.scene.layout.VBox;
import models.LessonModel;

public class Notification {

    private static Notification notification;
    private VBox mainPane;
    private final double spacing = 4d;

    private Notification() {
        initializing();
    }

    public static Notification getInstanc() {
        if (notification == null) {
            notification = new Notification();
        }
        return notification;
    }

    private void initializing() {
        this.mainPane = new VBox(this.spacing);
        this.mainPane.setStyle("-fx-background-color: transparent;");
        this.mainPane.setPrefSize(400, Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    }

    public VBox getMainPane() {
        return this.mainPane;
    }

    public void sendNotification(LessonModel lessonModel) {
        this.mainPane.getChildren().add(
                new NotificationPane(lessonModel)
        );
    }

}
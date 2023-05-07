import Notification.Notification;
import Service.Service;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Tools;

import java.awt.*;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(Notification.getInstanc().getMainPane());

        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(screenSize.getWidth()
                - Notification.getInstanc().getMainPane().getPrefWidth()
                - 10
        );
        stage.setY(10);
        stage.show();

        Service.getInstance().start();
    }

    public static void main(String[] args) {
        System.out.println("No param sended :(");
        System.out.println("\t-t120\t\tfor time in second; nothing 120s");
        System.out.println("\t-l1\t\tfor lesson 1");

        for(String arg: args){
            if(arg.startsWith("-t")){
                Tools.getInstance().TIME_IN_SECOND = Integer.parseInt(arg.toLowerCase(Locale.ROOT).trim().replace("-t", ""));
            }else  if(arg.startsWith("-l")){
                Service.getInstance().lessonNumber = Integer.parseInt(arg.toLowerCase(Locale.ROOT).trim().replace("-l", ""));
            }
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Service.getInstance().stop();
            }
        });

        launch(args);
    }
}

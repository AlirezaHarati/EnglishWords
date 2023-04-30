import Notification.Notification;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.FileModel;
import models.LessonModel;
import models.ReadingModel;
import org.json.simple.JSONArray;
import utils.Files;
import utils.JSON;

import javax.management.relation.Relation;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(Notification.getInstanc().getMainPane());

        FileModel fileModel = new Main().getFileModels("./data/504/Lesson1.json");
        Notification.getInstanc().sendNotification(fileModel.getLessonModels()[0]);
        Notification.getInstanc().sendNotification(fileModel.getLessonModels()[1]);
        Notification.getInstanc().sendNotification(fileModel.getLessonModels()[2]);
        Notification.getInstanc().sendNotification(fileModel.getLessonModels()[4]);

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
    }

    public static void main(String[] args) {
        launch(args);
//        FileModel fileModel = new Main().getFileModels("./data/504/Lesson1.json");
//
//        System.out.println(fileModel.getLessonModels()[0].getWord());
//        System.out.println(fileModel.getLessonModels()[0].getSynonym());
//        System.out.println("-------------------------");
//        System.out.println(fileModel.getReadingModel().getTitle());
//        System.out.println(fileModel.getReadingModel().getContent());
    }

    public FileModel getFileModels(String path) {
        String data = Files.getInstance().readFromFile(path);
        if (data.isEmpty()) {
            return null;
        }
        JSON json = new JSON();
        Map<String, Object> map = json.parse(data);
        if (map == null) {
            return null;
        }
        //-- Init Lesson Model
        ArrayList<Map<String, Object>> dataArray = json.getArrayData((JSONArray) map.get("data"));
        LessonModel[] lessonModels = new LessonModel[dataArray.toArray().length];
        JSONArray examples = null;
        for (int i = 0; i < lessonModels.length; i++) {
            lessonModels[i] = new LessonModel();

            lessonModels[i].setWord(String.valueOf(dataArray.get(i).get("word")));
            lessonModels[i].setSynonym(String.valueOf(dataArray.get(i).get("synonym")));
            lessonModels[i].setMeaning(String.valueOf(dataArray.get(i).get("meaning")));
            lessonModels[i].setPhonetic(String.valueOf(dataArray.get(i).get("phonetic")));

            examples = (JSONArray) dataArray.get(0).get("examples");
            if (examples != null && examples.size() > 0) {
                for (int j = 0; j < examples.size(); j++) {
                    lessonModels[i].addExample(String.valueOf(examples.get(j)));
                }
            }
        }
        //-- Init Reading Model
        Map<String, Object> readingMap = json.parse(map.get("reading").toString());
        ReadingModel readingModel = new ReadingModel();
        readingModel.setTitle(String.valueOf(readingMap.get("title")));
        readingModel.setContent(String.valueOf(readingMap.get("content")));

        //-- Init File Model
        FileModel fileModel = new FileModel();
        fileModel.setLessonModels(lessonModels);
        fileModel.setReadingModel(readingModel);

        return fileModel;
    }
}

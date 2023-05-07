package Service;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import models.FileModel;
import models.LessonModel;
import models.ReadingModel;
import org.json.simple.JSONArray;
import utils.Files;
import utils.JSON;
import utils.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Service {

    private static Service service;
    private Timeline timeline;
    public boolean isServiceRunning = false;
    public boolean isServiceStarted = false;

    public int lessonNumber = 1;
    private int counter = 0;
    private FileModel fileModel;

    private Service() {
    }

    public static Service getInstance() {
        if (service == null) {
            service = new Service();
        }
        return service;
    }

    public void start() {
        isServiceStarted = true;
        if (timeline != null) {
            return;
        }
        int timeInterval = Tools.getInstance().TIME_IN_SECOND * 1000;

        if (this.fileModel == null) {
            fileModel = getFileModels("./data/504/Lesson" + this.lessonNumber + ".json");
        }

        if (!isServiceRunning) {
            new ServiceHandler(fileModel.getLessonModels()[getCounter()]).start();
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(timeInterval), event -> {
            if (!isServiceRunning) {
                new ServiceHandler(fileModel.getLessonModels()[getCounter()]).start();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }

    public void stop() {
        isServiceStarted = false;
        timeline.stop();
        timeline = null;
    }

    private int getCounter() {
        if (this.counter == 11) {
            this.counter = 0;
            return this.counter;
        } else {
            return this.counter++;
        }
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
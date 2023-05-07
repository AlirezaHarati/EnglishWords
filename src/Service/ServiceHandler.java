package Service;

import Notification.Notification;
import javafx.application.Platform;
import models.LessonModel;

public class ServiceHandler extends Thread {

    public LessonModel lessonModel;

    public ServiceHandler(LessonModel lessonModel) {
        super();
        this.lessonModel = lessonModel;
    }

    @Override
    public void run() {
        startProc();
    }

    @Override
    public synchronized void start() {
        Service.getInstance().isServiceRunning = true;
        super.start();
    }

    @Override
    public void interrupt() {
        Service.getInstance().isServiceRunning = false;
        super.interrupt();
    }

    public void startProc() {
        if (this.lessonModel != null) {
            Platform.runLater(() -> {
                Notification.getInstanc().sendNotification(this.lessonModel);
            });
        }

        interrupt();
    }

}
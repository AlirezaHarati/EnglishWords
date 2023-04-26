package models;

public class FileModel {

    private LessonModel[] lessonModels = null;
    private ReadingModel readingModel = null;

    public LessonModel[] getLessonModels() {
        return lessonModels;
    }

    public void setLessonModels(LessonModel[] lessonModels) {
        this.lessonModels = lessonModels;
    }

    public ReadingModel getReadingModel() {
        return readingModel;
    }

    public void setReadingModel(ReadingModel readingModel) {
        this.readingModel = readingModel;
    }
}

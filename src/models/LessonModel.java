package models;

import java.util.ArrayList;

public class LessonModel {
    private String word, synonym, meaning, phonetic;
    private ArrayList<String> examples;

    public LessonModel() {
        this.word = "";
        this.meaning = "";
        this.synonym = "";
        this.phonetic = "";
        this.examples = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public void setExamples(ArrayList<String> examples) {
        this.examples = examples;
    }

    public void addExample(String example) {
        this.examples.add(example);
    }
}

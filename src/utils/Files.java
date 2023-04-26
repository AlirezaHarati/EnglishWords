package utils;

import enums.Consts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Files {

    private static Files files;
    public final String UTF8_BOM = "\uFEFF";

    public static Files getInstance() {
        if (files == null) {
            files = new Files();
        }
        return files;
    }

    public boolean createDirectory(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public boolean writeOnFile(String path, String content) {
        return writeOnFile(new File(path), content);
    }

    public boolean writeOnFile(String path, String content, boolean isAppend) {
        return writeOnFile(new File(path), content, isAppend);
    }

    public boolean writeOnFile(File file, String content) {
        return writeOnFile(file, content, false);
    }

    public boolean writeOnFile(File file, String content, boolean isAppend, String charset/*UTF8*/) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, isAppend), charset));
            out.write(content);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean writeOnFile(File file, String content, boolean isAppend) {
        return writeOnFile(file, content, isAppend, "UTF8");
    }

    public String readFromFile(String path) {
        return readFromFile(new File(path));
    }

    public String readFromFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                    "UTF8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(Consts.NEW_LINE.value());
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
            String data = removeUTF8BOM(stringBuilder.toString());
            return data.substring(0, data.length() - 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String removeUTF8BOM(String data) {
        if (data.startsWith(UTF8_BOM)) {
            data = data.substring(1);
        }
        return data;
    }
}

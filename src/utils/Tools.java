package utils;

public class Tools {

    private static Tools tools;
    public int TIME_IN_SECOND = 120;

    private Tools() {
    }

    public static Tools getInstance() {
        if (tools == null) {
            tools = new Tools();
        }
        return tools;
    }
}

package utils;

public class Tools {

    private static Tools tools;

    private Tools() {
    }

    public static Tools getInstance() {
        if (tools == null) {
            tools = new Tools();
        }
        return tools;
    }
}

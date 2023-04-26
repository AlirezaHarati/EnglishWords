package enums;


public enum Consts {
    NEW_LINE(System.lineSeparator());

    private final String value;

    private Consts(String val) {
        value = val;
    }

    public String value() {
        return this.value;
    }
}

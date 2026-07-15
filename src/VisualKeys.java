public enum VisualKeys {
    EMPTY("  "),
    CASTLE("\uD83C\uDFF0"),
    PERSON("\uD83E\uDDD9\u200D"),
    MONSTER("\uD83E\uDDDF\u200D"),
    BIG_MONSTER("\uD83D\uDC79");

    private final String image;

    VisualKeys(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}

import java.util.Locale;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int stepX;
    private final int stepY;

    Direction(int stepX, int stepY) {
        this.stepX = stepX;
        this.stepY = stepY;
    }

    public int getStepX() {
        return stepX;
    }

    public int getStepY() {
        return stepY;
    }

    public static Direction fromString(String value) {
        return switch (value.trim().toUpperCase(Locale.ROOT)) {
            case "ВВЕРХ" -> UP;
            case "ВНИЗ" -> DOWN;
            case "ВЛЕВО" -> LEFT;
            case "ВПРАВО" -> RIGHT;
            default -> null;
        };
    }
}

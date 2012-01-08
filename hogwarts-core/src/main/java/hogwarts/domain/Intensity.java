package hogwarts.domain;

public class Intensity {
    private final int value;

    private Intensity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public interface Modifier {
        Intensity modify(Intensity before);
    }

    private static final int RADIUS = 10;
    private static Intensity[] cache = new Intensity[] {//
    new Intensity(-10),//
            new Intensity(-9),//
            new Intensity(-8),//
            new Intensity(-7),//
            new Intensity(-6),//
            new Intensity(-5),//
            new Intensity(-4),//
            new Intensity(-3),//
            new Intensity(-2),//
            new Intensity(-1),//
            new Intensity(0),//
            new Intensity(1),//
            new Intensity(2),//
            new Intensity(3),//
            new Intensity(4),//
            new Intensity(5),//
            new Intensity(6),//
            new Intensity(7),//
            new Intensity(8),//
            new Intensity(9),//
            new Intensity(10) //
    };

    public static Intensity valueOf(int i) {
        int index = i+RADIUS;
        if (0 <= index && index < cache.length) {
            return cache[index];
        }
        return new Intensity(i);
    }
}

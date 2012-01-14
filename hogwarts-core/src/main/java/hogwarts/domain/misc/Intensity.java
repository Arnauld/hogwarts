package hogwarts.domain.misc;


public class Intensity {
    public enum Unit {
        Percent,
        Raw
    }
    private final int value;
    private final Unit unit;

    private Intensity(int value, Unit unit) {
        this.value = value;
        this.unit  = unit;
    }
    
    public Unit getUnit() {
        return unit;
    }
    public int getValue() {
        return value;
    }

    public interface Modifier {
        Intensity modify(Intensity before);
    }

    public static Intensity valueOf(int i, Unit unit) {
        return new Intensity(i, unit);
    }
}

package hogwarts.environment.effect;

import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.HasPosition;
import hogwarts.environment.Shape;

public class ShapeEffect {
    private final Shape shape;
    private final Environment environment;

    public ShapeEffect(Environment environment, Shape shape) {
        this.environment = environment;
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

    public void makeGlowing(HasPosition where, Color glow) {
        environment.log("A " + shape + " is glowing with a strange " + glow + " aura");
    }
}

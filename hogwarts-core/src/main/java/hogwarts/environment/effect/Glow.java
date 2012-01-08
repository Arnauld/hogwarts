package hogwarts.environment.effect;

import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.HasPosition;

public class Glow {
    private final Color color;
    private final Environment environment;

    public Glow(Environment environment, Color color) {
        this.environment = environment;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void makePulsating(HasPosition where) {
        environment.log("A " + color + " glow is pulsating at " + where.getPosition(environment));
    }
}

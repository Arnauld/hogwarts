package hogwarts.environment.effect;

import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.HasPosition;

public class Spark {
    private final Color color;
    private final Environment environment;

    public Spark(Environment environment, Color color) {
        this.environment = environment;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void makePulsating(HasPosition where) {
        environment.log("A " + color + " spark is pulsating at " + where.getPosition(environment));
    }
}

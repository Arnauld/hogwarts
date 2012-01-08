package hogwarts.environment.effect;

import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.HasPosition;
import hogwarts.environment.Position;

public class BeamOfLight {
    private final Color color;
    private final Environment environment;
    
    public BeamOfLight(Environment environment, Color color) {
        this.environment = environment;
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void makePulsating(HasPosition from, HasPosition to) {
        Position beg = from.getPosition(environment);
        Position end = to.getPosition(environment);
        environment.log("A bean of light " + color + " is pulsating from " + beg.getPositionLabel() + " to " + end.getPositionLabel());
    }
}

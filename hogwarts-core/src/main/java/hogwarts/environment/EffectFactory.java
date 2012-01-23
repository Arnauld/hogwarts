package hogwarts.environment;

import hogwarts.environment.effect.BeamOfLight;
import hogwarts.environment.effect.Glow;
import hogwarts.environment.effect.ShapeEffect;
import hogwarts.environment.effect.Spark;

public class EffectFactory {
    
    private Environment environment;
    public EffectFactory(Environment environment) {
        this.environment = environment;
    }

    public BeamOfLight createBeamOfLight(Color color) {
        return new BeamOfLight(environment, color);
    }

    public Glow createGlow(Color color) {
        return new Glow(environment, color);
    }

    public ShapeEffect createShape(hogwarts.environment.Shape shape) {
        return new ShapeEffect(environment, shape);
    }

    public Spark createSpark() {
        return new Spark(environment, Color.Gold);
    }

}

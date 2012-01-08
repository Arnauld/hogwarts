package hogwarts.domain;

import static hogwarts.util.Methods.asEffect;
import static hogwarts.util.Methods.asEffectF;
import hogwarts.environment.Color;
import hogwarts.util.HasBehavior;
import fj.Effect;
import fj.F;

public interface InteractiveObject extends Targetable {
    void explose();
    void bump();
    void levitate();
    void glow(Color color);
    void grab(HasBehavior anything);
    
    public static Effect<InteractiveObject> explose = asEffect(InteractiveObject.class, "explose");
    public static Effect<InteractiveObject> bump = asEffect(InteractiveObject.class, "bump");
    public static Effect<InteractiveObject> levitate = asEffect(InteractiveObject.class, "levitate");
    public static F<Color,Effect<InteractiveObject>> glow = asEffectF(InteractiveObject.class, "levitate", Color.class);
    public static F<HasBehavior,Effect<InteractiveObject>> grab = asEffectF(InteractiveObject.class, "grab", HasBehavior.class);

}

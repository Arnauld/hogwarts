package hogwarts.domain;

import static hogwarts.util.Methods.asEffect;
import fj.Effect;

public interface InanimateObject extends Targetable {
    void repair();
    
    public static Effect<InanimateObject> repair = asEffect(InanimateObject.class, "repair");
}

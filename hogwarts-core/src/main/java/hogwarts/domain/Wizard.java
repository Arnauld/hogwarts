package hogwarts.domain;

import hogwarts.domain.behavior.Caster;
import hogwarts.domain.behavior.HasWand;
import hogwarts.domain.behavior.LivingCreature;
import hogwarts.util.HasBehavior;

public interface Wizard extends Caster, LivingCreature, HasWand, HasBehavior {
    
}


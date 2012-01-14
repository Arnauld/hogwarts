package hogwarts.domain.behavior;

import hogwarts.domain.misc.Target;
import hogwarts.domain.spell.Incantation;
import hogwarts.domain.spell.Spell;
import hogwarts.util.HasBehavior;
import hogwarts.util.Parameter;

public interface Caster {

    public static final Parameter<Target> target = new Parameter<Target>("target", Target.class);
    
    <T extends Spell<T>> Incantation<T> incanteSpell(Spell<T> spell, Parameter<?>.Value... parameters);
    
    public static class F {
        public static Parameter<Target>.Value target(HasBehavior...targets) {
            return target.newValue(Target.F.targets(targets));
        }
    }
}

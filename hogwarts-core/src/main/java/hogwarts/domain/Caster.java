package hogwarts.domain;

import fj.F;
import hogwarts.environment.HasPosition;
import hogwarts.util.HasBehavior;
import hogwarts.util.HasId;
import hogwarts.util.Parameter;

public interface Caster extends HasId, HasPosition, HasBehavior {

    public static final Parameter<SpellTarget> targets = new Parameter<SpellTarget>("target", SpellTarget.class);
    
    public static final F<Targetable,Parameter<SpellTarget>.Value> target = new F<Targetable, Parameter<SpellTarget>.Value>() {
        @Override
        public Parameter<SpellTarget>.Value f(Targetable target) {
            return targets.newValue(SpellTarget.singleTarget.f(target));
        }
    };
    <T extends Spell<T>> Incantation<T> incanteSpell(Spell<T> spell, Parameter<?>.Value... parameters);
}

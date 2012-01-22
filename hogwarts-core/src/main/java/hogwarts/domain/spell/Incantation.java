package hogwarts.domain.spell;

import fj.data.Option;
import hogwarts.domain.misc.Target;
import hogwarts.environment.Environment;
import hogwarts.util.HasBehavior;

public class Incantation<T extends Spell<T>> {
    private final HasBehavior caster;
    private final Spell<T> spell;
    private final Option<Target> target;
    
    public Incantation(HasBehavior caster, Spell<T> spell, Option<Target> targetable) {
        this.caster = caster;
        this.spell = spell;
        this.target = targetable;
    }
    
    public HasBehavior getCaster() {
        return caster;
    }
    
    public Spell<T> getSpell() {
        return spell;
    }
    
    public Option<Target> getTarget() {
        return target;
    }
    
    public void performCast(Environment environment) {
        spell.performIncantation(this, environment);
    }
}

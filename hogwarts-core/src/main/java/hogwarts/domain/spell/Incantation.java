package hogwarts.domain.spell;

import fj.data.Option;
import hogwarts.environment.Environment;
import hogwarts.util.HasBehavior;

public class Incantation<T extends Spell<T>> {
    private final HasBehavior caster;
    private final Spell<T> spell;
    private final Option<HasBehavior> target;
    
    public Incantation(HasBehavior caster, Spell<T> spell, Option<HasBehavior> targetable) {
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
    
    public Option<HasBehavior> getTarget() {
        return target;
    }
    
    public void performCast(Environment environment) {
        spell.performIncantation(this, environment);
    }
}

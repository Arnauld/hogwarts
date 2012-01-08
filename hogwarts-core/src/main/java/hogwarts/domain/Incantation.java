package hogwarts.domain;

import hogwarts.environment.Environment;
import fj.data.Option;

public class Incantation<T extends Spell<T>> {
    private final Caster caster;
    private final Spell<T> spell;
    private final Option<Targetable> target;
    
    public Incantation(Caster caster, Spell<T> spell, Option<Targetable> targetable) {
        this.caster = caster;
        this.spell = spell;
        this.target = targetable;
    }
    
    public Caster getCaster() {
        return caster;
    }
    
    public Spell<T> getSpell() {
        return spell;
    }
    
    public Option<Targetable> getTarget() {
        return target;
    }
    
    public void performCast(Environment environment) {
        spell.createAnimation(this).animate(environment);
        spell.process(this, environment);
    }
}

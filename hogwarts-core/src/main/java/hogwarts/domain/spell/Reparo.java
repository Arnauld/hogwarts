package hogwarts.domain.spell;

import static hogwarts.domain.InanimateObject.repair;
import fj.Effect;
import hogwarts.domain.AbstractSpell;
import hogwarts.domain.InanimateObject;
import hogwarts.domain.Incantation;
import hogwarts.domain.SpellLevel;
import hogwarts.domain.Targetable;
import hogwarts.environment.Environment;

/**
 *  Reparo is a spell used to seamlessly mend broken objects. 
 *  In most cases, a sufficiently cast Mending Charm can 
 *  completely fix any broken object. 
 *
 */
public class Reparo extends AbstractSpell<Reparo> {
    
    public Reparo() {
        super(SpellLevel.Beginner);
    }
    
    public Reparo(SpellLevel level) {
        super(level);
    }
    
    @Override
    public Reparo specializeForLevel(SpellLevel level) {
        return new Reparo(level);
    }
    
    @Override
    public void process(Incantation<Reparo> incantation, Environment environment) {
        incantation.getTarget().foreach(getEffectOnTarget());
    }
    
    protected Effect<Targetable> getEffectOnTarget() {
        return new Effect<Targetable> () {
            @Override
            public void e(Targetable t) {
                t.strikedBySpell(Reparo.this);
                t.as(InanimateObject.class).foreach(repair);
            }
        };
    }

    public boolean isTargetable() {
        return true;
    }
}

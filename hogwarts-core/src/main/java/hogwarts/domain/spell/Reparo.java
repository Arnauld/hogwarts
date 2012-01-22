package hogwarts.domain.spell;

import static hogwarts.domain.spell.CanBeStrikedBySpell.F.strikedBySpell;
import static hogwarts.domain.spell.Spells.defaultIntensityForLevel;
import static hogwarts.domain.behavior.CanBeRepaired.F.repair;
import fj.Effect;
import hogwarts.domain.behavior.CanBeRepaired;
import hogwarts.domain.misc.Intensity;
import hogwarts.environment.Environment;
import hogwarts.util.HasBehavior;

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
    public void performIncantation(Incantation<Reparo> incantation, Environment environment) {
        Intensity intensity = defaultIntensityForLevel(getLevel());
        applyOnTarget(incantation, getEffectOnTarget(intensity));
    }
    
    protected Effect<HasBehavior> getEffectOnTarget(final Intensity intensity) {
        return new Effect<HasBehavior> () {
            @Override
            public void e(HasBehavior t) {
                t.as(CanBeStrikedBySpell.class).foreach(strikedBySpell(Reparo.this));
                t.as(CanBeRepaired.class).foreach(repair(intensity));
            }
        };
    }

    public boolean isTargetable() {
        return true;
    }
}

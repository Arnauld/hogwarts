package hogwarts.domain.spell;

import static hogwarts.domain.spell.CanBeStrikedBySpell.F.strikedBySpell;
import fj.Effect;
import hogwarts.domain.capability.Fear;
import hogwarts.domain.capability.Fear.Aware;
import hogwarts.domain.misc.Intensity;
import hogwarts.environment.Environment;
import hogwarts.environment.Shape;
import hogwarts.util.HasBehavior;
import hogwarts.util.Parameter;

/**
 *  The Patronus Charm is a charm that evokes a partially-tangible, 
 *  positive energy force known as a Patronus (pl. Patronuses). 
 *  
 *  Patronuses are also called spirit guardians though this may 
 *  only refer to corporeal Patronuses. It is primarily designed 
 *  for defence against otherwise unbeatable Dark creatures like 
 *  Dementors and Lethifolds, though there are other uses.
 *
 */
public class ExpectoPatronum extends AbstractSpell<ExpectoPatronum> {
    
    public static final Parameter<Shape> Shape = Parameter.create("patronus-shape", Shape.class);
    
    public ExpectoPatronum() {
        super(SpellLevel.Beginner);
    }
    
    public ExpectoPatronum(SpellLevel level) {
        super(level);
    }

    @Override
    public void performIncantation(Incantation<ExpectoPatronum> incantation, Environment environment) {
        applyOnTarget(incantation, getEffectOnTarget(incantation));
    }
    
    protected Effect<HasBehavior> getEffectOnTarget(final Incantation<ExpectoPatronum> incantation) {
        return new Effect<HasBehavior> () {
            @Override
            public void e(HasBehavior t) {
                t.as(CanBeStrikedBySpell.class).foreach(strikedBySpell(ExpectoPatronum.this));
                Intensity amount = getFearReductionFor(incantation.getSpell().getLevel());
                HasBehavior caster = incantation.getCaster();
                caster.as(Fear.Aware.class).foreach(changeFearThrough(t, amount));
            }
        };
    }
    
    protected Effect<Fear.Aware> changeFearThrough(final HasBehavior sourceOfFear, final Intensity amount) {
        return new Effect<Fear.Aware> () {
            @Override
            public void e(Aware a) {
                sourceOfFear.as(Fear.CauseOf.class).foreach(Fear.changeFearOnF(a, amount));
            }
        };
    }
    
    protected Intensity getFearReductionFor(SpellLevel level) {
        switch(level) {
            case Beginner: return Intensity.valueOf(0, Intensity.Unit.Raw);
            case Novice:   return Intensity.valueOf(-1, Intensity.Unit.Raw);
            case Expert:   return Intensity.valueOf(-5, Intensity.Unit.Raw);
            case Master:   return Intensity.valueOf(-100, Intensity.Unit.Percent);
        }
        return null;
    }

    @Override
    public boolean isTargetable() {
        return true;
    }

}

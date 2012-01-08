package hogwarts.domain.spell;

import fj.Effect;
import hogwarts.domain.AbstractSpell;
import hogwarts.domain.Caster;
import hogwarts.domain.HasPatronus;
import hogwarts.domain.Incantation;
import hogwarts.domain.Intensity;
import hogwarts.domain.SpellLevel;
import hogwarts.domain.Targetable;
import hogwarts.domain.capability.Fear;
import hogwarts.domain.capability.Fear.Aware;
import hogwarts.environment.Animation;
import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.Shape;
import hogwarts.environment.effect.ShapeEffect;
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
    public void process(Incantation<ExpectoPatronum> incantation, Environment environment) {
        incantation.getTarget().foreach(getEffectOnTarget(incantation));
    }
    
    protected Effect<Targetable> getEffectOnTarget(final Incantation<ExpectoPatronum> incantation) {
        return new Effect<Targetable> () {
            @Override
            public void e(Targetable t) {
                t.strikedBySpell(ExpectoPatronum.this);
                Intensity amount = getFearReductionFor(incantation.getSpell().getLevel());
                Caster caster = incantation.getCaster();
                caster.as(Fear.Aware.class).foreach(changeFearThrough(t, amount));
            }
        };
    }
    
    protected Effect<Fear.Aware> changeFearThrough(final Targetable t, final Intensity amount) {
        return new Effect<Fear.Aware> () {
            @Override
            public void e(Aware a) {
                t.as(Fear.CauseOf.class).foreach(Fear.changeFearOnF(a, amount));
            }
        };
    }
    
    protected Intensity getFearReductionFor(SpellLevel level) {
        switch(level) {
            case Beginner: return Intensity.valueOf(0);
            case Novice:   return Intensity.valueOf(-1);
            case Expert:   return Intensity.valueOf(-5);
            case Master:   return Intensity.valueOf(-10);
        }
        return null;
    }

    @Override
    public Animation createAnimation(final Incantation<ExpectoPatronum> incantation) {
        return new Animation() {
            public void animate(Environment environment) {
                Caster caster = incantation.getCaster();
                incantation.getTarget().foreach(getAnimationEffect(caster, environment));
            }
        };
    }
    
    protected Effect<Targetable> getAnimationEffect(final Caster caster, final Environment environment) {
        return new Effect<Targetable> () {
            @Override
            public void e(final Targetable target) {
                Effect<HasPatronus> effect = HasPatronus.applyEffectOnPatronus.f(new Effect<Shape> () {
                    @Override
                    public void e(hogwarts.environment.Shape patronus) {
                        ShapeEffect shapeEffect = environment.getEffectFactory().createShape(patronus);
                        shapeEffect.makeGlowing(caster, Color.WhiteTransluent);
                    }
                });
                caster.as(HasPatronus.class).foreach(effect);
            }
        };
    }
    
    @Override
    public boolean isTargetable() {
        return true;
    }

}

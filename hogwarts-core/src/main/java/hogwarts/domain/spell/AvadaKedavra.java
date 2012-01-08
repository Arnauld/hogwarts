package hogwarts.domain.spell;

import static hogwarts.domain.LivingCreature.kill;
import fj.Effect;
import hogwarts.domain.AbstractSpell;
import hogwarts.domain.Caster;
import hogwarts.domain.Incantation;
import hogwarts.domain.LivingCreature;
import hogwarts.domain.SpellLevel;
import hogwarts.domain.Targetable;
import hogwarts.environment.Animation;
import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.effect.BeamOfLight;

/**
 *  The Killing Curse (also known as the Avada Kedavra Curse) is a 
 *  spell that causes instantaneous death and is one of the three 
 *  Unforgivable Curses.
 */
public class AvadaKedavra extends AbstractSpell<AvadaKedavra> {
    
    public AvadaKedavra() {
        super(SpellLevel.Master);
    }

    @Override
    public void process(Incantation<AvadaKedavra> incantation, Environment environment) {
        incantation.getTarget().foreach(getEffectOnTarget());
    }
    
    protected Effect<Targetable> getEffectOnTarget() {
        return new Effect<Targetable> () {
            @Override
            public void e(Targetable t) {
                t.strikedBySpell(AvadaKedavra.this);
                t.as(LivingCreature.class).foreach(kill);
            }
        };
    }
    
    @Override
    public Animation createAnimation(final Incantation<AvadaKedavra> incantation) {
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
            public void e(Targetable target) {
                BeamOfLight beamOfLight = environment.getEffectFactory().createBeamOfLight(Color.Green);
                beamOfLight.makePulsating(caster, target);
            }
        };
    }
    
    @Override
    public boolean isTargetable() {
        return true;
    }
}

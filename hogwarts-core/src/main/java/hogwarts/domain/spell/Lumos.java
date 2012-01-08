package hogwarts.domain.spell;

import static hogwarts.domain.InteractiveObject.glow;
import fj.Effect;
import hogwarts.domain.AbstractSpell;
import hogwarts.domain.Caster;
import hogwarts.domain.HasWand;
import hogwarts.domain.Incantation;
import hogwarts.domain.InteractiveObject;
import hogwarts.domain.SpellLevel;
import hogwarts.domain.Wand;
import hogwarts.environment.Animation;
import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.effect.Glow;

/**
 *  The Wand-Lighting Charm (Lumos) is a charm that causes 
 *  the tip of the caster's wand to emit a warm glow.
 *
 */
public class Lumos extends AbstractSpell<Lumos> {
    
    public Lumos() {
        super(SpellLevel.Beginner);
    }
    
    public Lumos(SpellLevel level) {
        super(level);
    }
    
    @Override
    public void process(Incantation<Lumos> incantation, Environment environment) {
        Effect<HasWand> effect = HasWand.applyEffectOnWand.f(getEffectOnWand());
        incantation.getCaster().as(HasWand.class).foreach(effect);
    }

    protected Effect<Wand> getEffectOnWand() {
        return new Effect<Wand> () {
            @Override
            public void e(Wand t) {
                t.strikedBySpell(Lumos.this);
                t.as(InteractiveObject.class).foreach(glow.f(Color.Light));
            }
        };
    }
    
    public Animation createAnimation(final Incantation<Lumos> incantation) {
        return new Animation() {
            public void animate(Environment environment) {
                Caster caster = incantation.getCaster();
                Effect<HasWand> effect = HasWand.applyEffectOnWand.f(getAnimationEffect(environment));
                caster.as(HasWand.class).foreach(effect);
            }
        };
    }
    
    protected Effect<Wand> getAnimationEffect(final Environment environment) {
        return new Effect<Wand> () {
            @Override
            public void e(Wand t) {
                Glow glow = environment.getEffectFactory().createGlow(Color.Light);
                glow.makePulsating(t);
            }
        };
    }
    
    @Override
    public boolean isTargetable() {
        return false;
    }
}

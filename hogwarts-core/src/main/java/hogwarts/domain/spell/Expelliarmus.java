package hogwarts.domain.spell;

import static hogwarts.domain.InteractiveObject.bump;
import fj.Effect;
import hogwarts.domain.AbstractSpell;
import hogwarts.domain.Caster;
import hogwarts.domain.Incantation;
import hogwarts.domain.InteractiveObject;
import hogwarts.domain.SpellLevel;
import hogwarts.domain.Targetable;
import hogwarts.environment.Animation;
import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.environment.HasPosition;
import hogwarts.environment.effect.BeamOfLight;

/**
 *  Expelliarmus - a.k.a. the Disarming Charm, so-called because it
 *  changes its object's (the opponent's) quality from armed to 
 *  disarmed by separating them from their wand.
 *  
 *  It has been known to knock an opponent backwards in some cases, 
 *  as well as disarming them. This may depend on whether the spell 
 *  strikes an opponent's wand or body. The charm's incantation is 
 *  Expelliarmus.
 */
public class Expelliarmus extends AbstractSpell<Expelliarmus> {
    
    public Expelliarmus() {
        super(SpellLevel.Beginner);
    }
    
    public Expelliarmus(SpellLevel level) {
        super(level);
    }
    
    @Override
    public Expelliarmus specializeForLevel(SpellLevel level) {
        return new Expelliarmus(level);
    }
    
    @Override
    public void process(Incantation<Expelliarmus> incantation, Environment environment) {
        incantation.getTarget().foreach(getEffectOnTarget());
    }
    
    protected Effect<Targetable> getEffectOnTarget() {
        return new Effect<Targetable> () {
            @Override
            public void e(Targetable t) {
                t.strikedBySpell(Expelliarmus.this);
                t.as(InteractiveObject.class).foreach(bump);
            }
        };
    }

    @Override
    public Animation createAnimation(final Incantation<Expelliarmus> incantation) {
        return new Animation() {
            public void animate(Environment environment) {
                Caster caster = incantation.getCaster();
                Color color = getBeamColorFor(incantation.getSpell().getLevel());
                incantation.getTarget().foreach(getAnimationEffect(caster, color, environment));
            }
        };
    }
    
    protected Effect<Targetable> getAnimationEffect(final HasPosition caster, final Color color, final Environment environment) {
        return new Effect<Targetable> () {
            @Override
            public void e(Targetable target) {
                BeamOfLight beamOfLight = environment.getEffectFactory().createBeamOfLight(color);
                beamOfLight.makePulsating(caster, target);
            }
        };
    }
    
    public static Color getBeamColorFor(SpellLevel level) {
        switch(level) {
            case Beginner:
            case Novice:
                return Color.Gold;
            case Expert:
            case Master:
                default:
                return Color.Red;
        }
    }
    
    public boolean isTargetable() {
        return true;
    }
}

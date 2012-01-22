package hogwarts.domain.spell;

import static hogwarts.domain.spell.CanBeStrikedBySpell.F.strikedBySpell;
import static hogwarts.domain.spell.Spells.defaultIntensityForLevel;
import fj.Effect;
import hogwarts.domain.behavior.CanBeBumped;
import hogwarts.domain.misc.Intensity;
import hogwarts.environment.Color;
import hogwarts.environment.Environment;
import hogwarts.util.HasBehavior;

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
    public void performIncantation(Incantation<Expelliarmus> incantation, Environment environment) {
        Intensity intensity = defaultIntensityForLevel(getLevel());
        applyOnTarget(incantation, getEffectOnTarget(intensity));
    }
    
    protected Effect<HasBehavior> getEffectOnTarget(final Intensity intensity) {
        return new Effect<HasBehavior> () {
            @Override
            public void e(HasBehavior t) {
                t.as(CanBeStrikedBySpell.class).foreach(strikedBySpell(Expelliarmus.this));
                t.as(CanBeBumped.class).foreach(CanBeBumped.F.bump(intensity));
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

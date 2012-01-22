package hogwarts.domain.spell;

import static hogwarts.domain.spell.CanBeStrikedBySpell.F.strikedBySpell;
import static hogwarts.domain.spell.Spells.defaultIntensityForLevel;
import fj.Effect;
import hogwarts.domain.behavior.CanLevitate;
import hogwarts.domain.misc.Intensity;
import hogwarts.environment.Environment;
import hogwarts.util.HasBehavior;

/**
 *  Wingardium Leviosais a charm used to make objects levitate,
 *  
 *  Wingardium is a composite word, based on: English to wing 
 *  meaning "to fly" (e.g. the plane winged skywards); arduus 
 *  (meaning "high, tall, lofty, steep, proudly elevated") or 
 *  arduum (meaning "steep place, the steep"); and the common
 *  Latin ending -ium. Leviosa probably derives from Latin levo,
 *  meaning to "raise, lift up", or levis, meaning light (of 
 *  weight). Altogether, therefore, the incantation could best
 *   be read as "lift up high".
 *
 */
public class WingardiumLeviosa extends AbstractSpell<WingardiumLeviosa> {
    
    public WingardiumLeviosa() {
        super(SpellLevel.Beginner);
    }
    
    public WingardiumLeviosa(SpellLevel level) {
        super(level);
    }
    
    @Override
    public WingardiumLeviosa specializeForLevel(SpellLevel level) {
        return new WingardiumLeviosa(level);
    }
    
    @Override
    public void performIncantation(Incantation<WingardiumLeviosa> incantation,Environment environment) {
        Intensity intensity = defaultIntensityForLevel(getLevel());
        applyOnTarget(incantation, getEffectOnTarget(intensity));
    }
    
    protected Effect<HasBehavior> getEffectOnTarget(final Intensity intensity) {
        return new Effect<HasBehavior> () {
            @Override
            public void e(HasBehavior t) {
                t.as(CanBeStrikedBySpell.class).foreach(strikedBySpell(WingardiumLeviosa.this));
                t.as(CanLevitate.class).foreach(CanLevitate.F.levitate(intensity));
            }
        };
    }

    public boolean isTargetable() {
        return true;
    }
}

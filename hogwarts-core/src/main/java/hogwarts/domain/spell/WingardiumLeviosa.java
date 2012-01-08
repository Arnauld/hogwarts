package hogwarts.domain.spell;

import static hogwarts.domain.InteractiveObject.levitate;
import fj.Effect;
import hogwarts.domain.AbstractSpell;
import hogwarts.domain.Incantation;
import hogwarts.domain.InteractiveObject;
import hogwarts.domain.SpellLevel;
import hogwarts.domain.Targetable;
import hogwarts.environment.Environment;

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
    public void process(Incantation<WingardiumLeviosa> incantation,Environment environment) {
        incantation.getTarget().foreach(getEffectOnTarget());
    }
    
    protected Effect<Targetable> getEffectOnTarget() {
        return new Effect<Targetable> () {
            @Override
            public void e(Targetable t) {
                t.strikedBySpell(WingardiumLeviosa.this);
                t.as(InteractiveObject.class).foreach(levitate);
            }
        };
    }

    public boolean isTargetable() {
        return true;
    }
}

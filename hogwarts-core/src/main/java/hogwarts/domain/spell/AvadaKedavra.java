package hogwarts.domain.spell;

import static hogwarts.domain.behavior.LivingCreature.F.kill;
import static hogwarts.domain.spell.CanBeStrikedBySpell.F.strikedBySpell;
import fj.Effect;
import hogwarts.domain.behavior.LivingCreature;
import hogwarts.environment.Environment;
import hogwarts.util.HasBehavior;

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
    public void performIncantation(Incantation<AvadaKedavra> incantation, Environment environment) {
        applyOnTarget(incantation, getEffectOnTarget());
    }
    
    protected Effect<HasBehavior> getEffectOnTarget() {
        return new Effect<HasBehavior> () {
            @Override
            public void e(HasBehavior t) {
                t.as(CanBeStrikedBySpell.class).foreach(strikedBySpell(AvadaKedavra.this));
                t.as(LivingCreature.class).foreach(kill());
            }
        };
    }
    
    @Override
    public boolean isTargetable() {
        return true;
    }
}

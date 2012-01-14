package hogwarts.domain.spell;

import static hogwarts.domain.spell.CanBeStrikedBySpell.F.strikedBySpell;
import static hogwarts.domain.spell.Spells.defaultIntensityForLevel;
import fj.Effect;
import hogwarts.domain.Wand;
import hogwarts.domain.behavior.CanGlow;
import hogwarts.domain.behavior.HasWand;
import hogwarts.domain.misc.Intensity;
import hogwarts.environment.Color;
import hogwarts.environment.Environment;

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
    public void performIncantation(Incantation<Lumos> incantation, Environment environment) {
        Intensity intensity = defaultIntensityForLevel(getLevel());
        Effect<HasWand> effect = HasWand.applyEffectOnWand.f(getEffectOnWand(intensity));
        incantation.getCaster().as(HasWand.class).foreach(effect);
    }

    protected Effect<Wand> getEffectOnWand(final Intensity intensity) {
        return new Effect<Wand> () {
            @Override
            public void e(Wand t) {
                t.as(CanBeStrikedBySpell.class).foreach(strikedBySpell(Lumos.this));
                t.as(CanGlow.class).foreach(CanGlow.F.glow(Color.Light, intensity));
            }
        };
    }
    
    @Override
    public boolean isTargetable() {
        return false;
    }
}

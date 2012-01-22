package hogwarts.domain.spell;

import fj.Effect;
import hogwarts.domain.misc.Target;
import hogwarts.util.HasBehavior;



public abstract class AbstractSpell<T extends Spell<T>> implements Spell<T> {
    
    private final SpellLevel level;
    public AbstractSpell(SpellLevel level) {
        this.level = level;
    }

    @Override
    public String spellName() {
        return getClass().getSimpleName();
    }
    
    @Override
    public SpellLevel getLevel() {
        return level;
    }
    
    @SuppressWarnings("unchecked")
    public T specializeForLevel(SpellLevel level) {
        return (T)this;
    }
    
    protected void applyOnTarget(Incantation<T> incantation, final Effect<HasBehavior> effectOnTarget) {
        incantation.getTarget().foreach(new Effect<Target>() {
            public void e(Target target) {
                for(HasBehavior singleTarget : target) {
                    effectOnTarget.e(singleTarget);
                }
            };
        });
    }
}

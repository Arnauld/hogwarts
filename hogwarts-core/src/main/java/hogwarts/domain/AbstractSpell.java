package hogwarts.domain;

import hogwarts.environment.Animation;

public abstract class AbstractSpell<T extends Spell<T>> implements Spell<T> {
    
    private final SpellLevel level;
    public AbstractSpell(SpellLevel level) {
        this.level = level;
    }
    
    @Override
    public SpellLevel getLevel() {
        return level;
    }
    
    @SuppressWarnings("unchecked")
    public T specializeForLevel(SpellLevel level) {
        return (T)this;
    }
    
    @Override
    public Animation createAnimation(Incantation<T> incantation) {
        return Animation.None;
    }
    
}

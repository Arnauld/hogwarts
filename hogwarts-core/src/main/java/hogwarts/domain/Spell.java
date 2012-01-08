package hogwarts.domain;

import hogwarts.environment.Animation;
import hogwarts.environment.Environment;

public interface Spell<T extends Spell<T>> {
    boolean isTargetable();

    SpellLevel getLevel();
    T specializeForLevel(SpellLevel level);
    
    Animation createAnimation(Incantation<T> incantation);
    void process(Incantation<T> incantation, Environment environment);
}

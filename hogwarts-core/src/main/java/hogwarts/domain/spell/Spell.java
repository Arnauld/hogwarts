package hogwarts.domain.spell;

import hogwarts.environment.Environment;

public interface Spell<T extends Spell<T>> {
    String spellName();
    boolean isTargetable();

    SpellLevel getLevel();
    T specializeForLevel(SpellLevel level);
    
    void performIncantation(Incantation<T> spell, Environment env);
}

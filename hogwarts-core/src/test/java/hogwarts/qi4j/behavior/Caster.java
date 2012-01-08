package hogwarts.qi4j.behavior;


public interface Caster {
    void castSpell(String spellName);
    void castSpell(String spellName, Targetable target);
}

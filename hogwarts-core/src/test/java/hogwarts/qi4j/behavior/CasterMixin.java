package hogwarts.qi4j.behavior;

import org.qi4j.api.injection.scope.This;

public class CasterMixin implements Caster {

    @This HasSpellBook spellBook;
    
    @Override
    public void castSpell(String spellName) {
    }
    
    @Override
    public void castSpell(String spellName, Targetable target) {
    }
}

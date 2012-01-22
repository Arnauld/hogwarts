package hogwarts.domain.behavior;

import fj.data.Option;
import hogwarts.domain.SpellBook;
import hogwarts.domain.misc.Target;
import hogwarts.domain.spell.Incantation;
import hogwarts.domain.spell.Spell;
import hogwarts.util.HasBehavior;
import hogwarts.util.Parameter;

import org.qi4j.api.injection.scope.This;

public class CasterMixin implements Caster {

    private @This HasSpellBook hasSpellBook;
    private @This HasBehavior hasBehavior;
    
    public <T extends Spell<T>> Incantation<T> incanteSpell(Spell<T> spell, Parameter<?>.Value... parameters) {
        SpellBook spellBook = hasSpellBook.spellBook().get();
        if(spellBook.containsSpell(spell)) {
            Option<Target> targetAsOption = Option.none();
            for(Parameter<?>.Value value : parameters) {
                if(value.getParameter() == target) {
                    targetAsOption = Option.some((Target)value.value);
                    break;
                }
            }
            return new Incantation<T>(hasBehavior, spell, targetAsOption);
        }
        throw new RuntimeException("Attempt to cast an unknown spell: " + spell.spellName());
    }
}

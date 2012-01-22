package hogwarts.domain;

import java.util.Collection;

import fj.data.Option;
import hogwarts.domain.spell.Spell;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SpellBook {
    
    private Multimap<String, Spell<?>> spells = ArrayListMultimap.create();
    
    public <T extends Spell<T>> boolean containsSpell(Spell<T> searchedSpell) {
        Collection<Spell<?>> collection = spells.get(searchedSpell.spellName());
        if(collection==null)
            return false;
        
        for(Spell<?> spell : collection) {
            if(spell.getLevel()==searchedSpell.getLevel())
                return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Spell<T>> Option<Spell<T>> highestSpellVersion(Spell<T> searchedSpell) {
        Collection<Spell<?>> collection = spells.get(searchedSpell.spellName());
        if(collection==null)
            return Option.none();
        
        Spell<?> found = null;
        for(Spell<?> spell : collection) {
            if(found==null || spell.getLevel().isHigherThan(found.getLevel()))
                found = spell;
        }
        return Option.some((Spell<T>)found);
    }
    
}

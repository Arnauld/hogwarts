package hogwarts.qi4j.domain;

import java.util.List;

import org.qi4j.api.mixin.Mixins;
import org.qi4j.api.property.Property;

import fj.data.Option;

@Mixins({SpellBook.Mixin.class})
public interface SpellBook {
    Property<List<Spell>> spells();

    Option<Spell> findSpell(String spellName);
    
    public abstract class Mixin implements SpellBook {
        @Override
        public Option<Spell> findSpell(String spellName) {
            for(Spell spell : spells().get())
                if(spellName.equals(spell.name().get()))
                    return Option.some(spell);
            return Option.none();
        }
    }
}

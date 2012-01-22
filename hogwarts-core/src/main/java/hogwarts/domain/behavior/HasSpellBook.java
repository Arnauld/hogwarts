package hogwarts.domain.behavior;

import hogwarts.domain.SpellBook;

import org.qi4j.api.property.Immutable;
import org.qi4j.api.property.Property;

public interface HasSpellBook {
    public interface SpellBookProperty extends Property<SpellBook> {
    }
    @Immutable
    SpellBookProperty spellBook();
}

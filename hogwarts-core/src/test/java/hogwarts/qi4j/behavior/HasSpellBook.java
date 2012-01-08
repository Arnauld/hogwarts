package hogwarts.qi4j.behavior;

import hogwarts.qi4j.domain.SpellBook;

import org.qi4j.api.entity.association.Association;

public interface HasSpellBook {
    Association<SpellBook> spellBook();
}

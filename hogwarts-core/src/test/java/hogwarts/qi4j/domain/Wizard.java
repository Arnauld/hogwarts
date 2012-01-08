package hogwarts.qi4j.domain;

import hogwarts.qi4j.behavior.Caster;
import hogwarts.qi4j.behavior.HasName;
import hogwarts.qi4j.behavior.HasSpellBook;

public interface Wizard extends HasName, Caster, HasSpellBook {

}

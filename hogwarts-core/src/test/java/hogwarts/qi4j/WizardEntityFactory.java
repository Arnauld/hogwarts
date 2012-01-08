package hogwarts.qi4j;

import hogwarts.qi4j.domain.SpellBook;
import hogwarts.qi4j.domain.Wizard;

public interface WizardEntityFactory {

    Wizard create(String name, SpellBook book);
}
